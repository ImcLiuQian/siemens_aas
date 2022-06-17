package com.imc.service_as.entity_submodel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.aasenv.factory.AasFactory;
import com.imc.siemens_aas.aasenv.submodel.Submodel;
import com.imc.siemens_aas.aasenv.submodel.SubmodelFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 用来管理 AS的factoryIO，Service和Nameplate三个子模型
 */
@Component
@Slf4j
public class AsDataBase implements InitializingBean {

    public static AasEnv AS;

    private ObjectMapper mapper;

    public static Submodel Instance_factoryIO_Submodel;
    public static Submodel Nameplate_Submodel;
    public static Submodel Service_Submodel;

    @Override
    public void afterPropertiesSet() throws Exception {
        mapper = new ObjectMapper();

        Instance_factoryIO_Submodel = SubmodelFactory.createByFile(ASConfigProperties.FactoryIoFilePath);
        log.info(mapper.writeValueAsString(Instance_factoryIO_Submodel));
        Nameplate_Submodel = SubmodelFactory.createByFile(ASConfigProperties.NameplateFilePath);
        log.info(mapper.writeValueAsString(Nameplate_Submodel));
        Service_Submodel = SubmodelFactory.createByFile(ASConfigProperties.ServiceFilePath);
        log.info(mapper.writeValueAsString(Service_Submodel));

        AS = AasFactory.createByFile(ASConfigProperties.AsFilePath);
        log.info(mapper.writeValueAsString(AS));
    }
}
