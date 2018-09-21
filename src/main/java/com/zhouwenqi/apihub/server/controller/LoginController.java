package com.zhouwenqi.apihub.server.controller;

import com.zhouwenqi.apihub.server.base.BaseController;
import com.zhouwenqi.apihub.server.model.request.ReqLogin;
import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 * Created by zhouwenqi on 2018/9/21.
 */
@RestController
public class LoginController extends BaseController {
    @GetMapping("/login")
    @ResponseBody
    public ResponseModel login(ReqLogin reqLogin){
        ResponseModel responseModel = ResponseModel.getSuccess();
        if(null==reqLogin){
            responseModel = ResponseModel.getNotParameter();
            return responseModel;
        }

        return responseModel;
    }
}
