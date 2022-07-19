package com.imc.siemens_aas.i4_0.statemachine.state.requester;

import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.message.frame.MessageType;
import lombok.extern.slf4j.Slf4j;

/**
 * 等待offer提供方完成服务的消息
 * 等待message消息按照完美逻辑，是应该写在这个类里面的，但是为了简化开发使用SpringBoot的Controller，就直接写到状态机里面了
 */
@Slf4j
public class CompletedWaiting implements RequesterState{
    private static CompletedWaiting singleton = new CompletedWaiting();
    public static RequesterState getInstance() {
        return singleton;
    }
    private CompletedWaiting() {}

    @Override
    public void doExecute(RequesterContext context, Message msg) {
        if (msg.getFrame().getType().equals(MessageType.Conforming)) {//如果执行完成
            log.info("服务调用完成");
            context.changeState(CFPSendingWaiting.getInstance());
        }
    }
}
