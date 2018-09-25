package com.zhouwenqi.apihub.server.exception;

import com.zhouwenqi.apihub.server.model.response.ResponseModel;

/**
 * Created by zhouwenqi on 2018/6/6.
 */
public class BaseApiException extends RuntimeException {
    public ResponseModel responseModel;
    public BaseApiException(){

    }
    public BaseApiException(int errorCode,String message){
        this.responseModel = new ResponseModel(errorCode,message);
    }
    public BaseApiException(ResponseModel responseModel){
        this.responseModel = responseModel;
    }
    public ResponseModel getResponseModel(){
        return this.responseModel;
    }
    public void setResponseModel(ResponseModel responseModel){
        this.responseModel = responseModel;
    }
}
