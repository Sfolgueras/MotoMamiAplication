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
    String callProcessReadInfo(@PathVariable String resource){
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
                default:
            }
        } catch (Exception e){
            System.err.println("No funcionan las tareas brr");
        }
        System.out.println("El valor de resource es: "+resource);
        return "Buenos dias";
    }
}