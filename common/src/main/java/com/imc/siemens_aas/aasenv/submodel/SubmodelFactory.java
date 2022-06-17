package com.imc.siemens_aas.aasenv.submodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SubmodelFactory {

    //用来管理submodel和对应的url
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
                eleJsonConvert(submodelElement);
            }
            Submodel submodel = mapper.convertValue(rootNode, Submodel.class);
            return submodel;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("submodelJson解析错误");
        }
    }

    private static void eleJsonConvert(JsonNode submodelElement) {
        JsonNode type = submodelElement.path("modelType").path("name");
        //1.设置submodelElement属性
        ((ObjectNode) submodelElement).set("type", type);
        //2.如果是Property，那么可以什么都不用做
        //3.如果是Operation，那么还需要设置里面的Property，也是增加一个type
        String typeName = type.asText();
        if (typeName.equals("Operation")) {
            //inputVariable
            JsonNode inputVariable = submodelElement.path("inputVariable");
            if (!inputVariable.isEmpty()) {//如果inputVariable中有数据
                for (JsonNode jsonNode : inputVariable) {
                    JsonNode elementNode = jsonNode.path("value").path("submodelElement");
                    JsonNode elementType = elementNode.path("modelType").path("name");
                    ((ObjectNode) elementNode).set("type", elementType);
                }
            }
            //outputVariable
            JsonNode outputVariable = submodelElement.path("outputVariable");
            if (!outputVariable.isEmpty()) {//如果outputVariable中有数据
                for (JsonNode jsonNode : outputVariable) {
                    JsonNode elementNode = jsonNode.path("value").path("submodelElement");
                    JsonNode elementType = elementNode.path("modelType").path("name");
                    ((ObjectNode) elementNode).set("type", elementType);
                }
            }
            //inoutputVariable
            JsonNode inoutputVariable = submodelElement.path("inoutputVariable");
            if (!inoutputVariable.isEmpty()) {//如果inoutputVariable中有数据
                for (JsonNode jsonNode : inoutputVariable) {
                    JsonNode elementNode = jsonNode.path("value").path("submodelElement");
                    JsonNode elementType = elementNode.path("modelType").path("name");
                    ((ObjectNode) elementNode).set("type", elementType);
                }
            }
        }
        //4.如果是SubmodelElementCollection，则继续向下遍历，直到其中没有SubmodelElementCollection
        if (typeName.equals("SubmodelElementCollection")) {
            JsonNode value = submodelElement.path("value");
            for (JsonNode jsonNode : value) {
                //递归调用
                eleJsonConvert(jsonNode);
            }
        }
    }

    //TODO 用正则表达式把序列化时的 "type": "SubmodelElementCollection" 的这种字段去掉
    //TODO 或者直接用JACKSON工具实现
}
