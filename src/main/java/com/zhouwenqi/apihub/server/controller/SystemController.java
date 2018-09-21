package com.zhouwenqi.apihub.server.controller;

import com.zhouwenqi.apihub.server.base.BaseController;
import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统
 * Created by zhouwenqi on 2018/9/21.
 */
@RestController
public class SystemController extends BaseController {
    /**
     * 测试服务器
     * @return
     */
    @GetMapping("/test")
    @ResponseBody
    public ResponseModel test(){
        return ResponseModel.getSuccess();
    }
}
