package com.motomami.tasks;
import com.motomami.Services.ProcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import static com.motomami.Utils.Constants.C_SOURCE_PARTS;

@Component
public class GetPartsTask {

//    @Autowired
//    ProcesService pService;
//    @Scheduled(cron = "${cron.task.getpart}")
//    public void task(){
//        try{
//            System.out.println("\nEsta tarea se lanza cada 15 segundos");
//            //pService.readFileInfo(C_SOURCE_PARTS);
//        } catch (Exception e){
//            System.err.println("heey pero me estoy poniendo peluche yo 😏😏");
//        }
//    }
}
