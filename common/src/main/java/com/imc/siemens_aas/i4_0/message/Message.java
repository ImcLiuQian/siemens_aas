package com.imc.siemens_aas.i4_0.message;

import com.imc.siemens_aas.i4_0.message.frame.Frame;
import com.imc.siemens_aas.i4_0.message.frame.Receiver;
import com.imc.siemens_aas.i4_0.message.frame.Sender;
import com.imc.siemens_aas.i4_0.message.interactionElement.InteractionElement;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class Message {
    private Frame frame;
    private List<InteractionElement> interactionElements;

//    /**
//     * 根据aas将Message初始化
//     * 需要额外设置：
//     * @param aasEnv
//     */
//    public Message(AasEnv aasEnv) {
//        this.frame = new Frame();
//
//
//
//        frame.setSender(aasEnv.get);
//
//
//        resMsg.setFrame(frame);
//    }

    /**
     * TODO
     */
    private Message initSendFrame() {

        //TODO

        return this;
    }

    /**
     * 对返回的消息帧头进行初始化，暂不设置frameType，messageId，
     * @param message
     * @return
     */
    public Message initRecvFrame(Message message) {
        frame = new Frame();
        Frame recvFrame = message.getFrame();
        //set Sender and Receiver
        Sender sender = new Sender();
        sender.setIdentification(recvFrame.getReceiver().getIdentification());
        sender.setRole(recvFrame.getReceiver().getRole());
        frame.setSender(sender);
        Receiver receiver = new Receiver();
        receiver.setIdentification(recvFrame.getSender().getIdentification());
        receiver.setRole(recvFrame.getSender().getRole());
        frame.setReceiver(receiver);
        return this;
    }
}
