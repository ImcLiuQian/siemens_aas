package com.imc.siemens_aas.aasenv.submodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.imc.siemens_aas.aasenv.submodel.submodelelement.SubmodelElement;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SubmodelFactory {

    /**
     * 用来管理submodel和对应的url
     */
    private HashMap<Submodel, String> ricUrls = new HashMap<>();

    /**
     * 将submodel注册到注册中心
     * @param submodel
     */
    public static void registry(Submodel submodel) {

    }

    /**
     * 对Submodel的属性和方法进行初始化
     */
    public static Submodel createByJson(String submodelJson) {
        Submodel submodel = jsonToSubmodel(submodelJson);
        //TODO 注册到注册中心
        return submodel;
    }

    /**
     * 对Submodel的属性和方法进行初始化
     */
    public static Submodel createByFile(String filePath) throws IOException {
        char[] jsonContent = new char[65535];
        FileReader reader = new FileReader(filePath);
        reader.read(jsonContent);
        Submodel submodel = SubmodelFactory.createByJson(new String(jsonContent));
        //TODO 注册到注册中心
        return submodel;
    }

    /**
     * 将json转换为Submodel
     *
     * @param submodelJson
     * @return
     */
    private static Submodel jsonToSubmodel(String submodelJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            ObjectNode rootNode = (ObjectNode) mapper.readTree(submodelJson);
            JsonNode submodelElements = rootNode.path("submodelElements");
            for (JsonNode submodelElement : submodelElements) {
                //调用方法来操作submodelElement
//                eleJsonConvert(submodelElement);
                SubmodelElement.addTypeInJson(submodelElement);
            }
            Submodel submodel = mapper.convertValue(rootNode, Submodel.class);
            return submodel;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("submodelJson解析错误");
        }
    }
}
