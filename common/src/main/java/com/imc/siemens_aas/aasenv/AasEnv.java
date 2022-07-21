package com.imc.siemens_aas.aasenv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imc.siemens_aas.aasenv.aaset.Asset;
import com.imc.siemens_aas.aasenv.assetAdministrationShell.AssetAdministrationShell;
import com.imc.siemens_aas.aasenv.conceptDescription.ConceptDescription;
import com.imc.siemens_aas.aasenv.submodel.ModelObject;
import com.imc.siemens_aas.aasenv.submodel.Submodel;
import com.imc.siemens_aas.aasenv.submodel.metamodel.metavalue.OperationVar;
import com.imc.siemens_aas.aasenv.submodel.submodelelement.Operation;
import com.imc.siemens_aas.i4_0.message.interactionElement.InteractionElement;
import com.imc.siemens_aas.i4_0.message.interactionElement.submodel.InteractionOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

@Data
@Slf4j
public class AasEnv {
    private List<AssetAdministrationShell> assetAdministrationShells;
    private List<Asset> assets;
    private List<Submodel> submodels;
    private List<ConceptDescription> conceptDescriptions;

    /**
     * 刷新对应submodel的值
     *
     * @param submodelList
     */
    public AasEnv refreshSubmodels(List<Submodel> submodelList) throws JsonProcessingException {
        for (Submodel submodelRefresh : submodelList) {
            String idShort = submodelRefresh.getIdShort();
            for (int j = 0; j < submodels.size(); j++) {
                Submodel submodel = submodels.get(j);
                if (submodel.getIdShort().equals(idShort)) {
                    submodels.set(j, submodelRefresh);
                }
            }
        }
        return this;
    }

    /**
     * 根据judge的结果，进行方法的调用，这里暂时设置成顺序调用
     * TODO 顺序调用替换成别的调用？？？
     *
     * @param operations
     */
    public void doService(List<InteractionOperation> operations) throws InvocationTargetException, IllegalAccessException {
        //顺序调用
        for (InteractionOperation interactionOperation : operations) {
            InteractionElement interaction = interactionOperation.getInteraction();
            Submodel submodel = interactionOperation.getSubmodel();
            List<OperationVar> inputVariable = interaction.getInputVariable();
            //将参数的值转换成Object数组，给反射调用
            Object[] methodParas = new Object[inputVariable.size()];
            for (int i = 0; i < inputVariable.size(); i++) {
                Object value = inputVariable.get(i).getValue().getSubmodelElement().getValue();
                    methodParas[i] = value;
            }

            //拿到Submodel中包装的那个JAVA类
            ModelObject modelObject = submodel.getModelObject();
            Class<? extends ModelObject> clazz = modelObject.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(interaction.getIdShort())) {
                    method.setAccessible(true);
                    method.invoke(modelObject, methodParas);
                    break;
                }
            }
        }
    }

    /**
     * 获取AAS中所有的Operation
     * @return
     */
    public HashMap<Submodel, HashMap<String, Operation>> operations() {
        HashMap<Submodel, HashMap<String, Operation>> res = new HashMap<>();
        for (Submodel submodel : submodels) {
            HashMap<String, Operation> operations = submodel.operations();
            if (operations.size() > 0) {//表明Submodel里面有Operation节点
                res.put(submodel, operations);
            }
        }
        return res;
    }
}