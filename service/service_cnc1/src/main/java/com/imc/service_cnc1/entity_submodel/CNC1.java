package com.imc.service_cnc1.entity_submodel;

import com.imc.service_cnc1.entity_submodel.metacnc1.Buttons;
import com.imc.service_cnc1.entity_submodel.metacnc1.CNC1_Status;
import com.imc.service_cnc1.entity_submodel.metacnc1.Conveyor;
import com.imc.service_cnc1.entity_submodel.metacnc1.RawColor;
import com.imc.siemens_aas.opcua.OpcUaClientService;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class CNC1 implements InitializingBean{

    private static OpcUaClientService uaClientService;

    @Autowired
    private OpcUaClientService clientService;
    private static CNC1_Instance_FactoryIO cnc1_instance_factoryIO;
    private static CNC1_Service cnc1_service;

    static {
        cnc1_instance_factoryIO = new CNC1_Instance_FactoryIO();
        cnc1_service = new CNC1_Service();
    }

    public static CNC1_Instance_FactoryIO getCnc1_instance_factoryIO() {
        return cnc1_instance_factoryIO;
    }

    public static CNC1_Service getCnc1_service() {
        return cnc1_service;
    }

    public static void readValue() {
        try {
            Buttons buttonsTemp = new Buttons();
            buttonsTemp.setEmergencyStop((Boolean) uaClientService.readNode(Cnc1NodeIds.emergencyStop));
            buttonsTemp.setReset((Boolean) uaClientService.readNode(Cnc1NodeIds.reset));
            buttonsTemp.setStart((Boolean) uaClientService.readNode(Cnc1NodeIds.start));
            buttonsTemp.setStop((Boolean) uaClientService.readNode(Cnc1NodeIds.stop));
            cnc1_instance_factoryIO.buttons = buttonsTemp;
            Conveyor conveyorTemp = new Conveyor();
            conveyorTemp.setEntryConveyorSpeed((Float) uaClientService.readNode(Cnc1NodeIds.entryConveyorSpeed));
            conveyorTemp.setExitConveyor1_Speed((Float) uaClientService.readNode(Cnc1NodeIds.exitConveyor1_Speed));
            conveyorTemp.setExitConveyor2_Speed((Float) uaClientService.readNode(Cnc1NodeIds.exitConveyor2_Speed));
            conveyorTemp.setExitConveyor3_Speed((Float) uaClientService.readNode(Cnc1NodeIds.exitConveyor3_Speed));
            cnc1_instance_factoryIO.conveyor = conveyorTemp;
            cnc1_instance_factoryIO.counter = (Short) uaClientService.readNode(Cnc1NodeIds.counter);
            cnc1_instance_factoryIO.entrySensor = (Boolean) uaClientService.readNode(Cnc1NodeIds.entrySensor);
            cnc1_instance_factoryIO.error = (Boolean) uaClientService.readNode(Cnc1NodeIds.error);
            cnc1_instance_factoryIO.exitSensor = (Boolean) uaClientService.readNode(Cnc1NodeIds.exitSensor);
            cnc1_instance_factoryIO.productType = (Boolean) uaClientService.readNode(Cnc1NodeIds.productType);
            RawColor rawColorTemp = new RawColor();
            rawColorTemp.setBlue((Boolean) uaClientService.readNode(Cnc1NodeIds.blue));
            rawColorTemp.setGreen((Boolean) uaClientService.readNode(Cnc1NodeIds.green));
            rawColorTemp.setGrey((Boolean) uaClientService.readNode(Cnc1NodeIds.grey));
            cnc1_instance_factoryIO.rawColor = rawColorTemp;

            cnc1_service.CNC1_rawColor = (Short) uaClientService.readNode(Cnc1NodeIds.CNC1_rawColor);
            cnc1_service.CNC1_type = (Boolean) uaClientService.readNode(Cnc1NodeIds.CNC1_type);
            cnc1_service.CNC1_faultSimulation = (Boolean) uaClientService.readNode(Cnc1NodeIds.CNC1_faultSimulation);
        } catch (Exception e) {
            throw new RuntimeException("读取opcua-CNC1节点信息失败");
        }
    }

    public static void setProduct(Short color, Boolean type) {
        try {
            Boolean aBoolean = uaClientService.writeNodeValue(Cnc1NodeIds.CNC1_rawColor, color);
            Boolean bBoolean = uaClientService.writeNodeValue(Cnc1NodeIds.CNC1_type, type);
            if (!(aBoolean && bBoolean)) {
                throw new RuntimeException("opc ua写入节点信息出错");
            }
        } catch (Exception e) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
    }

    public static CNC1_Status setStatus(Boolean initialFlag, Boolean startFlag, Boolean faultSimulation) {
        try {
            Boolean aBoolean = uaClientService.writeNodeValue(Cnc1NodeIds.initialFlag, initialFlag);
            Boolean bBoolean = uaClientService.writeNodeValue(Cnc1NodeIds.startFlag, startFlag);
            Boolean cBoolean = uaClientService.writeNodeValue(Cnc1NodeIds.CNC1_faultSimulation, faultSimulation);
            if (!(aBoolean && bBoolean && cBoolean)) {
                throw new RuntimeException("opc ua写入节点信息出错");
            }
            return getStatus();
        } catch (Exception e) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
    }

    public static CNC1_Status getStatus() {
        try {
            Boolean error = (Boolean) uaClientService.readNode(Cnc1NodeIds.error_s);
            Boolean initializing = (Boolean) uaClientService.readNode(Cnc1NodeIds.initializing);
            Boolean running = (Boolean) uaClientService.readNode(Cnc1NodeIds.running);
            Boolean stopped = (Boolean) uaClientService.readNode(Cnc1NodeIds.stopped);
            return new CNC1_Status(initializing, running, stopped, error);
        } catch (Exception e) {
            throw new RuntimeException("opc ua读取节点信息出错");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        uaClientService = clientService;
    }
}