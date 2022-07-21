package com.imc.service_as.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imc.service_as.entity_submodel.AS;
import com.imc.service_as.entity_submodel.AsDataBase;
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
            AS.readValue();
            Submodel Instance_factoryIO_Submodel
                    = AsDataBase.Instance_factoryIO_Submodel.setModelObject(AS.getInstance_factoryIO()).refreshValue();
            Submodel Nameplate_Submodel
                    = AsDataBase.Nameplate_Submodel;
            Submodel Service_Submodel
                    = AsDataBase.Service_Submodel.setModelObject(AS.getAs_service()).refreshValue();
            submodels.addAll(Arrays.asList(Instance_factoryIO_Submodel, Nameplate_Submodel, Service_Submodel));

            AasEnv aasEnv = AsDataBase.AS.refreshSubmodels(submodels);
            String msgJson = mapper.writeValueAsString(new WsMsg().setAasEnv(aasEnv).setState(null));
            WebSocketServer.sendInfo(msgJson, "AS");
        }
    }

    @Scheduled(fixedRate = 10)
    public void pushState() throws IOException {
        if (WebSocketServer.onlineCount != 0) {
            String msgJson = mapper.writeValueAsString(new WsMsg().setAasEnv(null).setState(stateMach.getState().getClass().getSimpleName()));
            WebSocketServer.sendInfo(msgJson, "AS");
        }
    }
}
