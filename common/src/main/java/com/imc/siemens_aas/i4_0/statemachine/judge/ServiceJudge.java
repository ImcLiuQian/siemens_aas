package com.imc.siemens_aas.i4_0.statemachine.judge;

import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.i4_0.message.interactionElement.InteractionElement;

import java.util.List;

public class ServiceJudge {
    private AasEnv aasEnv;
    private List<InteractionElement> interactionElements;
    private JudgeStrategy judgeStrategy;

    public ServiceJudge(AasEnv aasEnv, List<InteractionElement> interactionElements) {
        this.aasEnv = aasEnv;
        this.interactionElements = interactionElements;
    }

    public ServiceJudge setJudgeStrategy(JudgeStrategy judgeStrategy) {
        this.judgeStrategy = judgeStrategy;
        return this;
    }

    /**
     * 判断AAS能否满足需求，即有没有message传过来的idShort对应的方法，以及传入的参数类型是否正确
     * 参数，待添加，Submodel
     * @return
     */
    public boolean isOK() {
        return judgeStrategy.judge(aasEnv, interactionElements);
    }
}
