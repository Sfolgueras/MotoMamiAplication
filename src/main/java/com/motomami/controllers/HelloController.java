package com.motomami.controllers;

import com.motomami.Services.ProcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.motomami.Utils.Constants.*;


@RestController
public class HelloController
{
    @Autowired
    ProcesService pService;
    @RequestMapping("/")
    String hellow(){
        return "Hello World!";
    }

    @RequestMapping(value =("/readInfo/{resource}"), method = RequestMethod.GET, produces = "application/json")
    String callReadInfo(@PathVariable String resource){
        try{
            System.out.println("\nMe estan llamando desde la web");
            switch (resource.toUpperCase()){
                case C_SOURCE_PARTS:
                    pService.readFileInfo(C_SOURCE_PARTS);
                    break;
                case C_SOURCE_CUSTOMER:
                    pService.readFileInfo(C_SOURCE_CUSTOMER);
                    break;
                case  C_SOURCE_VEHICLE:
                    pService.readFileInfo(C_SOURCE_VEHICLE);
                    break;
                default:
            }
        } catch (Exception e){
            System.err.println("No funcionan las tareas brr");
        }
        System.out.println("El valor de resource es: "+resource);
        return "Buenos dias";
    }


    @RequestMapping(value =("/processInfo/{resource}"), method = RequestMethod.GET, produces = "application/json")
    String callProcessInfo(@PathVariable String resource){
        try{
            System.out.println("\nMe estan llamando desde el process");
            switch (resource.toUpperCase()){
                case C_SOURCE_PARTS:
                //    pService.readFileInfo(C_SOURCE_PARTS);
                    break;
                case C_SOURCE_CUSTOMER:
                    pService.processInfoWithStatusNotProcessed(C_SOURCE_CUSTOMER);
                    break;
                case  C_SOURCE_VEHICLE:
                //    pService.readFileInfo(C_SOURCE_VEHICLE);
                    break;
                default:
            }
        } catch (Exception e){
            System.err.println("No funcionan las tareas brr");
        }
        System.out.println("El valor de resource es: "+resource);
        return "Tamo activo siempre papi";
    }


    @RequestMapping(value =("/generateInvoice/{p_month}"), method = RequestMethod.GET, produces = "application/json")
    String callGenerateFile(@PathVariable String p_month){
        try{
            System.out.println("\nMe estan llamando desde el process");

           // pService.generateInvoceFile(C_SOURCE_CUSTOMER);

        } catch (Exception e){
            System.err.println("No funcionan las tareas brr");
        }
        System.out.println("El valor de resource es: "+p_month);
        return "Tamo activo siempre papi";
    }
}