package com.zhouwenqi.apihub.server.exception;

import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.model.response.ResultCode;

/**
 * 服务器错误异常
 * Created by zhouwenqi on 2018/6/6.
 */
public class ServiceErrorException extends BaseApiException {
    public ServiceErrorException(){
        this.responseModel = ResponseModel.getError();
    }
    public ServiceErrorException(String msg){
        super(ResultCode.RESULT_SERVICE_ERROR,msg);
    }
    public ServiceErrorException(ResponseModel responseModel){
        super(responseModel);
    }
}
