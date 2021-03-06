package com.imc.service_as.entity_submodel;

import com.imc.service_as.entity_submodel.metaas.AS_Status;
import com.imc.siemens_aas.aasenv.submodel.ModelObject;
import lombok.Data;

@Data
public class AS_Service extends ModelObject {

    public Short AS_mode;

    public static void AS_AssembleStrategy(Integer mode) {
        AS.setAssembleStrategy(Short.parseShort(mode.toString()));
    }

    public static AS_Status AS_StatusMonitor(Boolean initialFlag, Boolean startFlag) {
        AS_Status as_status = AS.set_Status(initialFlag, startFlag);
        return as_status;
    }
}
