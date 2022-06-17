package com.imc.service_as.entity_submodel.metaas;

import lombok.Data;

@Data
public class Robot {
    private Boolean grab;
    private Boolean itemDetected;
    private Float read_X;
    private Float read_Z;
    private Boolean rotateCCW;
    private Boolean rotateCW;
    private Float set_X;
    private Float set_Z;
}
