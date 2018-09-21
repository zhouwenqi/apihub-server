package com.zhouwenqi.apihub.server.base;

import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by zhouwenqi on 2018/6/6.
 */
@RestController
@RequestMapping
public class ApiErrorController extends BasicErrorController {
    public ApiErrorController(){
        super(new DefaultErrorAttributes(),new ErrorProperties());
    }
    @RequestMapping("/error")
    public ResponseModel error(HttpServletRequest request, HttpServletResponse response){
        Map body = this.getErrorAttributes(request, this.isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = this.getStatus(request);
        ResponseModel responseModel = new ResponseModel(status.value(),body.get("message").toString());
        response.setStatus(status.value());
        return responseModel;
    }
}
