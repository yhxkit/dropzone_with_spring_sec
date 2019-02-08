package com.demo.ex18103101.controller;

import com.demo.ex18103101.entity.File;
import com.demo.ex18103101.entity.Test;
import com.demo.ex18103101.model.service.FileUploadService;
import com.demo.ex18103101.model.service.TestService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
@org.springframework.stereotype.Controller
public class Controller {

    private final String FILE_UPLOAD_PATH = "C:\\Users\\yhxkitest-INT\\IdeaProjects\\ex18103101\\src\\main\\resources\\static\\uploadedFiles\\";


    @Autowired
    private TestService testService;
    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/")
    public String main(){
//        testService.save(new Test("테스터입니다"));
//        return "main1";
        return "main";
    }


    @ResponseBody
    @GetMapping("/test")
    public Iterable<Test> getAll(){
        log.info("가져가라~");
        return testService.getTests();
    }

    @GetMapping("/react")
    public String react(){
        log.info("되나..");
        return "react/test/react";
    }

    @GetMapping("/img")
    public String img(){
        return "image_upload_form";
    }



    @DeleteMapping("/del")
    public Object del(){
            //파일 리스팅해놓고 받던가 말던가 허어어어 ㅠㅠ
        String fileToDel = "1540973245166_2646803553AD368003.jpeg";
        try {
            Optional<File> fileOp = Optional.ofNullable(fileUploadService.findFileWithName(fileToDel));
            if(fileOp.isPresent()){
                fileUploadService.delete(fileOp.get());
                //우선 db에서 삭제하고 //없을 경우 대비해서 널러블,,
                // db에 파일이 있으면 실제 파일도 삭제해도 되고.... 어차피 얘는 파일 없어도 익셉션 발생 안하니까 굳이 db가 없다고 삭제안할 필요는 없는 느낌
            }
            Files.deleteIfExists(Paths.get(FILE_UPLOAD_PATH+fileToDel));
            log.info("파일 삭제:" + fileToDel);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return getReturnMessage("success");// 그냥 처리만하면 되지 왜 반환값없다고 500에러 띄우지..?
    }



    @PostMapping("/upload1")
//    @RequestMapping(value = "/upload1", method = {RequestMethod.POST})
    public @ResponseBody
    Object upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        log.info("업로드1");
        return uploading(file, request, File.ROLE.BANNER_MAIN);
    }


    @PostMapping("/upload2")
//    @RequestMapping(value = "/upload2", method = {RequestMethod.POST})
    public @ResponseBody
    Object upload2(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        log.info("업로드2");
        return uploading(file, request, File.ROLE.BACKGROUND);
    }


    public Object uploading(MultipartFile file, HttpServletRequest request, File.ROLE role) {
        log.debug("파일을 업로드 합니다.");
        if (file.isEmpty()) {
            //   request.setAttribute("message", "업로드할 파일 없음");
            //  return "uploadStatus";
            return getReturnMessage("error");
        }

        try {
            byte[] bytes = file.getBytes();
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            log.info("저장되는 파일명 : "+fileName);
//            Path path = Paths.get( FILE_UPLOAD_PATH  + fileName); //파일명이 동일하면 덮어쓰기 때문에 중복되지 않도록 업로드 시간을 접두...
//            Files.write(path, bytes);
//            fileUploadService.save(new File(fileName, File.ROLE.valueOf(role.toString()))); // db 저장

            request.setAttribute("message", "업로드 성공 '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return getReturnMessage("success");

    }



    private ResponseEntity<?> getReturnMessage(String result) { //ㅋㅓㅇㅓㅓ,,.,
        JSONObject jsonObject = null;
        HttpStatus resultStatus = null;

        try {
            jsonObject = new JSONObject();

            if(result.equals("error")){
                resultStatus =  HttpStatus.INTERNAL_SERVER_ERROR;
                jsonObject.put("error", "에러 뜨게 합니다"); //실패일 경우
            }else {
                resultStatus= HttpStatus.OK;
                jsonObject.put("success", "성공했ㄸ"); //성공일 경우.... 클라이언트쪽에서 serverResponse로 받아서 처리 가능
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultStatus =  HttpStatus.INTERNAL_SERVER_ERROR;
            jsonObject.put("error", "익셉션 발생~"); //실패일 경우
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new org.springframework.http.ResponseEntity<>(jsonObject, headers, resultStatus); // 무조건 석세스
    }



}
