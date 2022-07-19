package common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imc.siemens_aas.aasenv.submodel.Submodel;
import com.imc.siemens_aas.i4_0.message.interactionElement.InteractionElement;
import com.imc.siemens_aas.utils.TypeUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

public class test {
    @Test
    void test1() {
        String aaa = "!";
        boolean primitive = TypeUtils.isPrimitive(aaa.getClass());
        System.out.println("primitive = " + primitive);
    }

    @Test
    void test2() throws NoSuchFieldException {//测试字段类型判断
        Class<Submodel> submodelClass = Submodel.class;
        Field semanticId = submodelClass.getDeclaredField("semanticId");
        Class<?> type = semanticId.getType();
        String s = type.toString();
        System.out.println("s = " + s);

        Field keys = type.getDeclaredField("keys");
        String s1 = keys.getType().toString();
        System.out.println("s1 = " + s1);
    }

    @Test
    void test3() throws NoSuchFieldException {//测试字段类型判断
        Object a = 'a';
        Class<?> aClass = a.getClass();
        System.out.println("aClass = " + aClass);
    }

    String IEListJson = "{\n" +
            "    [\n" +
            "        {\n" +
            "            \"semanticId\": {\n" +
            "                \"keys\": []\n" +
            "            },\n" +
            "            \"idShort\": \"CNC1_ChooseProduct\",\n" +
            "            \"category\": null,\n" +
            "            \"modelType\": {\n" +
            "                \"name\": \"Operation\"\n" +
            "            },\n" +
            "            \"inputVariable\": [\n" +
            "                {\n" +
            "                    \"value\": {\n" +
            "                        \"submodelElement\": {\n" +
            "                            \"value\": \"2\",\n" +
            "                            \"valueId\": null,\n" +
            "                            \"semanticId\": {\n" +
            "                                \"keys\": []\n" +
            "                            },\n" +
            "                            \"idShort\": \"color\",\n" +
            "                            \"category\": \"VARIABLE\",\n" +
            "                            \"modelType\": {\n" +
            "                                \"name\": \"Property\"\n" +
            "                            },\n" +
            "                            \"valueType\": {\n" +
            "                                \"dataObjectType\": {\n" +
            "                                    \"name\": \"int\"\n" +
            "                                }\n" +
            "                            },\n" +
            "                            \"kind\": \"Instance\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    \"modelType\": {\n" +
            "                        \"name\": \"OperationVariable\"\n" +
            "                    }\n" +
            "                },\n" +
            "                {\n" +
            "                    \"value\": {\n" +
            "                        \"submodelElement\": {\n" +
            "                            \"value\": \"false\",\n" +
            "                            \"valueId\": null,\n" +
            "                            \"semanticId\": {\n" +
            "                                \"keys\": []\n" +
            "                            },\n" +
            "                            \"idShort\": \"type\",\n" +
            "                            \"category\": \"VARIABLE\",\n" +
            "                            \"modelType\": {\n" +
            "                                \"name\": \"Property\"\n" +
            "                            },\n" +
            "                            \"valueType\": {\n" +
            "                                \"dataObjectType\": {\n" +
            "                                    \"name\": \"boolean\"\n" +
            "                                }\n" +
            "                            },\n" +
            "                            \"kind\": \"Instance\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    \"modelType\": {\n" +
            "                        \"name\": \"OperationVariable\"\n" +
            "                    }\n" +
            "                }\n" +
            "            ],\n" +
            "            \"kind\": \"Instance\",\n" +
            "            \"descriptions\": null\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    @Test
    void test4() throws JsonProcessingException {//createIEListbyJson
//        List<InteractionElement> ieListbyJson = InteractionElement.createIEListbyJson(IEListJson);
//        String s = new ObjectMapper().writeValueAsString(ieListbyJson);
//        System.out.println(s);
    }
}
