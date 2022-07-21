package com.imc.siemens_aas.i4_0.statemachine.state.requester.thread;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.message.frame.MessageType;
import com.imc.siemens_aas.i4_0.statemachine.state.requester.Offer;
import com.imc.siemens_aas.i4_0.statemachine.state.requester.RequesterContext;
import com.imc.siemens_aas.utils.HttpClientHelper;
import lombok.SneakyThrows;

public class CFPSendingThread extends Thread{

    private RequesterContext context;
    private String url;
    private Message msg;

    public CFPSendingThread(RequesterContext context, String url, Message message) {
        this.context = context;
        this.url = url;
        this.msg = message;
    }

    @SneakyThrows
    @Override
    public void run() {
        ObjectMapper mapper = new ObjectMapper();
        String resMsgJson = HttpClientHelper.doPostByParam(url + "/aas/i4.0/provider/aasProposal",
                mapper.writeValueAsString(msg),
                msg.getFrame().getReplyBy().intValue());
        Message message = Message.createByJson(resMsgJson);
        boolean canOffer = message.getFrame().getType().equals(MessageType.Offer);
        if (canOffer) {//如果接受到的类型是offer，那么就放入context的hashMap等待进行评估
            context.addOffer(System.currentTimeMillis(), new Offer().setUrl(url).setOfferMsg(message));
        }
        //如果不是offer类型，就什么也不干
    }
}
