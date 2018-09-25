package com.zhouwenqi.apihub.server.exception;

import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.model.response.ResultCode;

/**
 * Token错误异常
 * Created by zhouwenqi on 2018/6/6.
 */
public class TokenErrorException extends BaseApiException {
    public TokenErrorException(){
        this.responseModel = ResponseModel.getTokenError();
    }
    public TokenErrorException(String msg){
        super(ResultCode.RESULT_TOKEN_ERROR,msg);
    }
    public TokenErrorException(ResponseModel responseModel){
        super(responseModel);
    }
}
