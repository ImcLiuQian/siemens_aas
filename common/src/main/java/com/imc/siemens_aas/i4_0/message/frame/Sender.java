package com.imc.siemens_aas.i4_0.message.frame;

import com.imc.siemens_aas.aasenv.common.Identification;
import lombok.Data;

@Data
public class Sender {
    private Identification identification;
    private Role role;
}
