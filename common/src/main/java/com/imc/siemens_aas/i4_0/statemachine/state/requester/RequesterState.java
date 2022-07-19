package com.imc.siemens_aas.i4_0.statemachine.state.requester;


import com.imc.siemens_aas.i4_0.message.Message;

public interface RequesterState {
    void doExecute(RequesterContext context, Message msg);
}
