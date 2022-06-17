package com.imc.service_cnc1.entity_submodel.metacnc1;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CNC1_Status {
    protected Boolean initializing;
    protected Boolean running;
    protected Boolean stopped;
    protected Boolean error;
}
