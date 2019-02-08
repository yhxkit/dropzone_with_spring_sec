package com.demo.ex18103101.model.repository;

import com.demo.ex18103101.entity.Test;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface TestRepository extends CrudRepository<Test, Integer> {


}
