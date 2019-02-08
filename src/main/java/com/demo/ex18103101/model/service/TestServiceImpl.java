package com.demo.ex18103101.model.service;

import com.demo.ex18103101.entity.Test;
import com.demo.ex18103101.model.repository.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@org.springframework.stereotype.Service
public class TestServiceImpl implements TestService{


    @Autowired
    private TestRepository testRepository;

    @Override
    public void save(Test test){
        testRepository.save(test);
    }

    @Override
    public Iterable<Test> getTests(){
        return  testRepository.findAll();
    }


}
