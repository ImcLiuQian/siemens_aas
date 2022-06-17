package com.imc.service_cnc2;

import com.imc.service_cnc2.entity_submodel.CNC2;
import com.imc.service_cnc2.entity_submodel.CNC2_Instance_FactoryIO;
import com.imc.service_cnc2.entity_submodel.CNC2_Service;
import com.imc.service_cnc2.entity_submodel.metacnc2.CNC2_Status;
import com.imc.siemens_aas.opcua.OpcUaClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CNC2Test {

    @Autowired
    private OpcUaClientService uaClientService;

    @Test
    void test1() throws Exception {
        CNC2.readValue();
        CNC2_Instance_FactoryIO cnc2_instance_factoryIO = CNC2.getCnc2_instance_factoryIO();
        System.out.println("cnc2_instance_factoryIO = " + cnc2_instance_factoryIO);
        CNC2_Service cnc2_service = CNC2.getCnc2_service();
        System.out.println("cnc2_service = " + cnc2_service);
    }

    @Test
    void test2() throws Exception {
        CNC2.readValue();
        CNC2_Instance_FactoryIO cnc2_instance_factoryIO = CNC2.getCnc2_instance_factoryIO();
        System.out.println("cnc2_instance_factoryIO = " + cnc2_instance_factoryIO);
        CNC2_Service cnc2_service = CNC2.getCnc2_service();
        System.out.println("cnc2_service = " + cnc2_service);

        CNC2_Service.CNC2_ChooseProduct((short)2, true);

        CNC2.readValue();
        cnc2_instance_factoryIO = CNC2.getCnc2_instance_factoryIO();
        System.out.println("cnc2_instance_factoryIO = " + cnc2_instance_factoryIO);
        cnc2_service = CNC2.getCnc2_service();
        System.out.println("cnc2_service = " + cnc2_service);
    }

    @Test
    void test3() throws Exception {
        CNC2.readValue();
        CNC2_Instance_FactoryIO cnc2_instance_factoryIO = CNC2.getCnc2_instance_factoryIO();
        System.out.println("cnc2_instance_factoryIO = " + cnc2_instance_factoryIO);
        CNC2_Service cnc2_service = CNC2.getCnc2_service();
        System.out.println("cnc2_service = " + cnc2_service);

        CNC2_Status status = CNC2.getStatus();
        System.out.println("status = " + status);

        CNC2_Status cnc2_status = CNC2_Service.CNC2_StatusMonitor(false, true, false);
        System.out.println("cnc2_status = " + cnc2_status);

        CNC2.readValue();
        cnc2_instance_factoryIO = CNC2.getCnc2_instance_factoryIO();
        System.out.println("cnc2_instance_factoryIO = " + cnc2_instance_factoryIO);
        cnc2_service = CNC2.getCnc2_service();
        System.out.println("cnc2_service = " + cnc2_service);
    }
}
