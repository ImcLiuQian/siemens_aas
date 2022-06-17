package com.imc.siemens_aas.aasenv.assetAdministrationShell;

import com.imc.siemens_aas.aasenv.assetAdministrationShell.metaaas.AssetBrief;
import com.imc.siemens_aas.aasenv.assetAdministrationShell.metaaas.SubmodelBrief;
import com.imc.siemens_aas.aasenv.common.Identification;
import com.imc.siemens_aas.aasenv.common.ModelType;
import lombok.Data;

import java.util.List;

@Data
public class AssetAdministrationShell {
    private AssetBrief asset;
    private List<SubmodelBrief> submodels;
    private Identification identification;
    private String idShort;
    private ModelType modelType;
}
