package com.imc.siemens_aas.i4_0.statemachine.requesterjudge;

import com.imc.siemens_aas.i4_0.statemachine.state.requester.Offer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务请求者决定是否接受的状态评估策略
 */
public abstract class ReqJudgeStrategy {
    /**
     * 判断是否接受AAS的服务
     * @return
     */
    public abstract ConcurrentHashMap<Offer, Boolean> judge(ConcurrentHashMap<Long, Offer> offers);
}
