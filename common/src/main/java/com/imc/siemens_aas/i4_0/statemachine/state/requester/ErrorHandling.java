package com.imc.siemens_aas.i4_0.statemachine.state.requester;

import com.imc.siemens_aas.i4_0.message.Message;

/**
 * 错误处理状态，目前是当被调用方出现故障，并告诉Requester时，切换到此状态
 */
public class ErrorHandling implements RequesterState{
    private static ErrorHandling singleton = new ErrorHandling();
    public static RequesterState getInstance() {
        return singleton;
    }
    private ErrorHandling() {}

    /**
     * 重新发送上一次的I4.0消息
     * @param context
     * @param msg
     */
    @Override
    public void doExecute(RequesterContext context, Message msg) {
        //切换到CFPSendingWaiting状态，并且把lastMsg给其处理
        context.changeState(CFPSendingWaiting.getInstance());
        //依次处理每条消息
        for (Message lastMsg : context.getLastMsgs()) {
            context.handle(lastMsg);
        }
    }
}
