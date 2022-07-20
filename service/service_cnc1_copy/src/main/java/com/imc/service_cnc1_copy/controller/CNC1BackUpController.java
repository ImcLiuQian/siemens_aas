package com.imc.service_cnc1_copy.controller;

import com.imc.service_cnc1_copy.entity_submodel.CNC1BackUp;
import com.imc.service_cnc1_copy.entity_submodel.CNC1BackUp_Instance_FactoryIO;
import com.imc.service_cnc1_copy.entity_submodel.CNC1BackUpNodeIds;
import com.imc.service_cnc1_copy.entity_submodel.metacnc1_copy.CNC1Copy_Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("CNC1BackUp")
public class CNC1BackUpController {

    /**
     * 请求factory相关数据
     * @return
     */
    @GetMapping("/Data")
    public ResponseEntity getFactoryIO() {
        CNC1BackUp.readValue();
        CNC1BackUp_Instance_FactoryIO cnc1BackUp_instance_factoryIO = CNC1BackUp.getCnc1BackUp_instance_factoryIO();
        return new ResponseEntity(cnc1BackUp_instance_factoryIO, HttpStatus.OK);
    }

    @PutMapping("/faultSimulation/{value}")
    public ResponseEntity setFaultSimulation(@PathVariable Boolean value) throws Exception {
        CNC1BackUp.uaClientService.writeNodeValue(CNC1BackUpNodeIds.CNC1Copy_faultSimulation, value);
        return ResponseEntity.ok("");
    }

    @GetMapping("/Status")
    public ResponseEntity getAsStatus() {
        CNC1Copy_Status status = CNC1BackUp.getStatus();
        return new ResponseEntity(status, HttpStatus.OK);
    }

    @PutMapping("/StartFlag/{value}")
    public ResponseEntity setStartFlag(@PathVariable Boolean value) throws Exception {
        Boolean aStatus = CNC1BackUp.uaClientService.writeNodeValue(CNC1BackUpNodeIds.startFlag, value);
        if (!aStatus) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
        return ResponseEntity.ok("");
    }

    @PutMapping("/StopFlag/{value}")
    public ResponseEntity setStopFlag(@PathVariable Boolean value) throws Exception {
        Boolean aStatus = CNC1BackUp.uaClientService.writeNodeValue(CNC1BackUpNodeIds.stopFlag, value);
        if (!aStatus) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
        return ResponseEntity.ok("");
    }

    @PutMapping("/InitialFlag/{value}")
    public ResponseEntity setInitialFlag(@PathVariable Boolean value) throws Exception {
        Boolean aStatus = CNC1BackUp.uaClientService.writeNodeValue(CNC1BackUpNodeIds.initialFlag, value);
        if (!aStatus) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
        return ResponseEntity.ok("");
    }
}