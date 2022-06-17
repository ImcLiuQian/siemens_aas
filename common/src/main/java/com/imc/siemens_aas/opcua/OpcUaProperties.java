package com.imc.siemens_aas.opcua;

import lombok.Data;

/**
 * 绑定opc ua的基本属性，后面可以绑定application.properties，通过命令行传进来
 */
@Data
public class OpcUaProperties {
    public static String URL;

    public static Integer NAMESPACE;

    static {
        URL = "opc.tcp://192.168.0.10:4840";
        NAMESPACE = 3;
    }
}
