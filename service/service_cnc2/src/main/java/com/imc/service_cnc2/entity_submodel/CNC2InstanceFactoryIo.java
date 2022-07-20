package com.imc.service_cnc2.entity_submodel;

import com.imc.siemens_aas.aasenv.submodel.ModelObject;
import lombok.Data;

@Data
public class CNC2InstanceFactoryIo extends ModelObject {
    protected CNC2_Instance_FactoryIO cnc2;
}
