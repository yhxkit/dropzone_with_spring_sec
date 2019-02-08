package com.demo.ex18103101.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultMessage<T>{
    String result;
    String message;
    T data;
    boolean immediateReturn;

    public ResultMessage(String result, String message, boolean immediateReturn){
        this.result = result;
        this.message = message;
        this.immediateReturn = immediateReturn;
    }
}
