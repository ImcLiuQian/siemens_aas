package com.imc.siemens_aas.i4_0.statemachine;

import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.message.frame.Frame;
import com.imc.siemens_aas.i4_0.message.frame.MessageType;
import com.imc.siemens_aas.i4_0.message.interactionElement.InteractionElement;
import com.imc.siemens_aas.i4_0.statemachine.judge.JudgeStrategy;
import com.imc.siemens_aas.i4_0.statemachine.judge.ParaJudge;
import com.imc.siemens_aas.i4_0.statemachine.judge.ServiceJudge;

import java.util.List;

public class ProviderStateMach {

    private String conversationId;//状态机的会话Id
    private int messageId;//状态机的会话次数

    private AasEnv aasEnv;
    private JudgeStrategy judgeStrategy;


    public ProviderStateMach(AasEnv aasEnv, JudgeStrategy judgeStrategy) {
        this.aasEnv = aasEnv;
        this.judgeStrategy = judgeStrategy;
    }

    public Message handle(Message message) {
        Message resMsg = new Message().initRecvFrame(message);
        Frame resFrame = resMsg.getFrame();

        String type = message.getFrame().getType();
        if (type.equals(MessageType.CallForPro)) {
            List<InteractionElement> interactionElements = message.getInteractionElements();
            boolean isKnown = ParaJudge.isKnown(message);
            if (isKnown) {
                boolean isOk = new ServiceJudge(this.aasEnv, interactionElements).setJudgeStrategy(judgeStrategy).isOK();
                if (isOk) {
                    this.aasEnv.doServic(interactionElements);
                    resFrame.setType(MessageType.Offer);
                } else {
                    resFrame.setType(MessageType.Refusal);
                }
            }
        }
        resFrame.setType(MessageType.NotUnderStood);
        return resMsg;
    }
}