package com.imc.siemens_aas.i4_0.message.interactionElement;

import com.imc.siemens_aas.aasenv.common.Description;
import com.imc.siemens_aas.aasenv.common.ModelType;
import com.imc.siemens_aas.aasenv.submodel.metamodel.SemanticId;
import com.imc.siemens_aas.aasenv.submodel.metamodel.metavalue.OperationVar;
import lombok.Data;

import java.util.List;

@Data
public class InteractionElement {
    private SemanticId semanticId;
    private String idShort;
    private String category;
    private ModelType modelType;
    private List<OperationVar> inputVariable;
    private String kind;
    private List<Description> descriptions;
}
