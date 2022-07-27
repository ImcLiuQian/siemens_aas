package com.imc.siemens_aas.aasenv.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.aasenv.aaset.Asset;
import com.imc.siemens_aas.aasenv.assetAdministrationShell.AssetAdministrationShell;
import com.imc.siemens_aas.aasenv.conceptDescription.ConceptDescription;
import com.imc.siemens_aas.aasenv.submodel.Submodel;
import com.imc.siemens_aas.aasenv.submodel.SubmodelFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class AasFactory {

    /**
     * 对Submodel的属性和方法进行初始化
     */
    public static AasEnv createByFile(String filePath) throws IOException {
        char[] jsonContent = new char[65535];
        FileReader reader = new FileReader(filePath);
        reader.read(jsonContent);
        AasEnv aasEnv = createByJson(new String(jsonContent));
        //TODO 注册到注册中心
        return aasEnv;
    }

    /**
     * 将Json转换为AASEnv，因为Submodel需要设置type，所以这里分开转换
     * @param aasJson
     * @return
     */
    public static AasEnv createByJson(String aasJson) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        AasEnv aasEnv = new AasEnv();
        try {
            ObjectNode rootNode = (ObjectNode) mapper.readTree(aasJson);

            JsonNode aasJsonNode = rootNode.path("assetAdministrationShells");
            List<AssetAdministrationShell> assetAdministrationShells =
                    mapper.convertValue(aasJsonNode, new TypeReference<List<AssetAdministrationShell>>() {});
            aasEnv.setAssetAdministrationShells(assetAdministrationShells);

            JsonNode aasetNode = rootNode.path("assets");
            List<Asset> assets =
                    mapper.convertValue(aasetNode, new TypeReference<List<Asset>>() {});
            aasEnv.setAssets(assets);

            List<Submodel> submodels = new LinkedList<>();
            JsonNode submodelNodes = rootNode.path("submodels");
            for (JsonNode submodelNode : submodelNodes) {
                String submodelJson = mapper.writeValueAsString(submodelNode);
                Submodel submodel = SubmodelFactory.createByJson(submodelJson);
                submodels.add(submodel);
            }
            aasEnv.setSubmodels(submodels);

            JsonNode conDesNode = rootNode.path("conceptDescriptions");
            List<ConceptDescription> conceptDescriptions =
                    mapper.convertValue(conDesNode, new TypeReference<List<ConceptDescription>>() {});
            aasEnv.setConceptDescriptions(conceptDescriptions);
        } catch (JsonProcessingException e) {
            log.error("aasEnv Json 转换失败");
            throw new RuntimeException(e);
        }
        return aasEnv;
    }
}
