package com.futura.Purple.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.futura.Purple.Entity.ParameterTable;
import com.futura.Purple.Service.ParameterService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/param")
public class ParameterController {

    @Autowired
    private ParameterService paramService;

    @GetMapping("/getAll")
    public List<ParameterTable> getallparams(){
        return paramService.getAllParams();
    }

    @GetMapping("/{rule}")
    public List<ParameterTable> getruleone(@PathVariable String rule){
        return paramService.getRuleOne(rule);
    }
}
