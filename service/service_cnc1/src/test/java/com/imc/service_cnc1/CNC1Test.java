package com.imc.service_cnc1;

import com.imc.service_cnc1.entity_submodel.CNC1;
import com.imc.service_cnc1.entity_submodel.CNC1_Instance_FactoryIO;
import com.imc.service_cnc1.entity_submodel.CNC1_Service;
import com.imc.siemens_aas.opcua.OpcUaClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CNC1Test {

    @Autowired
    private OpcUaClientService uaClientService;

    @Test
    void test1() throws Exception {
        CNC1.readValue();
        CNC1_Instance_FactoryIO cnc1_instance_factoryIO = CNC1.getCnc1_instance_factoryIO();
        System.out.println("cnc1_instance_factoryIO = " + cnc1_instance_factoryIO);
        CNC1_Service cnc1_service = CNC1.getCnc1_service();
        System.out.println("cnc1_service = " + cnc1_service);
    }

    @Test
    void test2() throws Exception {
        CNC1.readValue();
        CNC1_Instance_FactoryIO cnc1_instance_factoryIO = CNC1.getCnc1_instance_factoryIO();
        System.out.println("cnc1_instance_factoryIO = " + cnc1_instance_factoryIO);
        CNC1_Service cnc1_service = CNC1.getCnc1_service();
        System.out.println("cnc1_service = " + cnc1_service);

//        CNC1_Service.CNC1_ChooseProduct((short)2, true);

        CNC1.readValue();
        cnc1_instance_factoryIO = CNC1.getCnc1_instance_factoryIO();
        System.out.println("cnc1_instance_factoryIO = " + cnc1_instance_factoryIO);
        cnc1_service = CNC1.getCnc1_service();
        System.out.println("cnc1_service = " + cnc1_service);
    }

    @Test
    void test3() throws Exception {
        CNC1.readValue();
        CNC1_Instance_FactoryIO cnc1_instance_factoryIO = CNC1.getCnc1_instance_factoryIO();
        System.out.println("cnc1_instance_factoryIO = " + cnc1_instance_factoryIO);
        CNC1_Service cnc1_service = CNC1.getCnc1_service();
        System.out.println("cnc1_service = " + cnc1_service);

        CNC1_Service.CNC1_StatusMonitor(false, false, true);

        CNC1.readValue();
        cnc1_instance_factoryIO = CNC1.getCnc1_instance_factoryIO();
        System.out.println("cnc1_instance_factoryIO = " + cnc1_instance_factoryIO);
        cnc1_service = CNC1.getCnc1_service();
        System.out.println("cnc1_service = " + cnc1_service);
    }
}
