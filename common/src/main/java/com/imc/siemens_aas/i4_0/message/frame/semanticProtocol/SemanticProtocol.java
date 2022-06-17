package com.imc.siemens_aas.i4_0.message.frame.semanticProtocol;

import com.imc.siemens_aas.i4_0.message.frame.Key;
import lombok.Data;

import java.util.List;

@Data
public class SemanticProtocol {
    private List<Key> keys;
}
