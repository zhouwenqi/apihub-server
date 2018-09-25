package com.zhouwenqi.apihub.server.exception;

import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.model.response.ResultCode;

/**
 * Token错误异常
 * Created by zhouwenqi on 2018/6/6.
 */
public class FailedException extends BaseApiException {
    public FailedException(){
        this.responseModel = ResponseModel.getTokenError();
    }
    public FailedException(String msg){
        super(ResultCode.RESULT_FAILED,msg);
    }
    public FailedException(ResponseModel responseModel){
        super(responseModel);
    }
}
