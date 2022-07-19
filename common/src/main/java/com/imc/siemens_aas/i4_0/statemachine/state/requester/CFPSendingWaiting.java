package com.imc.siemens_aas.i4_0.statemachine.state.requester;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.message.frame.MessageType;
import com.imc.siemens_aas.i4_0.statemachine.state.requester.thread.CFPSendingThread;
import com.imc.siemens_aas.utils.HttpClientHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 等待call for proposal的触发消息状态
 * 等待message消息按照完美逻辑，是应该写在这个类里面的，但是为了简化开发使用SpringBoot的Controller，就直接写到状态机里面了
 */
@Slf4j
public class CFPSendingWaiting implements RequesterState{
    private static CFPSendingWaiting singleton = new CFPSendingWaiting();
    public static RequesterState getInstance() {
        return singleton;
    }
    private CFPSendingWaiting() {}

    @Override
    public void doExecute(RequesterContext context, Message msg) {
        String type = msg.getFrame().getType();
        if (type.equals(MessageType.CallForPro)) {//如果前端发送过来的消息类型是CallForPro
            try {
                //给所有的AAS发送CallForPro消息
                ObjectMapper mapper = new ObjectMapper();
                //根据AAS调用的需求，向注册中心拿到符合要求的AAS的接口
                //TODO url接口对齐
                String jsonUrls = HttpClientHelper.doPostByParam(context.getRicUrl() + "", mapper.writeValueAsString(msg), 5000);
                HashMap<String, String> aasUrls;
                try {
                    aasUrls = new ObjectMapper().readValue(jsonUrls, new TypeReference<HashMap<String, String>>() {});
                } catch (JsonProcessingException e) {
                    log.error("状态机初始化失败: aas urls json解析失败");
                    throw new RuntimeException(e);
                }
                //向各个AAS挨个发送HTTP请求
                Collection<String> urls = aasUrls.values();
                LinkedList<Thread> sendThreads = new LinkedList<>();
                for (String url : urls) {
                    //开启多个线程同步去处理
                    //因为，假设是单线程，并且有10个AAS，这里会挨个等待10个AAS的回复，而当所有的AAS都没有回答时，会等待10 × getReplyBy().intValue()的时间长度
                    //而如果是多线程，只需要等待一个getReplyBy().intValue()的长度
                    CFPSendingThread cfpSendingThread = new CFPSendingThread(context, url + "/aas/i4.0/provider/aasProposal", msg);
                    cfpSendingThread.start();
                    sendThreads.add(cfpSendingThread);
                }
                //切换状态机的状态
                context.changeState(OfferWaiting.getInstance());
                //线程join
                for (Thread sendThread : sendThreads) {
                    sendThread.join();
                }
                //join完之后，说明，所有的请求都执行完毕了，让context根据当前的state去执行下面的操作，
                //参数为空是因为不需要message消息，评估所需的数据已经通过context.addOffer添加到context里面了
                context.handle(null);
            } catch (JsonProcessingException e) {
                log.error("Message json 转换错误");
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
