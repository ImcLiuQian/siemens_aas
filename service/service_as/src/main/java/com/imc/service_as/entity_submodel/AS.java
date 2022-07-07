package com.imc.service_as.entity_submodel;

import com.imc.service_as.entity_submodel.metaas.*;
import com.imc.siemens_aas.opcua.OpcUaClientService;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * OPC UA与Submodel的中间层
 */
@Data
@Component
public class AS implements InitializingBean {

    public static OpcUaClientService uaClientService;

    @Autowired
    private OpcUaClientService clientService;

    private static AS_Instance_FactoryIO as_instance_factoryIO;
    private static AsInstanceFactoryIo Instance_factoryIO;
    private static AS_Service as_service;

    static {
        as_instance_factoryIO = new AS_Instance_FactoryIO();
        as_service = new AS_Service();
        Instance_factoryIO = new AsInstanceFactoryIo();
    }

    public static AS_Instance_FactoryIO getAs_instance_factoryIO() {
//        readValue();
        return as_instance_factoryIO;
    }

    public static AS_Service getAs_service() {
//        readValue();
        return as_service;
    }

    public static AsInstanceFactoryIo getInstance_factoryIO() {
//        readValue();
        return Instance_factoryIO;
    }

    public static void readValue() {
        try {
            as_instance_factoryIO.clampSensor1 = (Boolean) uaClientService.readNode(AsNodeIds.clampSensor1);
            as_instance_factoryIO.clampSensor2 = (Boolean) uaClientService.readNode(AsNodeIds.clampSensor2);

            ConveyorSpeed conveyorSpeedTemp = new ConveyorSpeed();
            conveyorSpeedTemp.setAssembleConveyor1((Float) uaClientService.readNode(AsNodeIds.assembleConveyor1));
            conveyorSpeedTemp.setAssembleConveyor2((Float) uaClientService.readNode(AsNodeIds.assembleConveyor2));
            as_instance_factoryIO.conveyorSpeed = conveyorSpeedTemp;

            as_instance_factoryIO.leaveSensor1 = (Boolean) uaClientService.readNode(AsNodeIds.leaveSensor1);
            as_instance_factoryIO.leaveSensor2 = (Boolean) uaClientService.readNode(AsNodeIds.leaveSensor2);

            Positioner positionerOne = new Positioner();
            positionerOne.setClamp((Boolean) uaClientService.readNode(AsNodeIds.Clamp1));
            positionerOne.setClamped((Boolean) uaClientService.readNode(AsNodeIds.Clamped1));
            positionerOne.setLimit((Boolean) uaClientService.readNode(AsNodeIds.Limit1));
            positionerOne.setRaise((Boolean) uaClientService.readNode(AsNodeIds.Raise1));
            as_instance_factoryIO.positioner1 = positionerOne;
            Positioner positionerTwo = new Positioner();
            positionerTwo.setClamp((Boolean) uaClientService.readNode(AsNodeIds.Clamp2));
            positionerTwo.setClamped((Boolean) uaClientService.readNode(AsNodeIds.Clamped2));
            positionerTwo.setLimit((Boolean) uaClientService.readNode(AsNodeIds.Limit2));
            positionerTwo.setRaise((Boolean) uaClientService.readNode(AsNodeIds.Raise2));
            as_instance_factoryIO.positioner2 = positionerTwo;

            Removers removersTemp = new Removers();
            removersTemp.setRemover1((Boolean) uaClientService.readNode(AsNodeIds.Remover1));
            removersTemp.setRemover2((Boolean) uaClientService.readNode(AsNodeIds.Remover2));
            as_instance_factoryIO.removers = removersTemp;

            Robot robotTemp = new Robot();
            robotTemp.setGrab((Boolean) uaClientService.readNode(AsNodeIds.grab));
            robotTemp.setItemDetected((Boolean) uaClientService.readNode(AsNodeIds.itemDetected));
            robotTemp.setRead_X((Float) uaClientService.readNode(AsNodeIds.read_X));
            robotTemp.setRead_Z((Float) uaClientService.readNode(AsNodeIds.read_Z));
            robotTemp.setRotateCCW((Boolean) uaClientService.readNode(AsNodeIds.rotateCCW));
            robotTemp.setRotateCW((Boolean) uaClientService.readNode(AsNodeIds.rotateCW));
            robotTemp.setSet_X((Float) uaClientService.readNode(AsNodeIds.set_X));
            robotTemp.setSet_Z((Float) uaClientService.readNode(AsNodeIds.set_Z));
            as_instance_factoryIO.robot = robotTemp;

            Instance_factoryIO.AS = as_instance_factoryIO;

            as_service.AS_mode = (Short) uaClientService.readNode(AsNodeIds.AS_mode);
        } catch (Exception e) {
            throw new RuntimeException("读取opcua-AS节点信息失败");
        }
    }

    public static void setAssembleStrategy(short mode) {
        try {
            if (!uaClientService.writeNodeValue(AsNodeIds.AS_mode, mode)) {
                throw new RuntimeException("opc ua写入节点信息出错");
            }
        } catch (Exception e) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
    }

    /**
     * 设置status
     * @param initialFlag
     * @param startFlag
     * @return
     */
    public static AS_Status set_Status(Boolean initialFlag, Boolean startFlag) {
        try {
            Boolean aBoolean = uaClientService.writeNodeValue(AsNodeIds.InitialFlag, initialFlag);
            Boolean bBoolean = uaClientService.writeNodeValue(AsNodeIds.StartFlag, startFlag);
            if (!(aBoolean && bBoolean)) {
                throw new RuntimeException("opc ua写入节点信息出错");
            }
            return getStatus();
        } catch (Exception e) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
    }

    /**
     * 读取status
     * @return
     */
    public static AS_Status getStatus() {
        try {
            Boolean error = (Boolean) uaClientService.readNode(AsNodeIds.error);
            Boolean initializing = (Boolean) uaClientService.readNode(AsNodeIds.initializing);
            Boolean running = (Boolean) uaClientService.readNode(AsNodeIds.running);
            Boolean stopped = (Boolean) uaClientService.readNode(AsNodeIds.stopped);
            return new AS_Status(initializing, running, stopped, error);
        } catch (Exception e) {
            throw new RuntimeException("opc ua读取节点信息出错");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        uaClientService = clientService;
    }
}
