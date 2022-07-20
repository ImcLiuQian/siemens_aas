package com.imc.service_cnc2.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imc.service_cnc2.entity_submodel.CNC2;
import com.imc.service_cnc2.entity_submodel.CNC2DataBase;
import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.aasenv.submodel.Submodel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WebSocketService {
    private List<Submodel> submodels;
    private ObjectMapper mapper;

    {
        submodels = new ArrayList<>(3);
        mapper = new ObjectMapper();
    }

    @Scheduled(fixedRate = 3000)
    public void pushSubmodels() throws IOException {
        if (WebSocketServer.onlineCount != 0) {
            submodels.clear();
            CNC2.readValue();
            Submodel Instance_factoryIO_Submodel
                    = CNC2DataBase.Instance_factoryIO_Submodel.setModelObject(CNC2.getInstance_factoryIO()).refreshValue();
            Submodel Nameplate_Submodel
                    = CNC2DataBase.Nameplate_Submodel;
            Submodel Service_Submodel
                    = CNC2DataBase.Service_Submodel.setModelObject(CNC2.getCnc2_service()).refreshValue();
            submodels.addAll(Arrays.asList(Instance_factoryIO_Submodel, Nameplate_Submodel, Service_Submodel));
            String submodelsJson = mapper.writeValueAsString(submodels);
            WebSocketServer.sendInfo(submodelsJson, "CNC2");
            AasEnv aasEnv = CNC2DataBase.CNC2.refreshSubmodels(submodels);
            String aasEnvJson = mapper.writeValueAsString(aasEnv);
            WebSocketServer.sendInfo(aasEnvJson, "CNC2");
        }
    }
}
