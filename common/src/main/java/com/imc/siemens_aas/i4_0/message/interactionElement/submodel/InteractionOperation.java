package com.imc.siemens_aas.i4_0.message.interactionElement.submodel;

import com.imc.siemens_aas.aasenv.submodel.Submodel;
import com.imc.siemens_aas.aasenv.submodel.submodelelement.Operation;
import com.imc.siemens_aas.i4_0.message.interactionElement.InteractionElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InteractionOperation {
    private InteractionElement interaction;
    private Submodel submodel;
    private Operation operation;
}
