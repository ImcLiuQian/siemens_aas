package com.imc.siemens_aas.i4_0.statemachine.state.provider;

import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.utils.HttpClientHelper;

/**
 * 错误状态
 */
public class Error implements ProviderState{
    private static Error singleton = new Error();
    public static ProviderState getInstance() {
        return singleton;
    }
    private Error() {}

    @Override
    public void doExecute(ProviderContext context, Message msg) {
        String reqUrl = context.getReqUrl();
        if (reqUrl != null && reqUrl != "") {
            //因为只有在offer被接受后，执行服务时，context中的ReqUrl才会被写入值
            //所以reqUrl如果为空或""，就说明，没有被调用过服务，那么就不用通知Requester重新调用一次
            //如果不是这两种情况，就通知Requester重新调用
            HttpClientHelper.doPostByNotParam(reqUrl + "/providerError?aasIdShort=" +
                    context.getAasEnv().getAssetAdministrationShells().get(0).getIdShort());
        }
    }
}
