package com.imc.siemens_aas.i4_0.statemachine.state.provider;

import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.message.frame.Frame;
import com.imc.siemens_aas.i4_0.message.frame.MessageType;
import com.imc.siemens_aas.i4_0.message.interactionElement.InteractionElement;
import com.imc.siemens_aas.i4_0.statemachine.providerjudge.ParaJudge;
import com.imc.siemens_aas.i4_0.statemachine.providerjudge.ProJdResult;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ProposalAssessing implements ProviderState{
    private static ProposalAssessing singleton = new ProposalAssessing();
    public static ProviderState getInstance() {
        return singleton;
    }
    private ProposalAssessing() {}

    /**
     * 参数判断并回复消息
     * @param context
     * @param message
     */
    @Override
    public void doExecute(ProviderContext context, Message message) {
        //根据对方AAS提供的message评估出自己需要回复的resMsg，其中主要是对能否提供能力的判断
        Message resMsg = handle(context, message);
        context.setResMsg(resMsg);
        //根据评估出来的resMsg，进行状态切换
        String type = resMsg.getFrame().getType();
        if (type.equals(MessageType.Refusal) || type.equals(MessageType.NotUnderStood)) {//进入服务结束状态
            context.changeState(ProposalWaiting.getInstance());
        } else if (type.equals(MessageType.Offer)) {
            context.changeState(OfferResWaiting.getInstance());
        }
    }

    private Message handle(ProviderContext context, Message message) {
        Message resMsg = new Message().initRecvFrame(message);
        Frame resFrame = resMsg.getFrame();

        List<InteractionElement> interactionElements = message.getInteractionElements();
        boolean isKnown = ParaJudge.isKnown(message);
        if (isKnown) {
            ProJdResult result = context.getStrategy().judge(context.getAasEnv(), interactionElements);
            context.setResult(result);
            boolean isOk = result.isOK();
            if (isOk) {
                resFrame.setType(MessageType.Offer);
            } else {
                resFrame.setType(MessageType.Refusal);
            }
        } else {
            resFrame.setType(MessageType.NotUnderStood);
        }

        return resMsg;
    }
}
