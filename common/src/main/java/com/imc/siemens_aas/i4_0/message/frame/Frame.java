package com.imc.siemens_aas.i4_0.message.frame;

import com.imc.siemens_aas.i4_0.message.frame.semanticProtocol.SemanticProtocol;
import lombok.Data;

@Data
public class Frame {
    private String type;
    private Sender sender;
    private Receiver receiver;
    private int conversationId;
    private int messageId;
    private int replyBy;
    private SemanticProtocol semanticProtocol;
}
