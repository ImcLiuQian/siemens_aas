package com.imc.siemens_aas.i4_0.statemachine;

import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.statemachine.requesterjudge.ReqJudgeStrategy;
import com.imc.siemens_aas.i4_0.statemachine.state.requester.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;
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
    //上一次调用时的消息，记录下来，如果服务提供方出现Error了，就重新发送此批次消息，通过conversionID来区分是否为一批
    private static List<Message> lastMsgs = new LinkedList<>();
    private static List<String> errorAasIds = new LinkedList<>();//用于故障的AAS服务的id

    public void init(ReqJudgeStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 服务调用程序的入口
     */
    @PostMapping("aasProposal")
    public ResponseEntity callForProposal(@RequestBody String msgJson) {
        Message message = Message.createByJson(msgJson);
        if (state != CFPSendingWaiting.getInstance()) {
            return ResponseEntity.ok("service is already in use");
        }
        //如果message的conversionID和list已有的不一样，说明是新开的一次会话，那么把lastMsgs清空
        if (lastMsgs.size() > 0 && !message.getFrame().getConversationId().equals(lastMsgs.get(0).getFrame().getConversationId())) {
            lastMsgs.clear();
        }
        lastMsgs.add(message);
        handle(message);
        return ResponseEntity.ok("");
    }

    /**
     * 响应被调用方的服务错误消息
     * @return
     */
    @PostMapping("providerError")
    public ResponseEntity providerError(@RequestBody(required = false) String msgJson, @RequestParam String aasIdShort) {
        this.errorAasIds.add(aasIdShort);//把错误的AAS的id放入errorAasIds中
        if (lastMsgs.size() == 0) {//如果从未被调用过，lastMsgs就没有命令，就什么也不用干
            return ResponseEntity.ok("");
        }
        //如果没有带I4.0消息，就不进行转换
        Message message = null;
        if (msgJson != null) {
            message = Message.createByJson(msgJson);
        }
        this.changeState(ErrorHandling.getInstance());
        handle(message);
        return ResponseEntity.ok("");
    }

    @Override
    public RequesterState getState() {
        return state;
    }

    @Override
    public void changeState(RequesterState state) {
        //为了配合前端进行状态机的可视化，这里睡眠1s来延长每个状态的时间
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.state = state;
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

    @Override
    public List<Message> getLastMsgs() {
        return lastMsgs;
    }

    @Override
    public List<String> getErrAasIds() {
        return errorAasIds;
    }
}
