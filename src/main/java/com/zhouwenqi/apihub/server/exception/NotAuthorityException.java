package com.zhouwenqi.apihub.server.exception;

import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.model.response.ResultCode;

/**
 * 权限不够异常
 * Created by zhouwenqi on 2018/6/6.
 */
public class NotAuthorityException extends BaseApiException {
    public NotAuthorityException(){
        this.responseModel = ResponseModel.getNotAuthority();
    }
    public NotAuthorityException(String msg){
        super(ResultCode.RESULT_NOT_AUTHORITY,msg);
    }
    public NotAuthorityException(ResponseModel responseModel){
        super(responseModel);
    }
}
