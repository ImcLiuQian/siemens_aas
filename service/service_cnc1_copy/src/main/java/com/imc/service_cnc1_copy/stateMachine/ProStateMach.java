package com.imc.service_cnc1_copy.stateMachine;

import com.imc.service_cnc1_copy.entity_submodel.CNC1BackUp;
import com.imc.service_cnc1_copy.entity_submodel.CNC1BackUpDataBase;
import com.imc.service_cnc1_copy.entity_submodel.CNC1BackUpNodeIds;
import com.imc.service_cnc1_copy.stateMachine.thread.FaultCheckThread;
import com.imc.siemens_aas.i4_0.statemachine.ProviderStateMach;
import com.imc.siemens_aas.i4_0.statemachine.providerjudge.SampleProJudgeStrategy;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProStateMach implements InitializingBean {
    @Autowired
    public ProviderStateMach stateMach;

    @Override
    public void afterPropertiesSet() throws Exception {
        stateMach.init(CNC1BackUpDataBase.CNC1BackUp, SampleProJudgeStrategy.getInstance());
        new FaultCheckThread(this).start();
    }

    /**
     * 重新启动FactoryIO场景
     * 为什么这个重新启动不直接加在状态机里面？
     * 因为这个是特定场景相关的处理，最好不要写在通用的状态机里面
     * 那为什么不直接写在FaultCheckThread里面，而要写成这种回调函数的性质？
     * 因为FaultCheckThread设计出来，是作为错误检查线程，其目的是用来扫描错误状态，以及进行I4.0故障通知，按照这个意义来说，是不应该包含restart相关的代码的
     * 而这个restart的代码又是写在这里是最合适的，所以就设计成这种回调函数
     */
    @SneakyThrows
    public void restart() {
        CNC1BackUp.uaClientService.writeNodeValue(CNC1BackUpNodeIds.initialFlag, true);
        Thread.sleep(10);
        CNC1BackUp.uaClientService.writeNodeValue(CNC1BackUpNodeIds.initialFlag, false);
        CNC1BackUp.uaClientService.writeNodeValue(CNC1BackUpNodeIds.startFlag, true);
    }
}
