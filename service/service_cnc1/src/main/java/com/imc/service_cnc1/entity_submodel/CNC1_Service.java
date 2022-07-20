package com.imc.service_cnc1.entity_submodel;

import com.imc.service_cnc1.entity_submodel.metacnc1.CNC1_Status;
import com.imc.siemens_aas.aasenv.submodel.ModelObject;
import lombok.Data;

@Data
public class CNC1_Service extends ModelObject {

    public Short CNC1_rawColor;
    public Boolean CNC1_type;
    public Boolean CNC1_faultSimulation;

    public static void CNC1_ChooseProduct(Integer color, Boolean type) {
        CNC1.setProduct(Short.parseShort(color.toString()), type);
    }

    public static CNC1_Status CNC1_StatusMonitor(Boolean initialFlag, Boolean startFlag, Boolean faultSimulation) {
        CNC1_Status cnc1_status = CNC1.setStatus(initialFlag, startFlag, faultSimulation);
        return cnc1_status;
    }
}
