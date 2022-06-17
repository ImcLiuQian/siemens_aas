package com.imc.service_cnc2.entity_submodel;

import com.imc.service_cnc2.entity_submodel.metacnc2.Buttons;
import com.imc.service_cnc2.entity_submodel.metacnc2.CNC2_Status;
import com.imc.service_cnc2.entity_submodel.metacnc2.Conveyor;
import com.imc.service_cnc2.entity_submodel.metacnc2.RawColor;
import com.imc.siemens_aas.opcua.OpcUaClientService;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class CNC2 implements InitializingBean{

    private static OpcUaClientService uaClientService;

    @Autowired
    private OpcUaClientService clientService;
    private static CNC2_Instance_FactoryIO cnc2_instance_factoryIO;
    private static CNC2_Service cnc2_service;

    static {
        cnc2_instance_factoryIO = new CNC2_Instance_FactoryIO();
        cnc2_service = new CNC2_Service();
    }

    public static CNC2_Instance_FactoryIO getCnc2_instance_factoryIO() {
        return cnc2_instance_factoryIO;
    }

    public static CNC2_Service getCnc2_service() {
        return cnc2_service;
    }

    public static void readValue() {
        try {
            Buttons buttonsTemp = new Buttons();
            buttonsTemp.setEmergencyStop((Boolean) uaClientService.readNode(Cnc2NodeIds.emergencyStop));
            buttonsTemp.setReset((Boolean) uaClientService.readNode(Cnc2NodeIds.reset));
            buttonsTemp.setStart((Boolean) uaClientService.readNode(Cnc2NodeIds.start));
            buttonsTemp.setStop((Boolean) uaClientService.readNode(Cnc2NodeIds.stop));
            cnc2_instance_factoryIO.buttons = buttonsTemp;
            Conveyor conveyorTemp = new Conveyor();
            conveyorTemp.setEntryConveyorSpeed((Float) uaClientService.readNode(Cnc2NodeIds.entryConveyorSpeed));
            conveyorTemp.setExitConveyor1_Speed((Float) uaClientService.readNode(Cnc2NodeIds.exitConveyor1_Speed));
            conveyorTemp.setExitConveyor2_Speed((Float) uaClientService.readNode(Cnc2NodeIds.exitConveyor2_Speed));
            conveyorTemp.setExitConveyor3_Speed((Float) uaClientService.readNode(Cnc2NodeIds.exitConveyor3_Speed));
            cnc2_instance_factoryIO.conveyor = conveyorTemp;
            cnc2_instance_factoryIO.counter = (Short) uaClientService.readNode(Cnc2NodeIds.counter);
            cnc2_instance_factoryIO.entrySensor = (Boolean) uaClientService.readNode(Cnc2NodeIds.entrySensor);
            cnc2_instance_factoryIO.error = (Boolean) uaClientService.readNode(Cnc2NodeIds.error);
            cnc2_instance_factoryIO.exitSensor = (Boolean) uaClientService.readNode(Cnc2NodeIds.exitSensor);
            cnc2_instance_factoryIO.productType = (Boolean) uaClientService.readNode(Cnc2NodeIds.productType);
            RawColor rawColorTemp = new RawColor();
            rawColorTemp.setBlue((Boolean) uaClientService.readNode(Cnc2NodeIds.blue));
            rawColorTemp.setGreen((Boolean) uaClientService.readNode(Cnc2NodeIds.green));
            rawColorTemp.setGrey((Boolean) uaClientService.readNode(Cnc2NodeIds.grey));
            cnc2_instance_factoryIO.rawColor = rawColorTemp;

            cnc2_service.CNC2_rawColor = (Short) uaClientService.readNode(Cnc2NodeIds.CNC2_rawColor);
            cnc2_service.CNC2_type = (Boolean) uaClientService.readNode(Cnc2NodeIds.CNC2_type);
            cnc2_service.CNC2_faultSimulation = (Boolean) uaClientService.readNode(Cnc2NodeIds.CNC2_faultSimulation);
        } catch (Exception e) {
            throw new RuntimeException("读取opcua-CNC1节点信息失败");
        }
    }

    public static void setProduct(Short color, Boolean type) {
        try {
            Boolean aBoolean = uaClientService.writeNodeValue(Cnc2NodeIds.CNC2_rawColor, color);
            Boolean bBoolean = uaClientService.writeNodeValue(Cnc2NodeIds.CNC2_type, type);
            if (!(aBoolean && bBoolean)) {
                throw new RuntimeException("opc ua写入节点信息出错");
            }
        } catch (Exception e) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
    }

    public static CNC2_Status setStatus(Boolean initialFlag, Boolean startFlag, Boolean faultSimulation) {
        try {
            Boolean aBoolean = uaClientService.writeNodeValue(Cnc2NodeIds.initialFlag, initialFlag);
            Boolean bBoolean = uaClientService.writeNodeValue(Cnc2NodeIds.startFlag, startFlag);
            Boolean cBoolean = uaClientService.writeNodeValue(Cnc2NodeIds.CNC2_faultSimulation, faultSimulation);
            if (!(aBoolean && bBoolean && cBoolean)) {
                throw new RuntimeException("opc ua写入节点信息出错");
            }
            return getStatus();
        } catch (Exception e) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
    }

    public static CNC2_Status getStatus() {
        try {
            Boolean error = (Boolean) uaClientService.readNode(Cnc2NodeIds.error_s);
            Boolean initializing = (Boolean) uaClientService.readNode(Cnc2NodeIds.initializing);
            Boolean running = (Boolean) uaClientService.readNode(Cnc2NodeIds.running);
            Boolean stopped = (Boolean) uaClientService.readNode(Cnc2NodeIds.stopped);
            return new CNC2_Status(initializing, running, stopped, error);
        } catch (Exception e) {
            throw new RuntimeException("opc ua读取节点信息出错");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        uaClientService = clientService;
    }
}