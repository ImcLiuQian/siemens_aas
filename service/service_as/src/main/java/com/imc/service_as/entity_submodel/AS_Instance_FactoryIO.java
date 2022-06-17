package com.imc.service_as.entity_submodel;

import com.imc.service_as.entity_submodel.metaas.ConveyorSpeed;
import com.imc.service_as.entity_submodel.metaas.Positioner;
import com.imc.service_as.entity_submodel.metaas.Removers;
import com.imc.service_as.entity_submodel.metaas.Robot;
import lombok.Data;

@Data
public class AS_Instance_FactoryIO {
    protected Boolean clampSensor1;
    protected Boolean clampSensor2;
    protected ConveyorSpeed conveyorSpeed;
    protected Boolean leaveSensor1;
    protected Boolean leaveSensor2;
    protected Positioner positioner1;
    protected Positioner positioner2;
    protected Removers removers;
    protected Robot robot;
}
