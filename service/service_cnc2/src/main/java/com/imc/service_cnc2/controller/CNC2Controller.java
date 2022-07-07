package com.imc.service_cnc2.controller;

import com.imc.service_cnc2.entity_submodel.CNC2;
import com.imc.service_cnc2.entity_submodel.CNC2NodeIds;
import com.imc.service_cnc2.entity_submodel.CNC2_Instance_FactoryIO;
import com.imc.service_cnc2.entity_submodel.metacnc2.CNC2_Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("CNC2")
public class CNC2Controller {

    /**
     * 请求factory相关数据
     * @return
     */
    @GetMapping("/Data")
    public ResponseEntity getFactoryIO() {
        CNC2.readValue();
        CNC2_Instance_FactoryIO cnc2_instance_factoryIO = CNC2.getCnc2_instance_factoryIO();
        return new ResponseEntity(cnc2_instance_factoryIO, HttpStatus.OK);
    }

    @PutMapping("/rawColor/{value}")
    public ResponseEntity setRawColor(@PathVariable Short value) throws Exception {
        CNC2.uaClientService.writeNodeValue(CNC2NodeIds.CNC2_rawColor, value);
        return ResponseEntity.ok("");
    }

    @PutMapping("/type/{value}")
    public ResponseEntity setType(@PathVariable Short value) throws Exception {
        CNC2.uaClientService.writeNodeValue(CNC2NodeIds.CNC2_type, value);
        return ResponseEntity.ok("");
    }

    @PutMapping("/faultSimulation/{value}")
    public ResponseEntity setFaultSimulation(@PathVariable Short value) throws Exception {
        CNC2.uaClientService.writeNodeValue(CNC2NodeIds.CNC2_faultSimulation, value);
        return ResponseEntity.ok("");
    }

    @GetMapping("/Status")
    public ResponseEntity getAsStatus() {
        CNC2_Status status = CNC2.getStatus();
        return new ResponseEntity(status, HttpStatus.OK);
    }

    @PutMapping("/StartFlag/{value}")
    public ResponseEntity setStartFlag(@PathVariable Short value) throws Exception {
        Boolean aStatus = CNC2.uaClientService.writeNodeValue(CNC2NodeIds.startFlag, value);
        if (!aStatus) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
        return ResponseEntity.ok("");
    }

    @PutMapping("/StopFlag/{value}")
    public ResponseEntity setStopFlag(@PathVariable Short value) throws Exception {
        Boolean aStatus = CNC2.uaClientService.writeNodeValue(CNC2NodeIds.stopFlag, value);
        if (!aStatus) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
        return ResponseEntity.ok("");
    }

    @PutMapping("/InitialFlag/{value}")
    public ResponseEntity setInitialFlag(@PathVariable Short value) throws Exception {
        Boolean aStatus = CNC2.uaClientService.writeNodeValue(CNC2NodeIds.initialFlag, value);
        if (!aStatus) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
        return ResponseEntity.ok("");
    }

}