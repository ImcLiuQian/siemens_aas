package com.imc.service_as;

import com.imc.service_as.entity_submodel.AS;
import com.imc.service_as.entity_submodel.AS_Instance_FactoryIO;
import com.imc.service_as.entity_submodel.AS_Service;
import com.imc.service_as.entity_submodel.metaas.AS_Status;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OpcUaTest {

    @Test
    void contextLoads() {

    }

    /**
     * 读取
     * @throws Exception
     */
    @Test
    void test2() throws Exception {
        AS.readValue();
        AS_Instance_FactoryIO as_instance_factoryIO = AS.getAs_instance_factoryIO();
        System.out.println("as_instance_factoryIO = " + as_instance_factoryIO);
        AS_Service as_service = AS.getAs_service();
        System.out.println("as_service = " + as_service);
    }

    @Test
    void test3() throws Exception {
        AS.readValue();
        AS_Instance_FactoryIO as_instance_factoryIO = AS.getAs_instance_factoryIO();
        System.out.println("as_instance_factoryIO = " + as_instance_factoryIO);
        AS_Service as_service = AS.getAs_service();
        System.out.println("as_service = " + as_service);

//        AS_Service.AS_AssembleStrategy((short) 1);

        AS.readValue();
        as_instance_factoryIO = AS.getAs_instance_factoryIO();
        System.out.println("as_instance_factoryIO = " + as_instance_factoryIO);
        as_service = AS.getAs_service();
        System.out.println("as_service = " + as_service);
    }

    @Test
    void test4() throws Exception {
        AS_Status as_status = AS.getStatus();
        System.out.println("as_status = " + as_status);

        as_status = AS_Service.AS_StatusMonitor(true, false);
        System.out.println("as_status = " + as_status);

        as_status = AS_Service.AS_StatusMonitor(false, true);
        System.out.println("as_status = " + as_status);
    }
}
