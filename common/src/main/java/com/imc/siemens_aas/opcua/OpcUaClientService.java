package com.imc.siemens_aas.opcua;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.*;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class OpcUaClientService {

    public OpcUaClient opcUaClient;

    public OpcUaClientService() {
        try {
            opcUaClient = OpcUaClient.create(OpcUaProperties.URL);
            opcUaClient.connect().get();
            log.info("opc ua client init succeed");
        } catch (Exception e) {
            log.error("opc ua client init error");
        }
    }

    protected void finalize() {
        try {
            opcUaClient.disconnect().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取节点数据
     * @throws Exception
     */
    public Object readNode(NodeId nodeId) throws Exception {
        //读取节点数据
        DataValue dataValue = opcUaClient.readValue(0.0, TimestampsToReturn.Neither, nodeId).get();
        Object value = dataValue.getValue().getValue();
        return value;
    }

    /**
     * 写入节点数据
     * @throws Exception
     */
    public Boolean writeNodeValue(NodeId nodeId, Object value) throws Exception {
        DataValue dv = new DataValue(new Variant(value), null, null);
        //写入节点数据
        StatusCode statusCode = opcUaClient.writeValue(nodeId, dv).get();
        return statusCode.isGood();
    }
}