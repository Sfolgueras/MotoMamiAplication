package com.motomami.tasks;

import com.motomami.Services.ProcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.motomami.Utils.Constants.*;

@Component
public class GetCustomerTask {

    @Autowired
    ProcesService pService;

    @Scheduled(cron = "${cron.task.getCustomer}")
    public void task(){
        try{
            System.out.println("\nEsta tarea se lanza cada 15 segundos");
            pService.readFileInfo(C_SOURCE_CUSTOMER);
        } catch (Exception e){
            System.err.println("No funcó la tarea de los customers");
        }
    }
}
