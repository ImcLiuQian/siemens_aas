package com.imc.siemens_aas.aasenv.submodel.submodelelement;

import com.imc.siemens_aas.aasenv.submodel.metamodel.metavalue.ValueType;
import lombok.Data;


@Data
public class Property extends SubmodelElement{
    private Object value;
    private ValueType valueType;
}
