package com.imc.service_cnc1_copy.websocket;

import com.imc.siemens_aas.aasenv.AasEnv;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WsMsg {
    private AasEnv aasEnv;
    private String state;
}
