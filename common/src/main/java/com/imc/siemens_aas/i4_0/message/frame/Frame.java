package com.imc.siemens_aas.i4_0.message.frame;

import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.aasenv.common.Identification;
import com.imc.siemens_aas.aasenv.common.Type.IdType;
import com.imc.siemens_aas.i4_0.message.frame.semanticProtocol.RoleType;
import com.imc.siemens_aas.i4_0.message.frame.semanticProtocol.SemanticProtocol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Frame {
    private String type;
    private Sender sender;
    private Receiver receiver;
    private String conversationId;
    private String messageId;
    private Long replyBy;
    private SemanticProtocol semanticProtocol;

    /**
     * 使用内部Builder来构建Frame
     * @return
     */
    public static Builder custom() {
        return new Builder();
    }

    @Data
    @Accessors(chain = true)//链式编程
    public static class Builder {
        private String type;
        private Sender sender;
        private Identification sendIdentification;
        private Role sendRole;
        private String senderId;
        private String senderIdType;
        private String senderRoleName;
        private Receiver receiver;
        private Identification recvIdentification;
        private Role recvRole;
        private String receiverId;
        private String receiverIdType;
        private String receiverRoleName;
        private String conversationId;
        private String messageId;
        private Long replyBy;
        private SemanticProtocol semanticProtocol;


        /**
         * 如果被调用者有现成的sender，或receiver，或其中的identification和role，那么就直接使用现成的信息去构造frame
         * TODO 没有对semanticProtocol信息进行判断，后续可以加上
         * @return
         */
        public Frame build() {
            //设置sender
            if (sender == null) {
                sender = new Sender();
                if (sendIdentification == null) {
                    sendIdentification = new Identification();
                    sendIdentification.setId(senderId);
                    sendIdentification.setIdType(senderIdType);
                }
                sender.setIdentification(sendIdentification);
                if (sendRole == null) {
                    sendRole = new Role();
                    sendRole.setName(senderRoleName);
                }
                sender.setRole(sendRole);
            }

            //设置receiver
            if (receiver == null) {
                receiver = new Receiver();
                if (recvIdentification == null) {
                    recvIdentification = new Identification();
                    recvIdentification.setId(receiverId);
                    recvIdentification.setIdType(receiverIdType);
                }
                receiver.setIdentification(recvIdentification);
                if (recvRole == null) {
                    recvRole = new Role();
                    recvRole.setName(receiverRoleName);
                }
                receiver.setRole(recvRole);
            }

            if (replyBy == null) {
                replyBy = 1000000L;
            }

            return new Frame(type, sender, receiver, conversationId, messageId, replyBy, semanticProtocol);
        }
    }
}
