package com.imc.service_as.controller;

import com.imc.service_as.entity_submodel.AS;
import com.imc.service_as.entity_submodel.AS_Instance_FactoryIO;
import com.imc.service_as.entity_submodel.AS_Service;
import com.imc.service_as.entity_submodel.AsNodeIds;
import com.imc.service_as.entity_submodel.metaas.AS_Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("AS")
public class ASController {

    /**
     * 请求factory相关数据
     * @return
     */
    @GetMapping("/Data")
    public ResponseEntity getFactoryIO() {
        AS.readValue();
        AS_Instance_FactoryIO as_instance_factoryIO = AS.getAs_instance_factoryIO();
        return new ResponseEntity(as_instance_factoryIO, HttpStatus.OK);
    }

    @PutMapping("/AS_AssembleStrategy/{value}")
    public ResponseEntity setAsMode(@PathVariable int value) {
        AS_Service.AS_AssembleStrategy(value);
        AS_Status status = AS.getStatus();
        return new ResponseEntity(status, HttpStatus.OK);
    }

    @GetMapping("/Status")
    public ResponseEntity getAsStatus() {
        AS_Status status = AS.getStatus();
        return new ResponseEntity(status, HttpStatus.OK);
    }

    @PutMapping("/StartFlag/{value}")
    public ResponseEntity setStartFlag(@PathVariable Boolean value) throws Exception {
        Boolean aStatus = AS.uaClientService.writeNodeValue(AsNodeIds.StartFlag, value);
        if (!aStatus) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
        return ResponseEntity.ok("");
    }

    @PutMapping("/StopFlag/{value}")
    public ResponseEntity setStopFlag(@PathVariable Boolean value) throws Exception {
        Boolean aStatus = AS.uaClientService.writeNodeValue(AsNodeIds.StopFlag, value);
        if (!aStatus) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
        return ResponseEntity.ok("");
    }

    @PutMapping("/InitialFlag/{value}")
    public ResponseEntity setInitialFlag(@PathVariable Boolean value) throws Exception {
        Boolean aStatus = AS.uaClientService.writeNodeValue(AsNodeIds.InitialFlag, value);
        if (!aStatus) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
        return ResponseEntity.ok("");
    }
}
