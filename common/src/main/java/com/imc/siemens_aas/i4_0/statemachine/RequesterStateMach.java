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
     * @param message 前端发送消息也以message的形式发送
     * @return
     */
    @PostMapping("aasProposal")
    public ResponseEntity callForProposal(@RequestBody Message message) {
        if (state != CFPSendingWaiting.getInstance()) {
            return ResponseEntity.ok("service is already in use");
        }
        handle(message);
        return ResponseEntity.ok("");
    }

    @Override
    public ProviderState getState() {
        return null;
    }

    @Override
    public void changeState(RequesterState state) {

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
