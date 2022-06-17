package com.imc.siemens_aas.aasenv.submodel.submodelelement;

import com.imc.siemens_aas.aasenv.submodel.metamodel.metavalue.OperationVar;
import lombok.Data;

import java.util.List;

@Data
public class Operation extends SubmodelElement{
    private List<OperationVar> inputVariable;
    private List<OperationVar> outputVariable;
    private List<OperationVar> inoutputVariable;
}
