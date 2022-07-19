package com.imc.service_cnc1.controller;

import com.imc.service_cnc1.entity_submodel.CNC1;
import com.imc.service_cnc1.entity_submodel.CNC1NodeIds;
import com.imc.service_cnc1.entity_submodel.CNC1_Instance_FactoryIO;
import com.imc.service_cnc1.entity_submodel.metacnc1.CNC1_Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("CNC1")
public class CNC1Controller {

    /**
     * 请求factory相关数据
     * @return
     */
    @GetMapping("/Data")
    public ResponseEntity getFactoryIO() {
        CNC1.readValue();
        CNC1_Instance_FactoryIO cnc1_instance_factoryIO = CNC1.getCnc1_instance_factoryIO();
        return new ResponseEntity(cnc1_instance_factoryIO, HttpStatus.OK);
    }

    @PutMapping("/rawColor/{value}")
    public ResponseEntity setRawColor(@PathVariable Short value) throws Exception {
        CNC1.uaClientService.writeNodeValue(CNC1NodeIds.CNC1_rawColor, value);
        return ResponseEntity.ok("");
    }

    @PutMapping("/type/{value}")
    public ResponseEntity setType(@PathVariable Boolean value) throws Exception {
        CNC1.uaClientService.writeNodeValue(CNC1NodeIds.CNC1_type, value);
        return ResponseEntity.ok("");
    }

    @PutMapping("/faultSimulation/{value}")
    public ResponseEntity setFaultSimulation(@PathVariable Boolean value) throws Exception {
        CNC1.uaClientService.writeNodeValue(CNC1NodeIds.CNC1_faultSimulation, value);
        return ResponseEntity.ok("");
    }

    @GetMapping("/Status")
    public ResponseEntity getAsStatus() {
        CNC1_Status status = CNC1.getStatus();
        return new ResponseEntity(status, HttpStatus.OK);
    }

    @PutMapping("/StartFlag/{value}")
    public ResponseEntity setStartFlag(@PathVariable Boolean value) throws Exception {
        Boolean aStatus = CNC1.uaClientService.writeNodeValue(CNC1NodeIds.startFlag, value);
        if (!aStatus) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
        return ResponseEntity.ok("");
    }

    @PutMapping("/StopFlag/{value}")
    public ResponseEntity setStopFlag(@PathVariable Boolean value) throws Exception {
        Boolean aStatus = CNC1.uaClientService.writeNodeValue(CNC1NodeIds.stopFlag, value);
        if (!aStatus) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
        return ResponseEntity.ok("");
    }

    @PutMapping("/InitialFlag/{value}")
    public ResponseEntity setInitialFlag(@PathVariable Boolean value) throws Exception {
        Boolean aStatus = CNC1.uaClientService.writeNodeValue(CNC1NodeIds.initialFlag, value);
        if (!aStatus) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
        return ResponseEntity.ok("");
    }
}