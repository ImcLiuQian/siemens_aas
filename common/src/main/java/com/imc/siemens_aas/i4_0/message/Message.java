package com.imc.siemens_aas.i4_0.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.imc.siemens_aas.aasenv.AasEnv;
import com.imc.siemens_aas.aasenv.common.Identification;
import com.imc.siemens_aas.aasenv.common.Type.IdType;
import com.imc.siemens_aas.aasenv.submodel.Submodel;
import com.imc.siemens_aas.aasenv.submodel.submodelelement.SubmodelElement;
import com.imc.siemens_aas.i4_0.message.frame.Frame;
import com.imc.siemens_aas.i4_0.message.frame.Receiver;
import com.imc.siemens_aas.i4_0.message.frame.Role;
import com.imc.siemens_aas.i4_0.message.frame.Sender;
import com.imc.siemens_aas.i4_0.message.frame.semanticProtocol.RoleType;
import com.imc.siemens_aas.i4_0.message.interactionElement.InteractionElement;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class Message {
    private Frame frame;
    private List<InteractionElement> interactionElements;

    /**
     * 对返回的消息帧头进行初始化，暂不设置frameType
     * @param message
     * @return
     */
    public Message initRecvFrame(Message message) {

        Frame recvFrame = message.getFrame();

        frame = Frame.custom()
                .setSendIdentification(recvFrame.getReceiver().getIdentification())
                .setSendRole(recvFrame.getReceiver().getRole())
                .setRecvIdentification(recvFrame.getSender().getIdentification())
                .setRecvRole(recvFrame.getSender().getRole())
                .setConversationId(recvFrame.getConversationId())
                .setMessageId(recvFrame.getMessageId() + 1)//TODO 这里的策略是回复的消息将messageId + 1，之后可以完善
                .build();

        return this;
    }

    /**
     * 因为Message中的interactionElements持有Property属性
     * 而Property在进行json转换时，需要在json中带有type字段指明其为Property类型
     * 因此，在这里，需要对msgJson进行type字段的填充
     * @param msgJson
     * @return
     */
    public static Message createByJson(String msgJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            ObjectNode jsonNode = (ObjectNode) mapper.readTree(msgJson);
            JsonNode iElements = jsonNode.path("interactionElements");
            for (JsonNode iElement : iElements) {
                SubmodelElement.addTypeInJson(iElement);
            }
            return mapper.convertValue(jsonNode, Message.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("message json 解析错误");
        }
    }
}
