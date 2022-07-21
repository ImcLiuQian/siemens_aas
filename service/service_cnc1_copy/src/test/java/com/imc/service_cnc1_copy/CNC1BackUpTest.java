package com.imc.service_cnc1_copy;

import com.imc.service_cnc1_copy.entity_submodel.CNC1BackUp;
import com.imc.service_cnc1_copy.entity_submodel.CNC1BackUp_Instance_FactoryIO;
import com.imc.service_cnc1_copy.entity_submodel.CNC1BackUp_Service;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CNC1BackUpTest {

    @Test
    void test1() throws Exception {
        CNC1BackUp.readValue();
        CNC1BackUp_Instance_FactoryIO cnc1BackUp_instance_factoryIO = CNC1BackUp.getCnc1BackUp_instance_factoryIO();
        System.out.println("cnc1BackUp_instance_factoryIO = " + cnc1BackUp_instance_factoryIO);
        CNC1BackUp_Service cnc1BackUp_service = CNC1BackUp.getCnc1BackUp_service();
        System.out.println("cnc1BackUp_service = " + cnc1BackUp_service);
    }

    @Test
    void test2() throws Exception {
        CNC1BackUp.readValue();
        CNC1BackUp_Instance_FactoryIO cnc1BackUp_instance_factoryIO = CNC1BackUp.getCnc1BackUp_instance_factoryIO();
        System.out.println("cnc1BackUp_instance_factoryIO = " + cnc1BackUp_instance_factoryIO);
        CNC1BackUp_Service cnc1BackUp_service = CNC1BackUp.getCnc1BackUp_service();
        System.out.println("cnc1BackUp_service = " + cnc1BackUp_service);

//        CNC1BackUp_Service.CNC1_copy_ChooseProduct((short) 1, false);

        CNC1BackUp.readValue();
        cnc1BackUp_instance_factoryIO = CNC1BackUp.getCnc1BackUp_instance_factoryIO();
        System.out.println("cnc1BackUp_instance_factoryIO = " + cnc1BackUp_instance_factoryIO);
        cnc1BackUp_service = CNC1BackUp.getCnc1BackUp_service();
        System.out.println("cnc1BackUp_service = " + cnc1BackUp_service);
    }

    @Test
    void test3() throws Exception {
        CNC1BackUp.readValue();
        CNC1BackUp_Instance_FactoryIO cnc1BackUp_instance_factoryIO = CNC1BackUp.getCnc1BackUp_instance_factoryIO();
        System.out.println("cnc1BackUp_instance_factoryIO = " + cnc1BackUp_instance_factoryIO);
        CNC1BackUp_Service cnc1BackUp_service = CNC1BackUp.getCnc1BackUp_service();
        System.out.println("cnc1BackUp_service = " + cnc1BackUp_service);

//        CNC1BackUp_Service.CNC1_copy_StatusMonitor(false, false, true);

        CNC1BackUp.readValue();
        cnc1BackUp_instance_factoryIO = CNC1BackUp.getCnc1BackUp_instance_factoryIO();
        System.out.println("cnc1BackUp_instance_factoryIO = " + cnc1BackUp_instance_factoryIO);
        cnc1BackUp_service = CNC1BackUp.getCnc1BackUp_service();
        System.out.println("cnc1BackUp_service = " + cnc1BackUp_service);
    }
}
