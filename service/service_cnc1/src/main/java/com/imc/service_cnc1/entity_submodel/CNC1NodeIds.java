package com.imc.service_cnc1.entity_submodel;

import com.imc.siemens_aas.opcua.OpcUaProperties;
import lombok.Data;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@Data
public class CNC1NodeIds {
    private static Integer nameSpace;
    static {
        nameSpace = OpcUaProperties.NAMESPACE;
    }

    //模型信息
    protected static NodeId emergencyStop;
    protected static NodeId reset;
    protected static NodeId start;
    protected static NodeId stop;
    protected static NodeId entryConveyorSpeed;
    protected static NodeId exitConveyor1_Speed;
    protected static NodeId exitConveyor2_Speed;
    protected static NodeId exitConveyor3_Speed;
    protected static NodeId counter;
    protected static NodeId entrySensor;
    protected static NodeId error;
    protected static NodeId exitSensor;
    protected static NodeId productType;
    protected static NodeId blue;
    protected static NodeId green;
    protected static NodeId grey;
    //初始化
    static {
        emergencyStop = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"buttons\".\"emergencyStop\"");
        reset = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"buttons\".\"reset\"");
        start = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"buttons\".\"start\"");
        stop = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"buttons\".\"stop\"");
        entryConveyorSpeed = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"conveyor\".\"entryConveyorSpeed\"");
        exitConveyor1_Speed = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"conveyor\".\"exitConveyor1_Speed\"");
        exitConveyor2_Speed = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"conveyor\".\"exitConveyor2_Speed\"");
        exitConveyor3_Speed = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"conveyor\".\"exitConveyor3_Speed\"");
        counter = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"counter\"");
        entrySensor = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"entrySensor\"");
        error = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"error\"");
        exitSensor = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"exitSensor\"");
        productType = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"productType\"");
        blue = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"rawColor\".\"blue\"");
        green = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"rawColor\".\"green\"");
        grey = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1\".\"rawColor\".\"grey\"");
    }


    public static NodeId CNC1_rawColor;
    public static NodeId CNC1_type;
    public static NodeId CNC1_faultSimulation;
    public static NodeId initialFlag;
    public static NodeId startFlag;
    public static NodeId stopFlag;
    //初始化
    static {
        CNC1_rawColor = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC1_rawColor\"");
        CNC1_type = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC1_type\"");
        CNC1_faultSimulation = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC1_faultSimulation\"");
        initialFlag = new NodeId(nameSpace, "\"ServiceParameter\".\"initialFlag\"");
        startFlag = new NodeId(nameSpace, "\"ServiceParameter\".\"startFlag\"");
        stopFlag = new NodeId(nameSpace, "\"ServiceParameter\".\"stopFlag\"");
    }


    protected static NodeId error_s;
    protected static NodeId initializing;
    protected static NodeId running;
    protected static NodeId stopped;
    static {
        error_s = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC1_status\".\"error\"");
        initializing = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC1_status\".\"initializing\"");
        running = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC1_status\".\"running\"");
        stopped = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC1_status\".\"stopped\"");
    }
}
