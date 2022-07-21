package com.imc.siemens_aas.i4_0.statemachine;

import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.statemachine.requesterjudge.ReqJudgeStrategy;
import com.imc.siemens_aas.i4_0.statemachine.state.provider.ProviderState;
import com.imc.siemens_aas.i4_0.statemachine.state.requester.CFPSendingWaiting;
import com.imc.siemens_aas.i4_0.statemachine.state.requester.Offer;
import com.imc.siemens_aas.i4_0.statemachine.state.requester.RequesterContext;
import com.imc.siemens_aas.i4_0.statemachine.state.requester.RequesterState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ConcurrentHashMap;


/**
 * 服务请求者状态机，需要使用init函数初始化评估策略
 */
@Slf4j
@Controller
@RequestMapping("aas/i4.0/requester")
public class RequesterStateMach implements RequesterContext {

    @Value("${registry.url:8888}")
    private String ricUrl;

    private RequesterState state = CFPSendingWaiting.getInstance();

    private ReqJudgeStrategy strategy;//offer评估策略
    private static ConcurrentHashMap<Long, Offer> offerMsgs = new ConcurrentHashMap<>();//将收到的offer统计起来进行评估，key是消息到达时间，url是对应的AAS接口

    public void init(ReqJudgeStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 响应前端的按钮请求
     * @return
     */
    @PostMapping("aasProposal")
    public ResponseEntity callForProposal(@RequestBody String msgJson) {
        Message message = Message.createByJson(msgJson);
        if (state != CFPSendingWaiting.getInstance()) {
            return ResponseEntity.ok("service is already in use");
        }
        handle(message);
        return ResponseEntity.ok("");
    }

    @Override
    public RequesterState getState() {
        return state;
    }

    @Override
    public void changeState(RequesterState state) {
        this.state = state;
        //为了配合前端进行状态机的可视化，这里睡眠1s来延长每个状态的时间
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        log.info("切换至{}状态", state.getClass().getSimpleName());
    }

    @Override
    public void handle(Message message) {
        this.state.doExecute(this, message);
    }

    @Override
    public String getRicUrl() {
        return ricUrl;
    }

    @Override
    public ReqJudgeStrategy getStrategy() {
        return strategy;
    }

    @Override
    public ConcurrentHashMap<Long, Offer> getOffers() {
        return offerMsgs;
    }

    @Override
    public void addOffer(Long time, Offer offer) {
        this.offerMsgs.put(time, offer);
    }
}
