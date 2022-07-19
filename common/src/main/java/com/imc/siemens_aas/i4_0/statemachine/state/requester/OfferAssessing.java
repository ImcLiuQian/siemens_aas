package com.imc.siemens_aas.i4_0.statemachine.state.requester;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.message.frame.MessageType;
import com.imc.siemens_aas.i4_0.statemachine.requesterjudge.ReqJudgeStrategy;
import com.imc.siemens_aas.utils.HttpClientHelper;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OfferAssessing implements RequesterState {
    private static OfferAssessing singleton = new OfferAssessing();

    public static RequesterState getInstance() {
        return singleton;
    }

    private OfferAssessing() {}

    @Override
    public void doExecute(RequesterContext context, Message msg) {
        //Json序列化工具mapper
        ObjectMapper mapper = new ObjectMapper();
        //对offerMsgs进行评估
        ConcurrentHashMap<Long, Offer> offers = context.getOffers();
        //调用context的strategy进行评估
        ReqJudgeStrategy strategy = context.getStrategy();

        Message offerResponseMsg = null;//AAS执行服务后的结果

        ConcurrentHashMap<Offer, Boolean> results = strategy.judge(context.getOffers());
        //根据结果回复AAS的请求
        Iterator<Map.Entry<Offer, Boolean>> it = results.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Offer, Boolean> next = it.next();
            Offer offer = next.getKey();
            Boolean isSelected = next.getValue();
            Message offerMsg = offer.getOfferMsg();

            try {
                if (isSelected) {//如果是true，说明选择接受此Offer
                    offerMsg.getFrame().setType(MessageType.OfferAcceptance);
                    //切换至等待服务完成状态
                    context.changeState(CompletedWaiting.getInstance());
                    //发送消息
                    String responseMsgJson = HttpClientHelper.doPostByParam(offer.getUrl() + "/aasOfferReply",
                            mapper.writeValueAsString(offerMsg),
                            offerMsg.getFrame().getReplyBy().intValue());
                    offerResponseMsg = mapper.readValue(responseMsgJson, Message.class);
                } else {//如果是false，说明选择不接受此Offer
                    offerMsg.getFrame().setType(MessageType.OfferRejection);
                    //发送消息，如果是OfferRejection，不需要对方回消息
                    HttpClientHelper.doPostByParam(offer.getUrl() + "/aasOfferReply",
                            mapper.writeValueAsString(offerMsg),
                            offerMsg.getFrame().getReplyBy().intValue());
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        //把所有的results处理完之后，把AAS执行服务的结果，交给CompletedWaiting状态去处理
        context.handle(offerResponseMsg);
    }
}
