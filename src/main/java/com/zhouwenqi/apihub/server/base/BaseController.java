package com.zhouwenqi.apihub.server.base;

import com.zhouwenqi.apihub.server.common.DateSerialize;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Date;

/**
 * Controller - 基类
 * Created by zhouwenqi on 2018/6/5.
 */
public class BaseController {
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new DateSerialize(true));
    }
    public RequestAttributes getContextAttribute(){
        return RequestContextHolder.getRequestAttributes();
    }
}
