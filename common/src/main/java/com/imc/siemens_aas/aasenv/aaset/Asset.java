package com.imc.siemens_aas.aasenv.aaset;

import com.imc.siemens_aas.aasenv.common.Description;
import com.imc.siemens_aas.aasenv.common.Identification;
import com.imc.siemens_aas.aasenv.common.ModelType;
import lombok.Data;

import java.util.List;

@Data
public class Asset {
    private Identification identification;
    private String idShort;
    private ModelType modelType;
    private String kind;
    private List<Description> descriptions;
}
