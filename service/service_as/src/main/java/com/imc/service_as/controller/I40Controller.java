package com.imc.service_as.controller;

import com.imc.siemens_aas.i4_0.message.Message;
import com.imc.siemens_aas.i4_0.statemachine.ProviderStateMach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aas/i40")
@CrossOrigin
public class I40Controller {

//    @Autowired
//    private ProviderStateMach providerStateMach;

    @PostMapping
    public ResponseEntity recvMessage(Message message) {
//        String res = providerStateMach.handle(message);
        return ResponseEntity.ok("");
//        return ResponseEntity.ok(res);
    }
}
