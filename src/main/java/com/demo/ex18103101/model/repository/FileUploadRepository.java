package com.demo.ex18103101.model.repository;

import com.demo.ex18103101.entity.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends CrudRepository<File, Integer> {

    File findByRoleAndNewest(File.ROLE role, boolean newest);
    File findByFileName(String fileName);
}
