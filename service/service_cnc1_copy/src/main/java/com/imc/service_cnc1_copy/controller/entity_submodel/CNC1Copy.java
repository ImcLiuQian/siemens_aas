package com.imc.service_cnc1_copy.controller.entity_submodel;

import com.imc.service_cnc1_copy.controller.entity_submodel.metacnc1_copy.Buttons;
import com.imc.service_cnc1_copy.controller.entity_submodel.metacnc1_copy.CNC1Copy_Status;
import com.imc.service_cnc1_copy.controller.entity_submodel.metacnc1_copy.Conveyor;
import com.imc.service_cnc1_copy.controller.entity_submodel.metacnc1_copy.RawColor;
import com.imc.siemens_aas.opcua.OpcUaClientService;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class CNC1Copy implements InitializingBean{

    private static OpcUaClientService uaClientService;

    @Autowired
    private OpcUaClientService clientService;
    private static CNC1Copy_Instance_FactoryIO cnc1Copy_instance_factoryIO;
    private static CNC1Copy_Service cnc1Copy_service;

    static {
        cnc1Copy_instance_factoryIO = new CNC1Copy_Instance_FactoryIO();
        cnc1Copy_service = new CNC1Copy_Service();
    }

    public static CNC1Copy_Instance_FactoryIO getCnc1Copy_instance_factoryIO() {
        return cnc1Copy_instance_factoryIO;
    }

    public static CNC1Copy_Service getCnc1Copy_service() {
        return cnc1Copy_service;
    }

    public static void readValue() {
        try {
            Buttons buttonsTemp = new Buttons();
            buttonsTemp.setEmergencyStop((Boolean) uaClientService.readNode(CNC1CopyNodeIds.emergencyStop));
            buttonsTemp.setReset((Boolean) uaClientService.readNode(CNC1CopyNodeIds.reset));
            buttonsTemp.setStart((Boolean) uaClientService.readNode(CNC1CopyNodeIds.start));
            buttonsTemp.setStop((Boolean) uaClientService.readNode(CNC1CopyNodeIds.stop));
            cnc1Copy_instance_factoryIO.buttons = buttonsTemp;
            Conveyor conveyorTemp = new Conveyor();
            conveyorTemp.setEntryConveyorSpeed((Float) uaClientService.readNode(CNC1CopyNodeIds.entryConveyorSpeed));
            conveyorTemp.setExitConveyor1_Speed((Float) uaClientService.readNode(CNC1CopyNodeIds.exitConveyor1_Speed));
            conveyorTemp.setExitConveyor2_Speed((Float) uaClientService.readNode(CNC1CopyNodeIds.exitConveyor2_Speed));
            conveyorTemp.setExitConveyor3_Speed((Float) uaClientService.readNode(CNC1CopyNodeIds.exitConveyor3_Speed));
            cnc1Copy_instance_factoryIO.conveyor = conveyorTemp;
            cnc1Copy_instance_factoryIO.counter = (Short) uaClientService.readNode(CNC1CopyNodeIds.counter);
            cnc1Copy_instance_factoryIO.entrySensor = (Boolean) uaClientService.readNode(CNC1CopyNodeIds.entrySensor);
            cnc1Copy_instance_factoryIO.error = (Boolean) uaClientService.readNode(CNC1CopyNodeIds.error);
            cnc1Copy_instance_factoryIO.exitSensor = (Boolean) uaClientService.readNode(CNC1CopyNodeIds.exitSensor);
            cnc1Copy_instance_factoryIO.productType = (Boolean) uaClientService.readNode(CNC1CopyNodeIds.productType);
            RawColor rawColorTemp = new RawColor();
            rawColorTemp.setBlue((Boolean) uaClientService.readNode(CNC1CopyNodeIds.blue));
            rawColorTemp.setGreen((Boolean) uaClientService.readNode(CNC1CopyNodeIds.green));
            rawColorTemp.setGrey((Boolean) uaClientService.readNode(CNC1CopyNodeIds.grey));
            cnc1Copy_instance_factoryIO.rawColor = rawColorTemp;

            cnc1Copy_service.CNC1_copy_rawColor = (Short) uaClientService.readNode(CNC1CopyNodeIds.CNC1Copy_rawColor);
            cnc1Copy_service.CNC1_copy_type = (Boolean) uaClientService.readNode(CNC1CopyNodeIds.CNC1Copy_type);
            cnc1Copy_service.CNC1_copy_faultSimulation = (Boolean) uaClientService.readNode(CNC1CopyNodeIds.CNC1Copy_faultSimulation);
        } catch (Exception e) {
            throw new RuntimeException("读取opcua-CNC1Copy节点信息失败");
        }
    }

    public static void setProduct(Short color, Boolean type) {
        try {
            Boolean aBoolean = uaClientService.writeNodeValue(CNC1CopyNodeIds.CNC1Copy_rawColor, color);
            Boolean bBoolean = uaClientService.writeNodeValue(CNC1CopyNodeIds.CNC1Copy_type, type);
            if (!(aBoolean && bBoolean)) {
                throw new RuntimeException("opc ua写入节点信息出错");
            }
        } catch (Exception e) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
    }

    public static CNC1Copy_Status setStatus(Boolean initialFlag, Boolean startFlag, Boolean faultSimulation) {
        try {
            Boolean aBoolean = uaClientService.writeNodeValue(CNC1CopyNodeIds.initialFlag, initialFlag);
            Boolean bBoolean = uaClientService.writeNodeValue(CNC1CopyNodeIds.startFlag, startFlag);
            Boolean cBoolean = uaClientService.writeNodeValue(CNC1CopyNodeIds.CNC1Copy_faultSimulation, faultSimulation);
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
            Boolean error = (Boolean) uaClientService.readNode(CNC1CopyNodeIds.error_s);
            Boolean initializing = (Boolean) uaClientService.readNode(CNC1CopyNodeIds.initializing);
            Boolean running = (Boolean) uaClientService.readNode(CNC1CopyNodeIds.running);
            Boolean stopped = (Boolean) uaClientService.readNode(CNC1CopyNodeIds.stopped);
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