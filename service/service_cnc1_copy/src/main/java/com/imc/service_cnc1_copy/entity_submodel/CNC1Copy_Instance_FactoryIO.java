package com.imc.service_cnc1_copy.entity_submodel;

import com.imc.service_cnc1_copy.entity_submodel.metacnc1_copy.Buttons;
import com.imc.service_cnc1_copy.entity_submodel.metacnc1_copy.Conveyor;
import com.imc.service_cnc1_copy.entity_submodel.metacnc1_copy.RawColor;
import lombok.Data;

@Data
public class CNC1Copy_Instance_FactoryIO {
    protected Buttons buttons;
    protected Conveyor conveyor;
    protected Short counter;
    protected Boolean entrySensor;
    protected Boolean error;
    protected Boolean exitSensor;
    protected Boolean productType;
    protected RawColor rawColor;
}
