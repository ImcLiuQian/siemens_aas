package com.imc.service_cnc1_copy.entity_submodel;

import com.imc.service_cnc1_copy.entity_submodel.metacnc1_copy.Buttons;
import com.imc.service_cnc1_copy.entity_submodel.metacnc1_copy.CNC1Copy_Status;
import com.imc.service_cnc1_copy.entity_submodel.metacnc1_copy.Conveyor;
import com.imc.service_cnc1_copy.entity_submodel.metacnc1_copy.RawColor;
import com.imc.siemens_aas.opcua.OpcUaClientService;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class CNC1BackUp implements InitializingBean{

    public static OpcUaClientService uaClientService;

    @Autowired
    private OpcUaClientService clientService;
    private static CNC1BackUp_Instance_FactoryIO cnc1BackUp_instance_factoryIO;
    private static CNC1BackUpInstanceFactoryIo Instance_factoryIO;
    private static CNC1BackUp_Service cnc1BackUp_service;

    static {
        cnc1BackUp_instance_factoryIO = new CNC1BackUp_Instance_FactoryIO();
        cnc1BackUp_service = new CNC1BackUp_Service();
        Instance_factoryIO = new CNC1BackUpInstanceFactoryIo();
    }

    public static CNC1BackUp_Instance_FactoryIO getCnc1BackUp_instance_factoryIO() {
        return cnc1BackUp_instance_factoryIO;
    }

    public static CNC1BackUpInstanceFactoryIo getInstance_factoryIO() {
        return Instance_factoryIO;
    }

    public static CNC1BackUp_Service getCnc1BackUp_service() {
        return cnc1BackUp_service;
    }

    public static void readValue() {
        try {
            Buttons buttonsTemp = new Buttons();
            buttonsTemp.setEmergencyStop((Boolean) uaClientService.readNode(CNC1BackUpNodeIds.emergencyStop));
            buttonsTemp.setReset((Boolean) uaClientService.readNode(CNC1BackUpNodeIds.reset));
            buttonsTemp.setStart((Boolean) uaClientService.readNode(CNC1BackUpNodeIds.start));
            buttonsTemp.setStop((Boolean) uaClientService.readNode(CNC1BackUpNodeIds.stop));
            cnc1BackUp_instance_factoryIO.buttons = buttonsTemp;
            Conveyor conveyorTemp = new Conveyor();
            conveyorTemp.setEntryConveyorSpeed((Float) uaClientService.readNode(CNC1BackUpNodeIds.entryConveyorSpeed));
            conveyorTemp.setExitConveyor1_Speed((Float) uaClientService.readNode(CNC1BackUpNodeIds.exitConveyor1_Speed));
            conveyorTemp.setExitConveyor2_Speed((Float) uaClientService.readNode(CNC1BackUpNodeIds.exitConveyor2_Speed));
            conveyorTemp.setExitConveyor3_Speed((Float) uaClientService.readNode(CNC1BackUpNodeIds.exitConveyor3_Speed));
            cnc1BackUp_instance_factoryIO.conveyor = conveyorTemp;
            cnc1BackUp_instance_factoryIO.counter = (Short) uaClientService.readNode(CNC1BackUpNodeIds.counter);
            cnc1BackUp_instance_factoryIO.entrySensor = (Boolean) uaClientService.readNode(CNC1BackUpNodeIds.entrySensor);
            cnc1BackUp_instance_factoryIO.error = (Boolean) uaClientService.readNode(CNC1BackUpNodeIds.error);
            cnc1BackUp_instance_factoryIO.exitSensor = (Boolean) uaClientService.readNode(CNC1BackUpNodeIds.exitSensor);
            cnc1BackUp_instance_factoryIO.productType = (Boolean) uaClientService.readNode(CNC1BackUpNodeIds.productType);
            RawColor rawColorTemp = new RawColor();
            rawColorTemp.setBlue((Boolean) uaClientService.readNode(CNC1BackUpNodeIds.blue));
            rawColorTemp.setGreen((Boolean) uaClientService.readNode(CNC1BackUpNodeIds.green));
            rawColorTemp.setGrey((Boolean) uaClientService.readNode(CNC1BackUpNodeIds.grey));
            cnc1BackUp_instance_factoryIO.rawColor = rawColorTemp;

            Instance_factoryIO.CNC1_copy = cnc1BackUp_instance_factoryIO;

            cnc1BackUp_service.CNC1_backup_rawColor = (Short) uaClientService.readNode(CNC1BackUpNodeIds.CNC1Copy_rawColor);
            cnc1BackUp_service.CNC1_backup_type = (Boolean) uaClientService.readNode(CNC1BackUpNodeIds.CNC1Copy_type);
            cnc1BackUp_service.CNC1_backup_faultSimulation = (Boolean) uaClientService.readNode(CNC1BackUpNodeIds.CNC1Copy_faultSimulation);
        } catch (Exception e) {
            throw new RuntimeException("读取opcua-CNC1Copy节点信息失败");
        }
    }

    public static void setProduct(Short color, Boolean type) {
        try {
            Boolean aBoolean = uaClientService.writeNodeValue(CNC1BackUpNodeIds.CNC1Copy_rawColor, color);
            Boolean bBoolean = uaClientService.writeNodeValue(CNC1BackUpNodeIds.CNC1Copy_type, type);
            if (!(aBoolean && bBoolean)) {
                throw new RuntimeException("opc ua写入节点信息出错");
            }
        } catch (Exception e) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
    }

    public static CNC1Copy_Status setStatus(Boolean initialFlag, Boolean startFlag, Boolean faultSimulation) {
        try {
            Boolean aBoolean = uaClientService.writeNodeValue(CNC1BackUpNodeIds.initialFlag, initialFlag);
            Boolean bBoolean = uaClientService.writeNodeValue(CNC1BackUpNodeIds.startFlag, startFlag);
            Boolean cBoolean = uaClientService.writeNodeValue(CNC1BackUpNodeIds.CNC1Copy_faultSimulation, faultSimulation);
            if (!(aBoolean && bBoolean && cBoolean)) {
                throw new RuntimeException("opc ua写入节点信息出错");
            }
            return getStatus();
        } catch (Exception e) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
    }

    public static CNC1Copy_Status getStatus() {
        try {
            Boolean error = (Boolean) uaClientService.readNode(CNC1BackUpNodeIds.error_s);
            Boolean initializing = (Boolean) uaClientService.readNode(CNC1BackUpNodeIds.initializing);
            Boolean running = (Boolean) uaClientService.readNode(CNC1BackUpNodeIds.running);
            Boolean stopped = (Boolean) uaClientService.readNode(CNC1BackUpNodeIds.stopped);
            return new CNC1Copy_Status(initializing, running, stopped, error);
        } catch (Exception e) {
            throw new RuntimeException("opc ua读取节点信息出错");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        uaClientService = clientService;
    }
}