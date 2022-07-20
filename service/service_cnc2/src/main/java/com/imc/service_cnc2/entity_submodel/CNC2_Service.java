package com.imc.service_cnc2.entity_submodel;

import com.imc.service_cnc2.entity_submodel.metacnc2.CNC2_Status;
import com.imc.siemens_aas.aasenv.submodel.ModelObject;
import lombok.Data;

@Data
public class CNC2_Service extends ModelObject {

    protected Short CNC2_rawColor;
    protected Boolean CNC2_type;
    protected Boolean CNC2_faultSimulation;

    public static void CNC2_ChooseProduct(Integer color, Boolean type) {
        CNC2.setProduct(Short.parseShort(color.toString()), type);
    }

    public static CNC2_Status CNC2_StatusMonitor(Boolean initialFlag, Boolean startFlag, Boolean faultSimulation) {
        CNC2_Status cnc2_status = CNC2.setStatus(initialFlag, startFlag, faultSimulation);
        return cnc2_status;
    }
}
