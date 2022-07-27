package com.imc.orderapp.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imc.siemens_aas.i4_0.statemachine.RequesterStateMach;
import com.imc.siemens_aas.i4_0.statemachine.state.requester.ErrorHandling;
import com.imc.siemens_aas.i4_0.statemachine.state.requester.RequesterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 推送Requester的状态
 */
@Service
public class WebSocketService {
    private ObjectMapper mapper;

    {
        mapper = new ObjectMapper();
    }

    @Autowired
    private RequesterStateMach stateMach;

    @Scheduled(fixedRate = 10)
    public void pushState() throws IOException {
        if (WebSocketServer.onlineCount != 0) {
            WsMsg wsMsg = new WsMsg();

            RequesterState state = stateMach.getState();
            wsMsg.setState(state.getClass().getSimpleName());
            if (state == ErrorHandling.getInstance()) {//如果是错误状态
                wsMsg.setErrorAasIds(stateMach.getErrAasIds());
            } else {
                wsMsg.setErrorAasIds(null);
            }

            String msgJson = mapper.writeValueAsString(wsMsg);
            WebSocketServer.sendInfo(msgJson, "Order");
        }
    }
}
