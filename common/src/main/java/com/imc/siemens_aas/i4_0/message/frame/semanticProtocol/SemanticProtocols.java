package com.imc.siemens_aas.i4_0.message.frame.semanticProtocol;

import com.imc.siemens_aas.i4_0.message.frame.Key;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO 这里应该是查找字典服务器去添加字典类型
 */
public class SemanticProtocols {
    private static List<SemanticProtocol> semanticProtocols;
    public static SemanticProtocol semanticProtocol;

    static {
        semanticProtocol = new SemanticProtocol();
        Key key = new Key();
        key.setType("toBeDecided");
        key.setIdType("toBeDecided");
        key.setValue("toBeDecided");
        key.setLocal(false);
        LinkedList<Key> keys = new LinkedList<>();
        keys.add(key);
        semanticProtocol.setKeys(keys);
        semanticProtocols.add(semanticProtocol);
    }

    public static List<SemanticProtocol> get() {
        return semanticProtocols;
    }
}
