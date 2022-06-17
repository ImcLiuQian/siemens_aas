package com.imc.service_cnc2.entity_submodel;

import com.imc.service_cnc2.entity_submodel.metacnc2.Buttons;
import com.imc.service_cnc2.entity_submodel.metacnc2.Conveyor;
import com.imc.service_cnc2.entity_submodel.metacnc2.RawColor;
import lombok.Data;

@Data
public class CNC2_Instance_FactoryIO {
    protected Buttons buttons;
    protected Conveyor conveyor;
    protected Short counter;
    protected Boolean entrySensor;
    protected Boolean error;
    protected Boolean exitSensor;
    protected Boolean productType;
    protected RawColor rawColor;
}
