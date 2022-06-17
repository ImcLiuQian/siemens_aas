package com.imc.siemens_aas.aasenv.conceptDescription;

import com.imc.siemens_aas.aasenv.common.Identification;
import com.imc.siemens_aas.aasenv.common.ModelType;
import com.imc.siemens_aas.aasenv.conceptDescription.metaConceptDescription.EmbeddedDataSpecification;
import lombok.Data;

import java.util.List;

@Data
public class ConceptDescription {
    private Identification identification;
    private String idShort;
    private String category;
    private ModelType modelType;
    private List<EmbeddedDataSpecification> embeddedDataSpecifications;
}
