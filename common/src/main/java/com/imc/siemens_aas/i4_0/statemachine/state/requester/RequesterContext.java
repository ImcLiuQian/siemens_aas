package com.imc.siemens_aas.i4_0.statemachine.state.requester;

import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.statemachine.requesterjudge.ReqJudgeStrategy;
import com.imc.siemens_aas.i4_0.statemachine.state.provider.ProviderState;

import java.util.concurrent.ConcurrentHashMap;

public interface RequesterContext {
    ProviderState getState();
    void changeState(RequesterState state);
    void handle(Message message);
    String getRicUrl();
    ReqJudgeStrategy getStrategy();
    ConcurrentHashMap<Long, Offer> getOffers();
    void addOffer(Long time, Offer offer);
}
