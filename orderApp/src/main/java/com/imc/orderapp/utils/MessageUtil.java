package com.imc.orderapp.utils;

import com.imc.orderapp.entity.Order;
import com.imc.siemens_aas.i4_0.message.Message;

import java.util.List;

public class MessageUtil {
    /**
     * 根据order生成所需要的I4.0消息
     * @param order
     * @return
     */
//    public static List<Message> convertOrderToMessage(Order order) {
//
//
//        return null;
//    }

    /**
     * 将message发送给i4.0程序的入口，即发送给服务请求者状态机
     * @param message
     */
    public static void sendMsgToReqMach(Message message) {

    }
}
