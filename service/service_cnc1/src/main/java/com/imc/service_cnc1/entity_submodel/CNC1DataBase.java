package com.imc.service_cnc1.entity_submodel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.aasenv.factory.AasFactory;
import com.imc.siemens_aas.aasenv.submodel.Submodel;
import com.imc.siemens_aas.aasenv.submodel.SubmodelFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 用来管理 CNC1的factoryIO，Service和Nameplate三个子模型
 */
@Component
@Slf4j
public class CNC1DataBase implements InitializingBean {
    public static AasEnv CNC1;

    private ObjectMapper mapper;

    public static Submodel Instance_factoryIO_Submodel;
    public static Submodel Nameplate_Submodel;
    public static Submodel Service_Submodel;

    @Override
    public void afterPropertiesSet() throws Exception {
        mapper = new ObjectMapper();

        Instance_factoryIO_Submodel = SubmodelFactory.createByFile(CNC1ConfigProperties.FactoryIoFilePath);
        log.info(mapper.writeValueAsString(Instance_factoryIO_Submodel));
        Nameplate_Submodel = SubmodelFactory.createByFile(CNC1ConfigProperties.NameplateFilePath);
        log.info(mapper.writeValueAsString(Nameplate_Submodel));
        Service_Submodel = SubmodelFactory.createByFile(CNC1ConfigProperties.ServiceFilePath);
        Service_Submodel.setModelObject(com.imc.service_cnc1.entity_submodel.CNC1.getCnc1_service());
        log.info(mapper.writeValueAsString(Service_Submodel));

        CNC1 = AasFactory.createByFile(CNC1ConfigProperties.CNC1FilePath);
        List<Submodel> submodels = CNC1.getSubmodels();
        submodels.set(submodels.indexOf(Service_Submodel), Service_Submodel);
        log.info(mapper.writeValueAsString(CNC1));
    }
}


