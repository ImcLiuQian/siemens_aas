package com.imc.service_cnc2.entity_submodel;

import com.imc.service_cnc2.entity_submodel.metacnc2.CNC2_Status;
import lombok.Data;

@Data
public class CNC2_Service {

    protected Short CNC2_rawColor;
    protected Boolean CNC2_type;
    protected Boolean CNC2_faultSimulation;

    public static void CNC2_ChooseProduct(Short color, Boolean type) {
        CNC2.setProduct(color, type);
    }

    public static CNC2_Status CNC2_StatusMonitor(Boolean initialFlag, Boolean startFlag, Boolean faultSimulation) {
        CNC2_Status cnc2_status = CNC2.setStatus(initialFlag, startFlag, faultSimulation);
        return cnc2_status;
    }
}
