package com.demo.ex18103101.model.service;

import com.demo.ex18103101.entity.Test;

public interface TestService {
    void save(Test test);
    Iterable<Test> getTests();

}
