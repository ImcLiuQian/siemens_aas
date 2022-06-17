package com.imc.siemens_aas.aasenv.conceptDescription.metaConceptDescription;

import lombok.Data;

@Data
public class EmbeddedDataSpecification {
    private DataSpecification dataSpecification;
    private DataSpecificationContent dataSpecificationContent;
}
