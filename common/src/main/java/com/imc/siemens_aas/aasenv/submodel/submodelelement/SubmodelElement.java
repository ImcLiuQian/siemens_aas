package com.imc.siemens_aas.aasenv.submodel.submodelelement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    /**
     * 在jsonNode中加入所需要转换的字段
     */
    public static void addTypeInJson(JsonNode submodelElement) {
        JsonNode type = submodelElement.path("modelType").path("name");
        //1.设置submodelElement属性
        ((ObjectNode) submodelElement).set("type", type);
        //2.如果是Property，那么可以什么都不用做
        //3.如果是Operation，那么还需要设置里面的Property，也是增加一个type
        String typeName = type.asText();
        if (typeName.equals("Operation")) {
            //inputVariable
            JsonNode inputVariable = submodelElement.path("inputVariable");
            //如果inputVariable中有数据
            if (!inputVariable.isEmpty()) {
                for (JsonNode jsonNode : inputVariable) {
                    JsonNode elementNode = jsonNode.path("value").path("submodelElement");
                    JsonNode elementType = elementNode.path("modelType").path("name");
                    ((ObjectNode) elementNode).set("type", elementType);
                }
            }
            //outputVariable
            JsonNode outputVariable = submodelElement.path("outputVariable");
            //如果outputVariable中有数据
            if (!outputVariable.isEmpty()) {
                for (JsonNode jsonNode : outputVariable) {
                    JsonNode elementNode = jsonNode.path("value").path("submodelElement");
                    JsonNode elementType = elementNode.path("modelType").path("name");
                    ((ObjectNode) elementNode).set("type", elementType);
                }
            }
            //inoutputVariable
            JsonNode inoutputVariable = submodelElement.path("inoutputVariable");
            //如果inoutputVariable中有数据
            if (!inoutputVariable.isEmpty()) {
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
                addTypeInJson(jsonNode);
            }
        }
    }
}
