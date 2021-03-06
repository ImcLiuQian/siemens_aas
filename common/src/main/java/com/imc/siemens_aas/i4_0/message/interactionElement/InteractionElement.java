package com.imc.siemens_aas.i4_0.message.interactionElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imc.siemens_aas.aasenv.common.Description;
import com.imc.siemens_aas.aasenv.common.ModelType;
import com.imc.siemens_aas.aasenv.submodel.metamodel.SemanticId;
import com.imc.siemens_aas.aasenv.submodel.metamodel.metavalue.OperationVar;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class InteractionElement {
    private SemanticId semanticId;
    private String idShort;
    private String category;
    private ModelType modelType;
    private List<OperationVar> inputVariable;
    private String kind;
    private List<Description> descriptions;
}
