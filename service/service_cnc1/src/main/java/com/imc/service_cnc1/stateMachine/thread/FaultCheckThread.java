package com.imc.service_cnc1.stateMachine.thread;

import com.imc.service_cnc1.entity_submodel.CNC1;
import com.imc.service_cnc1.entity_submodel.CNC1NodeIds;
import com.imc.service_cnc1.stateMachine.ProStateMach;
import com.imc.siemens_aas.i4_0.statemachine.ProviderStateMach;
import com.imc.siemens_aas.i4_0.statemachine.state.provider.Error;
import lombok.SneakyThrows;

/**
 * 错误扫描线程，如果发生错误，就让状态机进入Error状态，并进行处理
 */
public class FaultCheckThread extends Thread{

    private ProviderStateMach stateMach;
    private ProStateMach proStateMach;

    public FaultCheckThread(ProStateMach proStateMach) {
        this.proStateMach = proStateMach;
        this.stateMach = proStateMach.stateMach;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            //周期为10ms
            Thread.sleep(1000);
            Boolean faultSimulation = (Boolean) CNC1.uaClientService.readNode(CNC1NodeIds.CNC1_faultSimulation);
            if (faultSimulation) {
                //将工位停止
                CNC1.uaClientService.writeNodeValue(CNC1NodeIds.stopFlag, true);
                //重新执行I4.0对工位的配置
                stateMach.changeState(Error.getInstance());
                stateMach.handle(null);
                proStateMach.restart();
                //处理完成之后跳出循环
                break;
            }
        }
    }
}
