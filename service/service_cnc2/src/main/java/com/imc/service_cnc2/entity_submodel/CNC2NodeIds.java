package com.imc.service_cnc2.entity_submodel;

import com.imc.siemens_aas.opcua.OpcUaProperties;
import lombok.Data;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@Data
public class CNC2NodeIds {
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
        emergencyStop = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"buttons\".\"emergencyStop\"");
        reset = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"buttons\".\"reset\"");
        start = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"buttons\".\"start\"");
        stop = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"buttons\".\"stop\"");
        entryConveyorSpeed = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"conveyor\".\"entryConveyorSpeed\"");
        exitConveyor1_Speed = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"conveyor\".\"exitConveyor1_Speed\"");
        exitConveyor2_Speed = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"conveyor\".\"exitConveyor2_Speed\"");
        exitConveyor3_Speed = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"conveyor\".\"exitConveyor3_Speed\"");
        counter = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"counter\"");
        entrySensor = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"entrySensor\"");
        error = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"error\"");
        exitSensor = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"exitSensor\"");
        productType = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"productType\"");
        blue = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"rawColor\".\"blue\"");
        green = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"rawColor\".\"green\"");
        grey = new NodeId(nameSpace, "\"Instance_factoryIO\".\"CNC2\".\"rawColor\".\"grey\"");
    }


    public static NodeId CNC2_rawColor;
    public static NodeId CNC2_type;
    public static NodeId CNC2_faultSimulation;
    public static NodeId initialFlag;
    public static NodeId startFlag;
    public static NodeId stopFlag;
    //初始化
    static {
        CNC2_rawColor = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC2_rawcolor\"");
        CNC2_type = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC2_type\"");
        CNC2_faultSimulation = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC2_faultSimulation\"");
        initialFlag = new NodeId(nameSpace, "\"ServiceParameter\".\"initialFlag\"");
        startFlag = new NodeId(nameSpace, "\"ServiceParameter\".\"startFlag\"");
        stopFlag = new NodeId(nameSpace, "\"ServiceParameter\".\"stopFlag\"");
    }


    protected static NodeId error_s;
    protected static NodeId initializing;
    protected static NodeId running;
    protected static NodeId stopped;
    static {
        error_s = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC2_status\".\"error\"");
        initializing = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC2_status\".\"initializing\"");
        running = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC2_status\".\"running\"");
        stopped = new NodeId(nameSpace, "\"ServiceParameter\".\"CNC2_status\".\"stopped\"");
    }
}
