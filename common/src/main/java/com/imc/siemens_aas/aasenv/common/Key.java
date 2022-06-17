package com.imc.siemens_aas.aasenv.common;

import lombok.Data;

@Data
public class Key {
    private String type;
    private Boolean local;
    private String value;
    private Integer index;
    private String idType;
}
