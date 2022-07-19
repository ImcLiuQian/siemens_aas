package com.imc.siemens_aas.i4_0.statemachine.state.provider;

import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.message.frame.MessageType;

/**
 * 服务结束状态，等待Proposal消息
 * 等待message消息按照完美逻辑，是应该写在这个类里面的，但是为了简化开发使用SpringBoot的Controller，就直接写到状态机里面了
 */
public class ProposalWaiting implements ProviderState{
    private static ProposalWaiting singleton = new ProposalWaiting();
    public static ProviderState getInstance() {
        return singleton;
    }
    private ProposalWaiting() {}

    @Override
    public void doExecute(ProviderContext context, Message msg) {
        //判断
        String type = msg.getFrame().getType();
        if (type.equals(MessageType.CallForPro)) {
            //状态切换
            context.changeState(ProposalAssessing.getInstance());
            //具体参数判断交给ProposalAssessing去做
            context.handle(msg);
        }
        //如果不是CallForPro消息，那么就什么都不做，让状态机保持在这个状态
    }
}
