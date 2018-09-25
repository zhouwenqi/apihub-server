package com.zhouwenqi.apihub.server.exception;

import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhouwenqi on 2018/6/6.
 */
@RestControllerAdvice
@ResponseBody
public class MappingExceptionHandler {
    @ExceptionHandler(BaseApiException.class)
    public ResponseModel mappingException(BaseApiException e, HttpServletResponse response) throws Exception{
        ResponseModel responseModel = e.responseModel;
        response.setStatus(e.getResponseModel().getCode());
        System.out.println("status:"+responseModel.getCode());
        return responseModel;
    }
}
