package com.imc.siemens_aas.i4_0.statemachine.state.requester;

import com.imc.siemens_aas.i4_0.message.Message;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Offer {
    private String url;
    private Message offerMsg;
}
