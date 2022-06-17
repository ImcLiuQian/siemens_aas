package com.imc.service_cnc1_copy;

import com.imc.service_cnc1_copy.controller.entity_submodel.CNC1Copy;
import com.imc.service_cnc1_copy.controller.entity_submodel.CNC1Copy_Instance_FactoryIO;
import com.imc.service_cnc1_copy.controller.entity_submodel.CNC1Copy_Service;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CNC1CopyTest {

    @Test
    void test1() throws Exception {
        CNC1Copy.readValue();
        CNC1Copy_Instance_FactoryIO cnc1Copy_instance_factoryIO = CNC1Copy.getCnc1Copy_instance_factoryIO();
        System.out.println("cnc1Copy_instance_factoryIO = " + cnc1Copy_instance_factoryIO);
        CNC1Copy_Service cnc1Copy_service = CNC1Copy.getCnc1Copy_service();
        System.out.println("cnc1Copy_service = " + cnc1Copy_service);
    }

    @Test
    void test2() throws Exception {
        CNC1Copy.readValue();
        CNC1Copy_Instance_FactoryIO cnc1Copy_instance_factoryIO = CNC1Copy.getCnc1Copy_instance_factoryIO();
        System.out.println("cnc1Copy_instance_factoryIO = " + cnc1Copy_instance_factoryIO);
        CNC1Copy_Service cnc1Copy_service = CNC1Copy.getCnc1Copy_service();
        System.out.println("cnc1Copy_service = " + cnc1Copy_service);

        CNC1Copy_Service.CNC1_copy_ChooseProduct((short) 1, false);

        CNC1Copy.readValue();
        cnc1Copy_instance_factoryIO = CNC1Copy.getCnc1Copy_instance_factoryIO();
        System.out.println("cnc1Copy_instance_factoryIO = " + cnc1Copy_instance_factoryIO);
        cnc1Copy_service = CNC1Copy.getCnc1Copy_service();
        System.out.println("cnc1Copy_service = " + cnc1Copy_service);
    }

    @Test
    void test3() throws Exception {
        CNC1Copy.readValue();
        CNC1Copy_Instance_FactoryIO cnc1Copy_instance_factoryIO = CNC1Copy.getCnc1Copy_instance_factoryIO();
        System.out.println("cnc1Copy_instance_factoryIO = " + cnc1Copy_instance_factoryIO);
        CNC1Copy_Service cnc1Copy_service = CNC1Copy.getCnc1Copy_service();
        System.out.println("cnc1Copy_service = " + cnc1Copy_service);

        CNC1Copy_Service.CNC1_copy_StatusMonitor(false, false, true);

        CNC1Copy.readValue();
        cnc1Copy_instance_factoryIO = CNC1Copy.getCnc1Copy_instance_factoryIO();
        System.out.println("cnc1Copy_instance_factoryIO = " + cnc1Copy_instance_factoryIO);
        cnc1Copy_service = CNC1Copy.getCnc1Copy_service();
        System.out.println("cnc1Copy_service = " + cnc1Copy_service);
    }
}
