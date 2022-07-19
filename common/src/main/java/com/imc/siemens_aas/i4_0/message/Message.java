package com.imc.siemens_aas.i4_0.message;

import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.aasenv.common.Identification;
import com.imc.siemens_aas.aasenv.common.Type.IdType;
import com.imc.siemens_aas.i4_0.message.frame.Frame;
import com.imc.siemens_aas.i4_0.message.frame.Receiver;
import com.imc.siemens_aas.i4_0.message.frame.Role;
import com.imc.siemens_aas.i4_0.message.frame.Sender;
import com.imc.siemens_aas.i4_0.message.frame.semanticProtocol.RoleType;
import com.imc.siemens_aas.i4_0.message.interactionElement.InteractionElement;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class Message {
    private Frame frame;
    private List<InteractionElement> interactionElements;

    /**
     * 对返回的消息帧头进行初始化，暂不设置frameType
     * @param message
     * @return
     */
    public Message initRecvFrame(Message message) {

        Frame recvFrame = message.getFrame();

        frame = Frame.custom()
                .setSendIdentification(recvFrame.getReceiver().getIdentification())
                .setSendRole(recvFrame.getReceiver().getRole())
                .setRecvIdentification(recvFrame.getSender().getIdentification())
                .setRecvRole(recvFrame.getSender().getRole())
                .setConversationId(recvFrame.getConversationId())
                .setMessageId(recvFrame.getMessageId() + 1)//TODO 这里的策略是回复的消息将messageId + 1，之后可以完善
                .build();

        return this;
    }
}
