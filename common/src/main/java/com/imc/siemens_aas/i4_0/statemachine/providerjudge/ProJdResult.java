package com.imc.siemens_aas.i4_0.statemachine.providerjudge;

import com.imc.siemens_aas.i4_0.message.interactionElement.submodel.InteractionOperation;
import lombok.Data;

import java.util.List;

/**
 * provider judge result服务提供者评估结果
 */
@Data
public class ProJdResult {
    //是否满足AAS的要求
    private boolean isOK;
    //返回的InteractionElement与Submodel中Operation的对应关系
    private List<InteractionOperation> operations;
}
