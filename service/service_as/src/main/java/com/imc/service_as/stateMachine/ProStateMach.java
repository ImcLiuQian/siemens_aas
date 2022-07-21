package com.imc.service_as.stateMachine;

import com.imc.service_as.entity_submodel.AsDataBase;
import com.imc.siemens_aas.i4_0.statemachine.ProviderStateMach;
import com.imc.siemens_aas.i4_0.statemachine.providerjudge.SampleProJudgeStrategy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProStateMach implements InitializingBean {
    @Autowired
    private ProviderStateMach stateMach;

    @Override
    public void afterPropertiesSet() throws Exception {
        stateMach.init(AsDataBase.AS, SampleProJudgeStrategy.getInstance());
    }
}
