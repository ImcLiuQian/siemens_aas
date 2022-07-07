package com.imc.service_cnc1_copy.entity_submodel;

import com.imc.service_cnc1_copy.entity_submodel.metacnc1_copy.CNC1Copy_Status;
import lombok.Data;

@Data
public class CNC1Copy_Service {

    protected Short CNC1_copy_rawColor;
    protected Boolean CNC1_copy_type;
    protected Boolean CNC1_copy_faultSimulation;

    public static void CNC1_copy_ChooseProduct(Short color, Boolean type) {
        CNC1Copy.setProduct(color, type);
    }

    public static CNC1Copy_Status CNC1_copy_StatusMonitor(Boolean initialFlag, Boolean startFlag, Boolean faultSimulation) {
        CNC1Copy_Status cnc1Copy_status = CNC1Copy.setStatus(initialFlag, startFlag, faultSimulation);
        return cnc1Copy_status;
    }
}
