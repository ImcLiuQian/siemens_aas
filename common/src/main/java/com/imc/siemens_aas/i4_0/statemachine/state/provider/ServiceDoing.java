package com.imc.siemens_aas.i4_0.statemachine.state.provider;


import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.message.frame.Frame;
import com.imc.siemens_aas.i4_0.message.frame.MessageType;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public class ServiceDoing implements ProviderState{
    private static ServiceDoing singleton = new ServiceDoing();
    public static ProviderState getInstance() {
        return singleton;
    }
    private ServiceDoing() {}

    @Override
    public void doExecute(ProviderContext context, Message msg) {
        Message resMsg = new Message().initRecvFrame(msg);
        Frame resFrame = resMsg.getFrame();
        //调用AAS的服务
        try {
            context.getAasEnv().doService(context.getResult().getOperations());
            resFrame.setType(MessageType.Conforming);
            log.info("服务执行完成");
            context.changeState(ProposalWaiting.getInstance());//服务完成，切换至ProposalWaiting状态
        } catch (InvocationTargetException | IllegalAccessException e) {
            resFrame.setType(MessageType.Error);
            throw new RuntimeException(e);
        }
        context.setResMsg(resMsg);
    }
}
