package com.zhouwenqi.apihub.server.exception;

import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.model.response.ResultCode;

/**
 * 参数错误异常
 * Created by zhouwenqi on 2018/6/6.
 */
public class ParameterErrorException extends BaseApiException {
    public ParameterErrorException(){
        this.responseModel = ResponseModel.getParameterError();
    }
    public ParameterErrorException(String msg){
        super(ResultCode.RESULT_PARAMETER_ERROR,msg);
    }
    public ParameterErrorException(ResponseModel responseModel){
        super(responseModel);
    }
}
