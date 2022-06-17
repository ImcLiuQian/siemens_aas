package com.imc.siemens_aas.i4_0.statemachine.judge;

import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.aasenv.submodel.Submodel;
import com.imc.siemens_aas.aasenv.submodel.submodelelement.ElementType;
import com.imc.siemens_aas.aasenv.submodel.submodelelement.SubmodelElement;
import com.imc.siemens_aas.i4_0.message.interactionElement.InteractionElement;

import java.util.HashMap;
import java.util.List;

public class SampleJudgeStrategy extends JudgeStrategy{
    /**
     * 找到aasEnv中的submodel中的submodelElements中的Operation类型的节点，判断其idShort是否为interactionElements中的idShort
     * @param aasEnv
     * @param interactionElements
     * @return
     */
    @Override
    public boolean judge(AasEnv aasEnv, List<InteractionElement> interactionElements) {
        List<Submodel> submodels = aasEnv.getSubmodels();
        //用hashMap去降低时间复杂度，这里不直接用两层遍历去做
        HashMap<Object, Object> interactions = new HashMap<>();
        for (InteractionElement interactionElement : interactionElements) {
            String idShort = interactionElement.getIdShort();
            interactions.put(idShort, interactionElement);
        }
        //匹配idShort
        for (Submodel submodel : submodels) {
            //idShort匹配上之后，匹配modelType，以及inputVariable的参数类型
            List<SubmodelElement> submodelElements = submodel.getSubmodelElements();
            for (SubmodelElement submodelElement : submodelElements) {
                String elementType = submodelElement.getModelType().getName();
                //如果是Property类型，什么都不用干
                //如果是Operation类型，进行idShort类型的判断
                if (elementType.equals(ElementType.Operation)) {

                }
                //如果是SubmodelElementCollection类型，继续查找下一层

            }
        }


        //设置，或者返回Submodel的方法的位置信息，以便调用

    }
}
