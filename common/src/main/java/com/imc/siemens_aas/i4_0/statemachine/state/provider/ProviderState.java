package com.imc.siemens_aas.i4_0.statemachine.state.provider;


import com.imc.siemens_aas.i4_0.message.Message;

public interface ProviderState {
    void doExecute(ProviderContext context, Message msg);
}
