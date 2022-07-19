package com.imc.siemens_aas.i4_0.statemachine.providerjudge;

import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.aasenv.submodel.Submodel;
import com.imc.siemens_aas.aasenv.submodel.metamodel.metavalue.OperationVar;
import com.imc.siemens_aas.aasenv.submodel.submodelelement.Operation;
import com.imc.siemens_aas.i4_0.message.interactionElement.InteractionElement;
import com.imc.siemens_aas.i4_0.message.interactionElement.submodel.InteractionOperation;

import java.util.*;

public class SampleProJudgeStrategy extends ProJudgeStrategy {
    /**
     * 找到aasEnv中的submodel中的submodelElements中的Operation类型的节点，判断其idShort是否为interactionElements中的idShort
     * 并且将InteractionElement与Submodel中的Operation对应起来，作为结构体返回，给后面的AAS调用
     * @param aasEnv
     * @param interactionElements
     * @return
     */
    @Override
    public ProJdResult judge(AasEnv aasEnv, List<InteractionElement> interactionElements) {
        ProJdResult res = new ProJdResult();
        LinkedList<InteractionOperation> operations = new LinkedList<>();

        //用hashMap去降低时间复杂度，这里不直接用两层遍历去做
        HashMap<String, InteractionElement> interactions = new HashMap<>();
        for (InteractionElement interactionElement : interactionElements) {
            String idShort = interactionElement.getIdShort();
            interactions.put(idShort, interactionElement);
        }
        int fitCount = 0;//用来记录满足interactionElements条件的个数，如果等于InteractionElement的个数，就返回true

        //拿到aasEnv中所有的Submodel与对应的Operation节点进行判断
        HashMap<Submodel, HashMap<String, Operation>> submodelOperationMap = aasEnv.getOperations();

        //遍历interactions，对比其中的key，即idShort
        Set<Map.Entry<String, InteractionElement>> interactionSet = interactions.entrySet();
        Iterator<Map.Entry<String, InteractionElement>> it = interactionSet.iterator();
        while (it.hasNext()) {
            Map.Entry<String, InteractionElement> interactionEntry = it.next();

            //遍历submodelOperationMap，对比submodel中的opreation的idShort，构建operations
            Set<Map.Entry<Submodel, HashMap<String, Operation>>> submodelOperationSet = submodelOperationMap.entrySet();
            Iterator<Map.Entry<Submodel, HashMap<String, Operation>>> iterator = submodelOperationSet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<Submodel, HashMap<String, Operation>> submodelOperationEntry = iterator.next();
                HashMap<String, Operation> operationMap = submodelOperationEntry.getValue();
                if (operationMap.containsKey(interactionEntry.getKey())) {//判断key是否符合
                    List<OperationVar> inputVars = interactionEntry.getValue().getInputVariable();
                    //判断inputVariable的输入参数与Operation中的参数是否匹配
                    Operation operation = operationMap.get(interactionEntry.getKey());
                    boolean isMatched = operation.inputParaMatcher(inputVars);
                    if (isMatched) {
                        operations.add(
                                new InteractionOperation(interactionEntry.getValue(), submodelOperationEntry.getKey(), operation));
                        fitCount++;
                        break;
                    }
                }
            }
        }

        //根据fitCount的值判断是否满足交互需求，并设置相应的返回值
        if (fitCount == interactionElements.size()) {//如果刚好满足所有的交互需求
            res.setOK(true);
            res.setOperations(operations);
        } else {//如果不满足条件
            res.setOK(false);//把ok状态设为false
            res.setOperations(null);//把返回的对应关系Map清空
        }
        return res;
    }
}
