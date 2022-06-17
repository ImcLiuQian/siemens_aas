package com.imc.service_cnc1_copy.controller.entity_submodel.metacnc1_copy;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CNC1Copy_Status {
    protected Boolean initializing;
    protected Boolean running;
    protected Boolean stopped;
    protected Boolean error;
}
