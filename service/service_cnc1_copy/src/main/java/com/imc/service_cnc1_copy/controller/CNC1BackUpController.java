package com.imc.service_cnc1_copy.controller;

import com.imc.service_cnc1_copy.entity_submodel.CNC1Copy;
import com.imc.service_cnc1_copy.entity_submodel.CNC1CopyNodeIds;
import com.imc.service_cnc1_copy.entity_submodel.CNC1Copy_Instance_FactoryIO;
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
        CNC1Copy.readValue();
        CNC1Copy_Instance_FactoryIO cnc1Copy_instance_factoryIO = CNC1Copy.getCnc1Copy_instance_factoryIO();
        return new ResponseEntity(cnc1Copy_instance_factoryIO, HttpStatus.OK);
    }

    @PutMapping("/faultSimulation/{value}")
    public ResponseEntity setFaultSimulation(@PathVariable Short value) throws Exception {
        CNC1Copy.uaClientService.writeNodeValue(CNC1CopyNodeIds.CNC1Copy_faultSimulation, value);
        return ResponseEntity.ok("");
    }

    @GetMapping("/Status")
    public ResponseEntity getAsStatus() {
        CNC1Copy_Status status = CNC1Copy.getStatus();
        return new ResponseEntity(status, HttpStatus.OK);
    }

    @PutMapping("/StartFlag/{value}")
    public ResponseEntity setStartFlag(@PathVariable Short value) throws Exception {
        Boolean aStatus = CNC1Copy.uaClientService.writeNodeValue(CNC1CopyNodeIds.startFlag, value);
        if (!aStatus) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
        return ResponseEntity.ok("");
    }

    @PutMapping("/StopFlag/{value}")
    public ResponseEntity setStopFlag(@PathVariable Short value) throws Exception {
        Boolean aStatus = CNC1Copy.uaClientService.writeNodeValue(CNC1CopyNodeIds.stopFlag, value);
        if (!aStatus) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
        return ResponseEntity.ok("");
    }

    @PutMapping("/InitialFlag/{value}")
    public ResponseEntity setInitialFlag(@PathVariable Short value) throws Exception {
        Boolean aStatus = CNC1Copy.uaClientService.writeNodeValue(CNC1CopyNodeIds.initialFlag, value);
        if (!aStatus) {
            throw new RuntimeException("opc ua写入节点信息出错");
        }
        return ResponseEntity.ok("");
    }
}