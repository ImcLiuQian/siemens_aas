package com.imc.siemens_aas.aasenv.submodel.metamodel;

import com.imc.siemens_aas.aasenv.common.Key;
import lombok.Data;

import java.util.List;

@Data
public class SemanticId {
    private List<Key> keys;
}
