package com.imc.service_as;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imc.service_as.entity_submodel.*;
import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.aasenv.submodel.Submodel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class refreshFieldTest {
    String testStr;
    {
        testStr = "{\n" +
                "  \"semanticId\": {\n" +
                "    \"keys\": []\n" +
                "  },\n" +
                "  \"qualifiers\": [],\n" +
                "  \"hasDataSpecification\": [],\n" +
                "  \"identification\": {\n" +
                "    \"idType\": \"IRI\",\n" +
                "    \"id\": \"https://example.com/ids/sm/2393_8002_5022_4743\"\n" +
                "  },\n" +
                "  \"administration\": null,\n" +
                "  \"idShort\": \"Instance_factoryIO\",\n" +
                "  \"category\": \"CONSTANT\",\n" +
                "  \"modelType\": {\n" +
                "    \"name\": \"Submodel\"\n" +
                "  },\n" +
                "  \"kind\": \"Instance\",\n" +
                "  \"submodelElements\": [\n" +
                "    {\n" +
                "      \"ordered\": false,\n" +
                "      \"allowDuplicates\": false,\n" +
                "      \"semanticId\": {\n" +
                "        \"keys\": []\n" +
                "      },\n" +
                "      \"constraints\": [],\n" +
                "      \"hasDataSpecification\": [],\n" +
                "      \"idShort\": \"AS\",\n" +
                "      \"category\": null,\n" +
                "      \"modelType\": {\n" +
                "        \"name\": \"SubmodelElementCollection\"\n" +
                "      },\n" +
                "      \"value\": [\n" +
                "        {\n" +
                "          \"ordered\": false,\n" +
                "          \"allowDuplicates\": false,\n" +
                "          \"semanticId\": {\n" +
                "            \"keys\": []\n" +
                "          },\n" +
                "          \"constraints\": [],\n" +
                "          \"hasDataSpecification\": [],\n" +
                "          \"idShort\": \"conveyorSpeed\",\n" +
                "          \"category\": null,\n" +
                "          \"modelType\": {\n" +
                "            \"name\": \"SubmodelElementCollection\"\n" +
                "          },\n" +
                "          \"value\": [\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": [\n" +
                "                  {\n" +
                "                    \"type\": \"ConceptDescription\",\n" +
                "                    \"local\": true,\n" +
                "                    \"value\": \"https://example.com/ids/cd/8504_4022_5022_4155\",\n" +
                "                    \"index\": 0,\n" +
                "                    \"idType\": \"IRI\"\n" +
                "                  }\n" +
                "                ]\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"assembleConveyor1\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"double\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"speed of assemble conveyor1\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"装配传送带1的速度\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": [\n" +
                "                  {\n" +
                "                    \"type\": \"ConceptDescription\",\n" +
                "                    \"local\": true,\n" +
                "                    \"value\": \"https://example.com/ids/cd/8504_4022_5022_4155\",\n" +
                "                    \"index\": 0,\n" +
                "                    \"idType\": \"IRI\"\n" +
                "                  }\n" +
                "                ]\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"assembleConveyor2\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"double\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"speed of assemble conveyor2\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"装配传送带2的速度\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ],\n" +
                "          \"kind\": \"Instance\",\n" +
                "          \"descriptions\": null\n" +
                "        },\n" +
                "        {\n" +
                "          \"ordered\": false,\n" +
                "          \"allowDuplicates\": false,\n" +
                "          \"semanticId\": {\n" +
                "            \"keys\": []\n" +
                "          },\n" +
                "          \"constraints\": [],\n" +
                "          \"hasDataSpecification\": [],\n" +
                "          \"idShort\": \"positioner1\",\n" +
                "          \"category\": \"\",\n" +
                "          \"modelType\": {\n" +
                "            \"name\": \"SubmodelElementCollection\"\n" +
                "          },\n" +
                "          \"value\": [\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"Clamp\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"boolean\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"clamp when true; loose when false\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"为真时固定器开始夹紧，为假时固定器松开\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"Clamped\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"boolean\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"true when clamped; else false\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"夹紧后为真，否则为假\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"Raise\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"boolean\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"raise when true; fall when false\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"为真时固定器抬起使产品通过，为假时落下拦住产品\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"Limit\",\n" +
                "              \"category\": null,\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"boolean\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": null\n" +
                "            }\n" +
                "          ],\n" +
                "          \"kind\": \"Instance\",\n" +
                "          \"descriptions\": null\n" +
                "        },\n" +
                "        {\n" +
                "          \"ordered\": false,\n" +
                "          \"allowDuplicates\": false,\n" +
                "          \"semanticId\": {\n" +
                "            \"keys\": []\n" +
                "          },\n" +
                "          \"constraints\": [],\n" +
                "          \"hasDataSpecification\": [],\n" +
                "          \"idShort\": \"positioner2\",\n" +
                "          \"category\": \"\",\n" +
                "          \"modelType\": {\n" +
                "            \"name\": \"SubmodelElementCollection\"\n" +
                "          },\n" +
                "          \"value\": [\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"Clamp\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"boolean\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"clamp when true; loose when false\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"为真时固定器开始夹紧，为假时固定器松开\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"Clamped\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"boolean\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"true when clamped; else false\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"夹紧后为真，否则为假\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"Raise\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"boolean\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"raise when true; fall when false\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"为真时固定器抬起使产品通过，为假时落下拦住产品\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"Limit\",\n" +
                "              \"category\": null,\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"boolean\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": null\n" +
                "            }\n" +
                "          ],\n" +
                "          \"kind\": \"Instance\",\n" +
                "          \"descriptions\": null\n" +
                "        },\n" +
                "        {\n" +
                "          \"ordered\": false,\n" +
                "          \"allowDuplicates\": false,\n" +
                "          \"semanticId\": {\n" +
                "            \"keys\": []\n" +
                "          },\n" +
                "          \"constraints\": [],\n" +
                "          \"hasDataSpecification\": [],\n" +
                "          \"idShort\": \"removers\",\n" +
                "          \"category\": null,\n" +
                "          \"modelType\": {\n" +
                "            \"name\": \"SubmodelElementCollection\"\n" +
                "          },\n" +
                "          \"value\": [\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"Remover1\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"boolean\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"product remover1，in use when true\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"物料终结1，为true时启用\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"Remover2\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"boolean\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"product remover2，in use when true\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"物料终结2，为true时启用\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ],\n" +
                "          \"kind\": \"Instance\",\n" +
                "          \"descriptions\": null\n" +
                "        },\n" +
                "        {\n" +
                "          \"ordered\": false,\n" +
                "          \"allowDuplicates\": false,\n" +
                "          \"semanticId\": {\n" +
                "            \"keys\": []\n" +
                "          },\n" +
                "          \"constraints\": [],\n" +
                "          \"hasDataSpecification\": [],\n" +
                "          \"idShort\": \"robot\",\n" +
                "          \"category\": null,\n" +
                "          \"modelType\": {\n" +
                "            \"name\": \"SubmodelElementCollection\"\n" +
                "          },\n" +
                "          \"value\": [\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"set_X\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"double\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"set target position in X\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"设置X方向的目标位置\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"set_Z\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"double\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"set target position in Z\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"设置Z方向的位置\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"read_X\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"double\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"read current position in X\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"读取X方向的当前位置\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"read_Z\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"double\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"read current position in Z\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"读取Z方向的当前位置\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"grab\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"boolean\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"true for grab,false for loose\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"为真时机械手吸盘抓取，为假时松开\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"rotateCW\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"boolean\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"rrotateCW，90° per rotate\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"顺时针旋转，上升沿时触发，一次90度\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"rotateCCW\",\n" +
                "              \"category\": \"VARIABLE\",\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"boolean\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": [\n" +
                "                {\n" +
                "                  \"language\": \"en\",\n" +
                "                  \"text\": \"rrotateCCW，90° per rotate\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"language\": \"cn\",\n" +
                "                  \"text\": \"逆时针旋转，上升沿时触发，一次90度\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"value\": \"\",\n" +
                "              \"valueId\": null,\n" +
                "              \"semanticId\": {\n" +
                "                \"keys\": []\n" +
                "              },\n" +
                "              \"constraints\": [],\n" +
                "              \"hasDataSpecification\": [],\n" +
                "              \"idShort\": \"itemDetected\",\n" +
                "              \"category\": null,\n" +
                "              \"modelType\": {\n" +
                "                \"name\": \"Property\"\n" +
                "              },\n" +
                "              \"valueType\": {\n" +
                "                \"dataObjectType\": {\n" +
                "                  \"name\": \"\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"kind\": \"Instance\",\n" +
                "              \"descriptions\": null\n" +
                "            }\n" +
                "          ],\n" +
                "          \"kind\": \"Instance\",\n" +
                "          \"descriptions\": null\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"\",\n" +
                "          \"valueId\": null,\n" +
                "          \"semanticId\": {\n" +
                "            \"keys\": []\n" +
                "          },\n" +
                "          \"constraints\": [],\n" +
                "          \"hasDataSpecification\": [],\n" +
                "          \"idShort\": \"clampSensor1\",\n" +
                "          \"category\": \"VARIABLE\",\n" +
                "          \"modelType\": {\n" +
                "            \"name\": \"Property\"\n" +
                "          },\n" +
                "          \"valueType\": {\n" +
                "            \"dataObjectType\": {\n" +
                "              \"name\": \"boolean\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"kind\": \"Instance\",\n" +
                "          \"descriptions\": [\n" +
                "            {\n" +
                "              \"language\": \"en\",\n" +
                "              \"text\": \"Unobscured when true, obscured when false\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"language\": \"cn\",\n" +
                "              \"text\": \"为真时无遮挡，为假时被遮挡\"\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"\",\n" +
                "          \"valueId\": null,\n" +
                "          \"semanticId\": {\n" +
                "            \"keys\": []\n" +
                "          },\n" +
                "          \"constraints\": [],\n" +
                "          \"hasDataSpecification\": [],\n" +
                "          \"idShort\": \"clampSensor2\",\n" +
                "          \"category\": \"VARIABLE\",\n" +
                "          \"modelType\": {\n" +
                "            \"name\": \"Property\"\n" +
                "          },\n" +
                "          \"valueType\": {\n" +
                "            \"dataObjectType\": {\n" +
                "              \"name\": \"boolean\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"kind\": \"Instance\",\n" +
                "          \"descriptions\": [\n" +
                "            {\n" +
                "              \"language\": \"en\",\n" +
                "              \"text\": \"Unobscured when true, obscured when false\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"language\": \"cn\",\n" +
                "              \"text\": \"为真时无遮挡，为假时被遮挡\"\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"\",\n" +
                "          \"valueId\": null,\n" +
                "          \"semanticId\": {\n" +
                "            \"keys\": []\n" +
                "          },\n" +
                "          \"constraints\": [],\n" +
                "          \"hasDataSpecification\": [],\n" +
                "          \"idShort\": \"leaveSensor1\",\n" +
                "          \"category\": \"VARIABLE\",\n" +
                "          \"modelType\": {\n" +
                "            \"name\": \"Property\"\n" +
                "          },\n" +
                "          \"valueType\": {\n" +
                "            \"dataObjectType\": {\n" +
                "              \"name\": \"boolean\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"kind\": \"Instance\",\n" +
                "          \"descriptions\": [\n" +
                "            {\n" +
                "              \"language\": \"en\",\n" +
                "              \"text\": \"Unobscured when true, obscured when false\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"language\": \"cn\",\n" +
                "              \"text\": \"为真时无遮挡，为假时被遮挡\"\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"\",\n" +
                "          \"valueId\": null,\n" +
                "          \"semanticId\": {\n" +
                "            \"keys\": []\n" +
                "          },\n" +
                "          \"constraints\": [],\n" +
                "          \"hasDataSpecification\": [],\n" +
                "          \"idShort\": \"leaveSensor2\",\n" +
                "          \"category\": \"VARIABLE\",\n" +
                "          \"modelType\": {\n" +
                "            \"name\": \"Property\"\n" +
                "          },\n" +
                "          \"valueType\": {\n" +
                "            \"dataObjectType\": {\n" +
                "              \"name\": \"boolean\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"kind\": \"Instance\",\n" +
                "          \"descriptions\": [\n" +
                "            {\n" +
                "              \"language\": \"en\",\n" +
                "              \"text\": \"Unobscured when true, obscured when false\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"language\": \"cn\",\n" +
                "              \"text\": \"为真时无遮挡，为假时被遮挡\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      \"kind\": \"Instance\",\n" +
                "      \"descriptions\": null\n" +
                "    }\n" +
                "  ],\n" +
                "  \"descriptions\": null\n" +
                "}\n";

    }

    Submodel submodel;
    ObjectMapper mapper;
    {
        mapper = new ObjectMapper();
//        submodel = TypeUtils.jsonToSubmodel(testStr);
        try {
            String s = mapper.writeValueAsString(submodel);
            System.out.println("s = " + s);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void test0() throws NoSuchFieldException, IllegalAccessException {
        Field identification = submodel.getClass().getDeclaredField("identification");
        identification.setAccessible(true);
        Object o = identification.get(submodel);
        System.out.println("o = " + o);

        Class<?> type = identification.getType();
        Field[] declaredFields = type.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Object o1 = declaredField.get(o);
            System.out.println("o1 = " + o1);
        }

    }

    @Test
    void test1() throws JsonProcessingException {
        //从UpcUa读取数据
        AS.readValue();
        //取出AsInstanceFactoryIo的数据
        AsInstanceFactoryIo instance_factoryIO = AS.getInstance_factoryIO();
        //构建Submodel
        Submodel submodel1 = submodel.setModelObject(instance_factoryIO).refreshValue();

        String s = mapper.writeValueAsString(submodel1);
        String s1 = mapper.writeValueAsString(instance_factoryIO);
        System.out.println("*************");
        System.out.println(s1);
        System.out.println("*************");
        System.out.println(s);

        //TODO 此方法结果是1个都不会写进去，为什么？
        //因为submodelElements的第一个元素就是SubmodelElementCollection，而所有的属性，字段，又是再其中进行了进一步的封装！！！
        //即多了一层SubmodelElementCollection
    }

    @Test
    void test2() throws IOException {
        AS.readValue();
        AsInstanceFactoryIo instance_factoryIO = AS.getInstance_factoryIO();
        System.out.println(mapper.writeValueAsString(instance_factoryIO));
        AS_Service as_service = AS.getAs_service();
        System.out.println(mapper.writeValueAsString(as_service));

        Submodel submodel1 = AsDataBase.Instance_factoryIO_Submodel.setModelObject(instance_factoryIO).refreshValue();
        System.out.println(mapper.writeValueAsString(submodel1));
        Submodel submodel2 = AsDataBase.Service_Submodel.setModelObject(as_service).refreshValue();
        System.out.println(mapper.writeValueAsString(submodel2));

        AasEnv as = AsDataBase.AS;
        String asJson1 = mapper.writeValueAsString(as);
        System.out.println(asJson1);

        as.refreshSubmodels(Arrays.asList(submodel1, submodel2));

        String asJson2 = mapper.writeValueAsString(as);
        System.out.println(asJson2);
    }
}
