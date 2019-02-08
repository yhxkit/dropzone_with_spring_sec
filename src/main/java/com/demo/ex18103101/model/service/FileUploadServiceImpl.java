package com.demo.ex18103101.model.service;


import com.demo.ex18103101.entity.File;
import com.demo.ex18103101.model.repository.FileUploadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService{

    @Autowired
    private FileUploadRepository fileUploadRepository;


    @Override
    public void save(File file){
        //저장하기 전에 기존의 최신 파일을 구 파일로 바꾸고 새로 넣은 파일을 최신으로 갱신
        Optional<File> latestFile = whatIsTheLatestOne(file);
        if(latestFile.isPresent()){
            File oldFile = latestFile.get();
            oldFile.setNewest(false);
        }
        fileUploadRepository.save(file);
    }

    @Override
    public void delete(File file) {
        fileUploadRepository.delete(file);
    }

    @Override
    public File findFileWithName(String fileName) {
        return fileUploadRepository.findByFileName(fileName);
    }

    public Optional<File> whatIsTheLatestOne(File file){
        Optional<File> result = Optional.ofNullable(fileUploadRepository.findByRoleAndNewest(file.getRole(), true));
        return result;
    }

}
