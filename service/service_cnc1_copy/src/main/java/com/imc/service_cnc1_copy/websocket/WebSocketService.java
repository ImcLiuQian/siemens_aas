package com.imc.service_cnc1_copy.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imc.service_cnc1_copy.entity_submodel.CNC1BackUp;
import com.imc.service_cnc1_copy.entity_submodel.CNC1BackUpDataBase;
import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.aasenv.submodel.Submodel;
import com.imc.siemens_aas.i4_0.statemachine.ProviderStateMach;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProviderStateMach stateMach;

    @Scheduled(fixedRate = 3000)
    public void pushAAS() throws IOException {
        if (WebSocketServer.onlineCount != 0) {
            submodels.clear();
            CNC1BackUp.readValue();
            Submodel Instance_factoryIO_Submodel
                    = CNC1BackUpDataBase.Instance_factoryIO_Submodel.setModelObject(CNC1BackUp.getInstance_factoryIO()).refreshValue();
            Submodel Nameplate_Submodel
                    = CNC1BackUpDataBase.Nameplate_Submodel;
            Submodel Service_Submodel
                    = CNC1BackUpDataBase.Service_Submodel.setModelObject(CNC1BackUp.getCnc1BackUp_service()).refreshValue();
            submodels.addAll(Arrays.asList(Instance_factoryIO_Submodel, Nameplate_Submodel, Service_Submodel));

            AasEnv aasEnv = CNC1BackUpDataBase.CNC1BackUp.refreshSubmodels(submodels);
            String aasEnvJson = mapper.writeValueAsString(new WsMsg().setAasEnv(aasEnv).setState(null));
            WebSocketServer.sendInfo(aasEnvJson, "CNC1BackUp");
        }
    }

    @Scheduled(fixedRate = 10)
    public void pushState() throws IOException {
        if (WebSocketServer.onlineCount != 0) {
            String msgJson = mapper.writeValueAsString(new WsMsg().setAasEnv(null).setState(stateMach.getState().getClass().getSimpleName()));
            WebSocketServer.sendInfo(msgJson, "CNC1BackUp");
        }
    }
}
