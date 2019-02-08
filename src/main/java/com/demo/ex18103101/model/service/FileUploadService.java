package com.demo.ex18103101.model.service;


import com.demo.ex18103101.entity.File;

public interface FileUploadService {
    void save(File file);
    void delete(File file);
    File findFileWithName(String fileName);
}
