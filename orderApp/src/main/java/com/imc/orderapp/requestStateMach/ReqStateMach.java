package com.imc.orderapp.requestStateMach;

import com.imc.siemens_aas.i4_0.statemachine.RequesterStateMach;
import com.imc.siemens_aas.i4_0.statemachine.requesterjudge.SampleReqJudgeStrategy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReqStateMach implements InitializingBean {

    @Autowired
    private RequesterStateMach stateMach;

    @Override
    public void afterPropertiesSet() throws Exception {
        stateMach.init(SampleReqJudgeStrategy.getInstance());
    }
}
