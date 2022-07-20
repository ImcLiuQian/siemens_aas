package com.imc.siemens_aas.i4_0.statemachine;

import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.statemachine.providerjudge.ProJdResult;
import com.imc.siemens_aas.i4_0.statemachine.providerjudge.ProJudgeStrategy;
import com.imc.siemens_aas.i4_0.statemachine.state.provider.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 服务提供者状态机，使用之前需要使用init方法绑定AAS和参数评估策略
 */
@Controller
@RequestMapping("aas/i4.0/provider")
public class ProviderStateMach implements ProviderContext {

    private AasEnv aasEnv;
    private ProJudgeStrategy judgeStrategy;
    private ProJdResult result;//状态机评估是否满足调用者的需求之后，产生的结果
    private Message resMsg;//需要返回的消息
    private ProviderState state = ProposalWaiting.getInstance();//当前状态机的状态，初始状态为等待Proposal状态

    public void init(AasEnv aasEnv, ProJudgeStrategy judgeStrategy) {
        this.aasEnv = aasEnv;
        this.judgeStrategy = judgeStrategy;
    }

    @PostMapping("aasProposal")
    public ResponseEntity pHandle(@RequestBody String msgJson) {
        Message message = Message.createByJson(msgJson);
        if (state != ProposalWaiting.getInstance()) {
            return ResponseEntity.ok("service is already in use");
        }
        handle(message);
        if (resMsg != null) {
            return ResponseEntity.ok(resMsg);
        } else {
            return ResponseEntity.ok("");
        }
    }

    @PostMapping("aasOfferReply")
    public ResponseEntity oRHandle(@RequestBody String msgJson) {
        Message message = Message.createByJson(msgJson);
        if (state != OfferResWaiting.getInstance()) {
            return ResponseEntity.ok("service is already in use");
        }
        handle(message);
        if (resMsg != null) {
            return ResponseEntity.ok(resMsg);
        } else {
            return ResponseEntity.ok("");
        }
    }

    @Override
    public void changeState(ProviderState state) {
        this.state = state;
    }

    @Override
    public ProviderState getState() {
        return this.state;
    }

    @Override
    public void handle(Message message) {
        this.state.doExecute(this, message);
    }

    @Override
    public void setResult(ProJdResult result) {
        this.result = result;
    }

    @Override
    public ProJdResult getResult() {
        return this.result;
    }

    @Override
    public ProJudgeStrategy getStrategy() {
        return this.judgeStrategy;
    }

    @Override
    public AasEnv getAasEnv() {
        return this.aasEnv;
    }

    @Override
    public void setResMsg(Message resMsg) {
        this.resMsg = resMsg;
    }
}