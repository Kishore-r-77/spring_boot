package com.futura.Purple.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futura.Purple.Entity.ParameterTable;
import com.futura.Purple.Repository.ParameterRepository;

import java.util.List;

@Service
public class ParameterService {

    @Autowired
    private ParameterRepository paramRepo;


    public List<ParameterTable> getAllParams(){
        return paramRepo.findAll();
    }

    public List<ParameterTable> getRuleOne(String rule){
        return paramRepo.getParameterwithRuleone(rule);
    }

}
