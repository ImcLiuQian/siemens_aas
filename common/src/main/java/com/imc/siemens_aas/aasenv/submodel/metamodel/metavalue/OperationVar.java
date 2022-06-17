package com.imc.siemens_aas.aasenv.submodel.metamodel.metavalue;

import com.imc.siemens_aas.aasenv.common.ModelType;
import lombok.Data;

@Data
public class OperationVar {
    private OperationVarElement value;
    private ModelType modelType;
}
