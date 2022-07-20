package com.imc.service_cnc1_copy.entity_submodel;

import com.imc.siemens_aas.opcua.OpcUaProperties;
import lombok.Data;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@Data
public class CNC1BackUpNodeIds {
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
        emergencyStop = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"buttons\".\"emergencyStop\"");
        reset = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"buttons\".\"reset\"");
        start = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"buttons\".\"start\"");
        stop = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"buttons\".\"stop\"");
        entryConveyorSpeed = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"conveyor\".\"entryConveyorSpeed\"");
        exitConveyor1_Speed = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"conveyor\".\"exitConveyor1_Speed\"");
        exitConveyor2_Speed = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"conveyor\".\"exitConveyor2_Speed\"");
        exitConveyor3_Speed = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"conveyor\".\"exitConveyor3_Speed\"");
        counter = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"counter\"");
        entrySensor = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"entrySensor\"");
        error = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"error\"");
        exitSensor = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"exitSensor\"");
        productType = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"productType\"");
        blue = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"rawColor\".\"blue\"");
        green = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"rawColor\".\"green\"");
        grey = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC1_copy\".\"rawColor\".\"grey\"");
    }

    public static NodeId CNC1Copy_rawColor;
    public static NodeId CNC1Copy_type;
    public static NodeId CNC1Copy_faultSimulation;
    public static NodeId initialFlag;
    public static NodeId startFlag;
    public static NodeId stopFlag;
    //初始化
    static {
        CNC1Copy_rawColor = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC1_rawColor\"");
        CNC1Copy_type = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC1_type\"");
        CNC1Copy_faultSimulation = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC1_copy_faultSimulation\"");
        initialFlag = new NodeId(nameSpace, "\"ServiceParameter\".\"initialFlag\"");
        startFlag = new NodeId(nameSpace, "\"ServiceParameter\".\"startFlag\"");
        stopFlag = new NodeId(nameSpace, "\"ServiceParameter\".\"stopFlag\"");
    }


    protected static NodeId error_s;
    protected static NodeId initializing;
    protected static NodeId running;
    protected static NodeId stopped;
    static {
        error_s = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC1_copy_status\".\"error\"");
        initializing = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC1_copy_status\".\"initializing\"");
        running = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC1_copy_status\".\"running\"");
        stopped = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC1_copy_status\".\"stopped\"");
    }
}
