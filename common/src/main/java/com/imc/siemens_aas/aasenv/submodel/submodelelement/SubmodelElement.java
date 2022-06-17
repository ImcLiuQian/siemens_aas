package com.imc.siemens_aas.aasenv.submodel.submodelelement;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.imc.siemens_aas.aasenv.common.Description;
import com.imc.siemens_aas.aasenv.common.ModelType;
import com.imc.siemens_aas.aasenv.submodel.metamodel.SemanticId;
import lombok.Data;

import java.util.List;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property ="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SubmodelElementCollection.class, name ="SubmodelElementCollection"),
        @JsonSubTypes.Type(value = Property.class, name ="Property"),
        @JsonSubTypes.Type(value = Operation.class, name ="Operation")
})
public abstract class SubmodelElement {
    private SemanticId semanticId;
    private String idShort;
    private String category;
    private ModelType modelType;
    private String kind;
    private List<Description> descriptions;
}
