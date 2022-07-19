package com.imc.siemens_aas.aasenv.submodel.metamodel.metavalue;

import com.imc.siemens_aas.aasenv.common.ModelType;
import com.imc.siemens_aas.aasenv.submodel.submodelelement.Property;
import lombok.Data;

@Data
public class OperationVar {
    private OperationVarElement value;
    private ModelType modelType;

    /**
     * 判断参数的格式是否相同
     * @param operationVar
     * @return
     */
    public boolean hasSameFormat(OperationVar operationVar) {
        Property varProperty = operationVar.value.getSubmodelElement();
        Property thisVarProperty = this.value.getSubmodelElement();
        if (!varProperty.getIdShort().equals(thisVarProperty.getIdShort())) {
            return false;
        }
        if (!varProperty.getModelType().getName().equals(thisVarProperty.getModelType().getName())) {
            return false;
        }
        if (!varProperty.getValueType().getDataObjectType().getName().equals(thisVarProperty.getValueType().getDataObjectType().getName())) {
            return false;
        }
        return true;
    }
}
