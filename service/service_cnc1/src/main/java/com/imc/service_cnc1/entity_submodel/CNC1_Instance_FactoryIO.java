package com.imc.service_cnc1.entity_submodel;

import com.imc.service_cnc1.entity_submodel.metacnc1.Buttons;
import com.imc.service_cnc1.entity_submodel.metacnc1.Conveyor;
import com.imc.service_cnc1.entity_submodel.metacnc1.RawColor;
import lombok.Data;

@Data
public class CNC1_Instance_FactoryIO {
    protected Buttons buttons;
    protected Conveyor conveyor;
    protected Short counter;
    protected Boolean entrySensor;
    protected Boolean error;
    protected Boolean exitSensor;
    protected Boolean productType;
    protected RawColor rawColor;
}
