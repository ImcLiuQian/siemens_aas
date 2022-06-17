package com.imc.siemens_aas.i4_0.statemachine.judge;

import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.i4_0.message.interactionElement.InteractionElement;

import java.util.List;

public abstract class JudgeStrategy {
    /**
     * 判断aasEnv能否处理interactionElements的内容
     * @param aasEnv
     * @param interactionElements
     * @return
     */
    public abstract boolean judge(AasEnv aasEnv, List<InteractionElement> interactionElements);
}
