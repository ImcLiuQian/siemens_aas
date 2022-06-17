package com.imc.siemens_aas.aasenv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imc.siemens_aas.aasenv.aaset.Asset;
import com.imc.siemens_aas.aasenv.assetAdministrationShell.AssetAdministrationShell;
import com.imc.siemens_aas.aasenv.conceptDescription.ConceptDescription;
import com.imc.siemens_aas.aasenv.submodel.Submodel;
import com.imc.siemens_aas.i4_0.message.interactionElement.InteractionElement;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class AasEnv {
    private List<AssetAdministrationShell> assetAdministrationShells;
    private List<Asset> assets;
    private List<Submodel> submodels;
    private List<ConceptDescription> conceptDescriptions;

    /**
     * 刷新对应submodel的值
     * @param submodelList
     */
    public AasEnv refreshSubmodels(List<Submodel> submodelList) throws JsonProcessingException {
        for (Submodel submodelRefresh : submodelList) {
            String idShort = submodelRefresh.getIdShort();
            for (int j = 0; j < submodels.size(); j++) {
                Submodel submodel = submodels.get(j);
                if (submodel.getIdShort().equals(idShort)) {
                    submodels.set(j, submodelRefresh);
                }
            }
        }
        return this;
    }

    /**
     * 接收i4.0消息，将其交给内部的submodel处理
     * @param interactionElements
     */
    public void doService(List<InteractionElement> interactionElements) {

    }
}