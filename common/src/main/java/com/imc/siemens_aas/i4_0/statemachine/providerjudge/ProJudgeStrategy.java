package com.imc.siemens_aas.i4_0.statemachine.providerjudge;

import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.i4_0.message.interactionElement.InteractionElement;

import java.util.List;

public abstract class ProJudgeStrategy {
    /**
     * 判断aasEnv能否处理interactionElements的内容
     * @param aasEnv
     * @param interactionElements
     * @return
     */
    public abstract ProJdResult judge(AasEnv aasEnv, List<InteractionElement> interactionElements);
}
