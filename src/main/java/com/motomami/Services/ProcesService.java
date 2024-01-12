package com.motomami.Services;
import org.springframework.stereotype.Service;

@Service
public interface ProcesService {
    //metodo que todas las clases implementen de esta utilizarán.
    void readFileInfo(String pSource);
}
