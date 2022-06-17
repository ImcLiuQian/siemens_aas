package com.imc.service_as.controller;


import com.imc.siemens_aas.aasenv.submodel.Submodel;
import com.imc.siemens_aas.aasenv.submodel.SubmodelFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/aas/AS")
@CrossOrigin
public class SubmodelController {
    /**
     * 通过读取文件生成子模型
     * @return
     */
    @PostMapping
    public ResponseEntity addSubmodelFromJson(@RequestBody String submodelStr) {
        Submodel submodel = SubmodelFactory.createByJson(submodelStr);
        return ResponseEntity.ok(submodel);
    }

    @GetMapping("test")
    public ResponseEntity test() {
        return ResponseEntity.ok("test ok");
    }
}
