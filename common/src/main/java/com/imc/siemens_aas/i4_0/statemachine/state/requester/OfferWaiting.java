package com.imc.siemens_aas.i4_0.statemachine.state.requester;

import com.imc.siemens_aas.i4_0.message.Message;
import lombok.extern.slf4j.Slf4j;


/**
 * 等待其他AAS回复offer信息
 */
@Slf4j
public class OfferWaiting implements RequesterState {
    private static OfferWaiting singleton = new OfferWaiting();

    public static OfferWaiting getInstance() {
        return singleton;
    }

    private OfferWaiting() {}

    @Override
    public void doExecute(RequesterContext context, Message msg) {
        //等待的具体过程在CFPSendingThread里面实现了
        //而且，在CFPSendingWaiting状态里面，线程join()之后，等待就结束了，结束之后才让OfferWaiting执行这里的doExecute
        //因此，这里只需要切换到评估状态，并且让context进行handle即可
        //当然，切换到评估状态之前，需要对context中的offerMsgs的内容进行判断
        //如果一条信息都没有返回，说明没有符合条件的aas能够提供offer
        //那么直接切换到call for proposal状态即可
        if (context.getOffers().size() == 0) {
            log.error("没有符合条件的aas能够提供offer，切换回call for Proposal状态");
            context.changeState(CFPSendingWaiting.getInstance());
        } else {//如果size不为0，说明有满足要求的aas提供offer，那么切换到评估状态进行评估
            context.changeState(OfferAssessing.getInstance());
            context.handle(null);//同样，不需要Message，因为评估的数据在context的HashMap offerMsgs中
        }
    }
}
