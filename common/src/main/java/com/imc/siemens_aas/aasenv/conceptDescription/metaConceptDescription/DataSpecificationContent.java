package com.imc.siemens_aas.aasenv.conceptDescription.metaConceptDescription;

import com.imc.siemens_aas.aasenv.common.Description;
import lombok.Data;

import java.util.List;

@Data
public class DataSpecificationContent {
    private List<Description> preferredName;
    private String unit;
    private String dataType;
}
