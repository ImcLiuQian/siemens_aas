package com.imc.service_cnc2.entity_submodel.metacnc2;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CNC2_Status {
    protected Boolean initializing;
    protected Boolean running;
    protected Boolean stopped;
    protected Boolean error;
}
