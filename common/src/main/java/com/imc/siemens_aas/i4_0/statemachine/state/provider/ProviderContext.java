package com.imc.siemens_aas.i4_0.statemachine.state.provider;

import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.statemachine.providerjudge.ProJdResult;
import com.imc.siemens_aas.i4_0.statemachine.providerjudge.ProJudgeStrategy;

public interface ProviderContext {
    void changeState(ProviderState state);
    ProviderState getState();
    void handle(Message message);
    void setResult(ProJdResult result);
    ProJdResult getResult();
    ProJudgeStrategy getStrategy();
    AasEnv getAasEnv();
    void setResMsg(Message resMsg);
    void setErrOccur(Boolean errOccur);
    Boolean getErrOccur();
    String getReqUrl();
    void setReqUrl(String reqUrl);
}
