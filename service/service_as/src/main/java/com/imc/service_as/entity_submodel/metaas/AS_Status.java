package com.imc.service_as.entity_submodel.metaas;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AS_Status {
    protected Boolean initializing;
    protected Boolean running;
    protected Boolean stopped;
    protected Boolean error;
}
