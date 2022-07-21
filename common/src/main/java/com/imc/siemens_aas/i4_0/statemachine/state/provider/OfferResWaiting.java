package com.imc.siemens_aas.i4_0.statemachine.state.provider;


import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.message.frame.MessageType;

/**
 * 等待message消息按照完美逻辑，是应该写在这个类里面的，但是为了简化开发使用SpringBoot的Controller，就直接写到状态机里面了
 */
public class OfferResWaiting implements ProviderState{
    private static OfferResWaiting singleton = new OfferResWaiting();
    public static ProviderState getInstance() {
        return singleton;
    }
    private OfferResWaiting() {}

    @Override
    public void doExecute(ProviderContext context, Message msg) {
        String type = msg.getFrame().getType();
        if (type.equals(MessageType.OfferAcceptance)) {//如果是OfferAcceptance服务接收消息，那么就直接调用aas的方法
            context.changeState(ServiceDoing.getInstance());
            context.handle(msg);
        } else if (type.equals(MessageType.OfferRejection)) {//如果是OfferRejection，就什么也不用做，切换回ProposalAssessing状态等待下一条I4.0消息
            context.setResult(null);
            context.setResMsg(null);
            context.changeState(ProposalWaiting.getInstance());
        }
    }
}
