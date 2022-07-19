package com.imc.siemens_aas.i4_0.statemachine.requesterjudge;

import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.statemachine.state.requester.Offer;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 最简的接受策略，按照消息的先后顺序来选择接受，选择第一个回复offer的aas
 */
public class SampleReqJudgeStrategy extends ReqJudgeStrategy{

    @Override
    public ConcurrentHashMap<Offer, Boolean> judge(ConcurrentHashMap<Long, Offer> offers) {
        Long minTime = Collections.min(offers.keySet());//拿到第一个提供offer的Key
        ConcurrentHashMap<Offer, Boolean> result = new ConcurrentHashMap<>();
        //构建返回结果，把接受的Offer对应的状态设置为true，其他全部设置为false
        Iterator<Map.Entry<Long, Offer>> iterator = offers.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Offer> offer = iterator.next();
            if (offer.getKey().equals(minTime)) {
                result.put(offer.getValue(), true);
            } else {
                result.put(offer.getValue(), false);
            }

        }
        return result;
    }
}
