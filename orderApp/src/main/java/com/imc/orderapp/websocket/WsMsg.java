package com.imc.orderapp.websocket;

import com.imc.siemens_aas.aasenv.AasEnv;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class WsMsg {
    private String state;
    private List<String> errorAasIds;
}
