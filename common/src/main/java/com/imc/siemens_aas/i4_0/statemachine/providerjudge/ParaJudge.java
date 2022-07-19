package com.imc.siemens_aas.i4_0.statemachine.providerjudge;

import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.message.frame.Key;
import com.imc.siemens_aas.i4_0.message.frame.semanticProtocol.SemanticProtocol;
import com.imc.siemens_aas.i4_0.message.frame.semanticProtocol.SemanticProtocols;

import java.util.List;

/**
 * 查找对应语义信息的类
 */
public class ParaJudge {

    /**
     * 判断参数是否已知，暂时根据本地字典类型判断
     * TODO 从服务器中查找对应的是否存在对应的语义信息
     * @return
     */
    public static boolean isKnown(Message message) {
        SemanticProtocol semanticProtocol = message.getFrame().getSemanticProtocol();
        List<Key> msgKeys = semanticProtocol.getKeys();
        List<SemanticProtocol> semanticProtocols = SemanticProtocols.get();
        for (Key msgKey : msgKeys) {
            for (SemanticProtocol protocol : semanticProtocols) {
                for (Key key : protocol.getKeys()) {
                    if (msgKey.equals(key)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
