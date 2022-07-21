package com.imc.service_cnc1_copy.entity_submodel;

import com.imc.service_cnc1_copy.entity_submodel.metacnc1_copy.CNC1Copy_Status;
import com.imc.siemens_aas.aasenv.submodel.ModelObject;
import lombok.Data;

@Data
public class CNC1BackUp_Service extends ModelObject {

    protected Short CNC1_backup_rawColor;
    protected Boolean CNC1_backup_type;
    protected Boolean CNC1_backup_faultSimulation;

    public static void CNC1_ChooseProduct(Integer color, Boolean type) {
        CNC1BackUp.setProduct(Short.parseShort(color.toString()), type);
    }

    public static CNC1Copy_Status CNC1_BackUp_StatusMonitor(Boolean initialFlag, Boolean startFlag, Boolean faultSimulation) {
        CNC1Copy_Status cnc1Copy_status = CNC1BackUp.setStatus(initialFlag, startFlag, faultSimulation);
        return cnc1Copy_status;
    }
}
