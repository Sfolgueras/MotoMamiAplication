package com.motomami.Services.impl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.motomami.Services.ProcesService;
import com.motomami.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static com.motomami.Utils.Constants.*;

@Service
public class ProcesServiceImpl implements ProcesService {
    @Value("${path.folder.outFiles}")
    String pathFolderoutFiles;
    @Value("${path.folder.inFiles}")
    String pathFolderinFiles;
    @Value("${path.folder.providers}")
    String pathFolderProviders;
    @Value("${path.folder.parts.providers}")
    String pathFolderPartsProviders;
    @Value("${path.folder.vehicles.providers}")
    String pathFolderVehiclesProviders;
    @Value("${path.folder.customers.providers}")
    String pathFolderCustomersProviders;
    @Value("${extension.file.providers}")
    String extensionFileProviders;
    Connection con;

    /*
    * Metodo que determina que fichero se leerá según el valor de source
    * */
    public void readFileInfo(String source) {
        try{
            switch (source){
                case C_SOURCE_PARTS:
                    readFileInfoParts();
                    break;
                case C_SOURCE_CUSTOMER:
                    readFileInfoCustomer();
                case C_SOURCE_VEHICLE:
                    readFileInfoVehicles();
                default:
            }
        } catch (Exception e) {
            System.err.println("Error leyendo los ficheros"+e.getMessage());
        }
    }
    /*
    * Metodo generico que usaremos por si nos introducen una fecha en un formato no aceptado ej:2020-11-22
    * */
    public Date getDateFormatMM(String sDate) {
        Date dateReturn = null;
        try {
            dateReturn = new SimpleDateFormat("yyyy/MM/dd").parse(sDate);
        } catch (ParseException e){
            try{
                String [] fecha = sDate.split("-");
                if(fecha.length == 3){
                    String nuevaFecha = fecha[0]+"/"+fecha[1]+"/"+fecha[2];
                    dateReturn = new SimpleDateFormat("yyyy/MM/dd").parse(nuevaFecha);
                }else{
                    System.err.println("FORMATO INCORRECTO");
                }
            } catch (ParseException Pe){
                System.err.println("Error al convertir la fecha "+ sDate);
            } catch (Exception ie ){
                System.err.println("Error");
            }
        } catch (Exception e){
            System.err.println("Error desconocido al convertir la fecha "+ sDate);
        }
        return dateReturn;
    }
    /*
    * Metodo que lee el fichero de los partes.
    * */
    public void readFileInfoParts() {
        List<PartDto> listOfParts = new ArrayList<PartDto>();
        //src/main/resources/in/BBVA/MM_insurance_customers_.dat
        String filePath = pathFolderPartsProviders;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            int numlinea = 0;
            while ((linea = br.readLine()) != null) {
                if(numlinea != 0) { //condicion para no cargar la primera linea del
                                    // fichero que contiene el nombre de las columnas.
                    PartDto part = new PartDto();
                    fillPartsDto(linea, part);
                    listOfParts.add(part);
                }
                System.out.println("Elemento "+listOfParts.size());
                numlinea++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("El archivo especificado no se ha encontrado, revise la ruta");
        } catch (IOException e) {
            System.err.println("Ha ocurrido un error durante la lectura del archivo");
        }
    }

