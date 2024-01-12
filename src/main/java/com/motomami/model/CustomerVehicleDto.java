package com.motomami.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerVehicleDto {
    private String customerType;
    private String dniCif;
    private List<VehicleDto> vehiculos = new ArrayList<>();

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getDniCif() {
        return dniCif;
    }

    public void setDniCif(String dniCif) {
        this.dniCif = dniCif;
    }

    public List<VehicleDto> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<VehicleDto> vehiculos) {
        this.vehiculos = vehiculos;
    }
}
