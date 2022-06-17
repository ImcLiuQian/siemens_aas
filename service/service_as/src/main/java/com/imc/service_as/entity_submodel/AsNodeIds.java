package com.imc.service_as.entity_submodel;

import com.imc.siemens_aas.opcua.OpcUaProperties;
import lombok.Data;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@Data
public class AsNodeIds {
    private static Integer nameSpace;
    static {
        nameSpace = OpcUaProperties.NAMESPACE;
    }

    //AS模型节点
    protected static NodeId clampSensor1;
    protected static NodeId clampSensor2;
    protected static NodeId assembleConveyor1;
    protected static NodeId assembleConveyor2;
    protected static NodeId leaveSensor1;
    protected static NodeId leaveSensor2;
    protected static NodeId Clamp1;
    protected static NodeId Clamped1;
    protected static NodeId Limit1;
    protected static NodeId Raise1;
    protected static NodeId Clamp2;
    protected static NodeId Clamped2;
    protected static NodeId Limit2;
    protected static NodeId Raise2;
    protected static NodeId Remover1;
    protected static NodeId Remover2;
    protected static NodeId grab;
    protected static NodeId itemDetected;
    protected static NodeId read_X;
    protected static NodeId read_Z;
    protected static NodeId rotateCCW;
    protected static NodeId rotateCW;
    protected static NodeId set_X;
    protected static NodeId set_Z;

    //状态节点初始化
    static {
        clampSensor1 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"clampSensor1\"");
        clampSensor2 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"clampSensor2\"");
        assembleConveyor1 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"conveyorSpeed\".\"assembleConveyor1\"");
        assembleConveyor2 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"conveyorSpeed\".\"assembleConveyor2\"");
        leaveSensor1 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"leaveSensor1\"");
        leaveSensor2 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"leaveSensor2\"");
        Clamp1 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"positioner1\".\"Clamp\"");
        Clamped1 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"positioner1\".\"Clamped\"");
        Limit1 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"positioner1\".\"Limit\"");
        Raise1 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"positioner1\".\"Raise\"");
        Clamp2 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"positioner2\".\"Clamp\"");
        Clamped2 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"positioner2\".\"Clamped\"");
        Limit2 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"positioner2\".\"Limit\"");
        Raise2 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"positioner2\".\"Raise\"");
        Remover1 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"removers\".\"Remover1\"");
        Remover2 = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"removers\".\"Remover2\"");
        grab = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"robot\".\"grab\"");
        itemDetected = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"robot\".\"itemDetected\"");
        read_X = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"robot\".\"read_X\"");
        read_Z = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"robot\".\"read_Z\"");
        rotateCCW = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"robot\".\"rotateCCW\"");
        rotateCW = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"robot\".\"rotateCW\"");
        set_X = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"robot\".\"set_X\"");
        set_Z = new NodeId(nameSpace, "\"Instance_factoryIO\".\"AS\".\"robot\".\"set_Z\"");
    }

    //AS控制节点
    protected static NodeId AS_mode;
    protected static NodeId InitialFlag;
    protected static NodeId StartFlag;
    static {
        AS_mode = new NodeId(nameSpace, "\"ServiceParameter\".\"AS_mode\"");
        InitialFlag = new NodeId(nameSpace, "\"ServiceParameter\".\"initialFlag\"");
        StartFlag = new NodeId(nameSpace, "\"ServiceParameter\".\"startFlag\"");
    }

    //AS状态节点
    protected static NodeId error;
    protected static NodeId initializing;
    protected static NodeId running;
    protected static NodeId stopped;
    static {
        error = new NodeId(nameSpace, "\"ServiceParameter\".\"AS_status\".\"error\"");
        initializing = new NodeId(nameSpace, "\"ServiceParameter\".\"AS_status\".\"initializing\"");
        running = new NodeId(nameSpace, "\"ServiceParameter\".\"AS_status\".\"running\"");
        stopped = new NodeId(nameSpace, "\"ServiceParameter\".\"AS_status\".\"stopped\"");
    }
}
