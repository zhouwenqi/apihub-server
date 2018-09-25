package com.zhouwenqi.apihub.server.exception;

import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.model.response.ResultCode;

/**
 * 缺少参数异常
 * Created by zhouwenqi on 2018/6/6.
 */
public class NotParameterException extends BaseApiException {
    public NotParameterException(){
        this.responseModel = ResponseModel.getNotParameter();
    }
    public NotParameterException(String msg){
        super(ResultCode.RESULT_NOT_PARAMETER,msg);
    }
    public NotParameterException(ResponseModel responseModel){
        super(responseModel);
    }
}