    /*
    * Metodo que rellena un objeto parte con los datos del fichero.
    * .trim() sirve para ignorar los espacios que metan en el fichero
    * */
    public void fillPartsDto(String linea, PartDto part) {
        String[] partes = linea.split(";");
        System.out.println(linea);
        part.setIdExternal(partes[0].trim());
        part.setDescriptionPartExternal(partes[2].trim());
        part.setCodeDamageExternal(partes[3].trim());
        part.setIdentityCode(partes[4].trim());
        part.setDatePartExternal(getDateFormatMM(partes[1].trim()));
    }
    /*
    * Metodo que lee el fichero de clientes.
    * */
    public void readFileInfoCustomer() {
        System.out.println("llamada a readFileInfoCustomer");
        List<CustomerDto> listOfCustomer = new ArrayList<CustomerDto>();
        String provider = pathFolderProviders.split(";")[0];
        String filePath = pathFolderinFiles+"/"+provider+"/"+pathFolderCustomersProviders.split(";")[0]+extensionFileProviders;
        System.out.println(filePath);
        //creamos un gson para la serializacion de los objetos clientes para su insercion en la base de datos.
        GsonBuilder gsonBuilder = new GsonBuilder().serializeNulls().setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            int numlinea = 0;
            while ((linea = br.readLine()) != null) {
                if(numlinea != 0) { //condicion para no cargar la primera linea del fichero.
                    CustomerDto customer = new CustomerDto();
                    fillCostumerDto(linea, customer);
                    listOfCustomer.add(customer);
                    //serializamos el objeto a json y lo guardamos en una variable para su posterior insercion.
                    String customerString = gson.toJson(customer);
                    boolean exists = false;
                    exists = existInfoCustomer(customerString);
                    if (exists){
                        System.out.println("Existe");
                    } else{
                        //lamada al metodo que inserta a la bbdd y le pasamos el string que contiene el json.
                        insertIntCustomer(customerString);
                        System.out.println("Se han añadido los datitos");
                    }
                }
                numlinea++;
                System.out.println("Elemento "+listOfCustomer.size());
            }
        } catch (FileNotFoundException e) {
            System.err.println("El archivo especificado no se ha encontrado, revise la ruta");
        } catch (IOException e) {
            System.err.println("Ha ocurrido un error durante la lectura del archivo");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
     * Metodo que rellena un objeto cliente con los datos del fichero.
     * */
    private void fillCostumerDto(String linea, CustomerDto customer){
        String[] clientes = linea.split(";");
        String dni = clientes[0].trim();
        String nombre = clientes[1].trim();
        String apellido1 = clientes[2].trim();
        String apellido2 = clientes[3].trim();
        String email = clientes[4].trim();
        String sDateBirth = clientes[5].trim();
        DireccionDto dre = new DireccionDto();
        String telefono = clientes[10].trim();
        String sexo = clientes[11].trim();
        System.out.println(linea);
        customer.setDNI(dni);
        customer.setNombre(nombre);
        customer.setApellido1(apellido1);
        customer.setApellido2(apellido2);
        customer.setEmail(email);
        customer.setDireccion(dre);
        customer.getDireccion().setTipoVia(clientes[7]);
        customer.getDireccion().setCodPostal(clientes[6]);
        customer.getDireccion().setCiudad(clientes[8]);
        customer.getDireccion().setNumero(clientes[9]);
        customer.setTelefono(telefono);
        customer.setSexo(sexo);
        customer.setFechaNacimiento(getDateFormatMM(sDateBirth));
    }

    /*
    * Metodo que se conecta a la base de datos y comprueba que el cliente no existe realizando una consulta.
    * */
    public boolean existInfoCustomer(String p_json) throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/motomamidb","root", "Sergino_PRO1");
        String query = "select count(*) as numCustomer from mm_intcustomers where contJson = ?;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            System.out.println("LLamada al exsits");
            ps = con.prepareStatement(query);
            ps.setString(1,p_json);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println("No funciona la select" + e.getMessage());
        } catch (Exception e){
            System.err.println("Error");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return true;
    }
    /*
    * Si el cliente no esta metido en la bbdd este metodo lo inserta
    * */
    public void insertIntCustomer(String p_json) throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/motomamidb","root", "Sergino_PRO1");
        String prov = "bbva";
        String creador ="mm_app";
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaFormateada = fechaHoraActual.format(formatter);
        PreparedStatement ps = null;
        String query = "INSERT INTO mm_intcustomers " +
                "(idProv, contJson, creationDate, lastUpdate, createdBy, updatedBy) " +
                "VALUES " +
                "(?, ?, ?, ?, ?, ?)";
        try {
            System.out.println("Insertando los datos grrr");
            ps = con.prepareStatement(query);
            ps.setString(1,prov);
            ps.setString(2,p_json);
            ps.setString(3, fechaFormateada);
            ps.setString(4,fechaFormateada);
            ps.setString(5,creador);
            ps.setString(6,creador);
            int rs3 = ps.executeUpdate();
            System.out.println("Se han actualizado "+ rs3 +" registros");
        } catch (SQLException e) {
            System.err.println("No funciona insertando");
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    /*
     * Metodo que lee el fichero de vehiculos.
     * */
    public void readFileInfoVehicles() {
        CustomerVehicleDto cvDto =  null;
        List<VehicleDto> listOfVehiculo = new ArrayList<VehicleDto>();
        String filePath = pathFolderVehiclesProviders;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            int numlinea = 0;
            String dniAnterior = null;
            while ((linea = br.readLine()) != null) {
                if(numlinea != 0) { //condicion para no cargar la primera linea del fichero.
                    String[] customerVehicle = linea.split(";");
                    VehicleDto vehiculoDto = new VehicleDto();
                    fillVehicleDto(linea, vehiculoDto);
                    listOfVehiculo.add(vehiculoDto);
                }
                System.out.println("Elemento "+listOfVehiculo.size());
                numlinea++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("El archivo especificado no se ha encontrado, revise la ruta");
        } catch (IOException e) {
            System.err.println("Ha ocurrido un error durante la lectura del archivo");
        }
    }

    /*
     * Metodo que rellena un objeto vehiculo con los datos del vehiculo.
     * */
    public void fillVehicleDto(String linea, VehicleDto vehiculo){
        String[] vehiculos = linea.split(";");
        CustomerVehicleDto customerVehiclesDto = new CustomerVehicleDto();
        String id = vehiculos[2].trim();
        String numberPlate = vehiculos[3].trim();
        String tipoVehiculo = vehiculos[4].trim();
        String marca = vehiculos[5].trim();
        String modelo = vehiculos[6].trim();
        String color = vehiculos[7].trim();
        String numeroSerial = vehiculos[8].trim();
        System.out.println(linea);
        vehiculo.setIdVehicleExternal(id);
        vehiculo.setNumberPlate(numberPlate);
        vehiculo.setVehicleType(tipoVehiculo);
        vehiculo.setBrand(marca);
        vehiculo.setModel(modelo);
        vehiculo.setColor(color);
        vehiculo.setSerialNumber(numeroSerial);
    }
}