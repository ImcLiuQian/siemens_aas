package com.imc.siemens_aas.aasenv.submodel.submodelelement;

import lombok.Data;

import java.util.List;

@Data
public class SubmodelElementCollection extends SubmodelElement{
    private List<SubmodelElement> value;
}
