package com.zhouwenqi.apihub.server.controller;

import com.zhouwenqi.apihub.server.entity.User;
import com.zhouwenqi.apihub.server.model.request.ReqRegister;
import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 注册
 * Created by zhouwenqi on 2018/9/25.
 */
@RestController
public class RegisterController {
    @Value("${app.user.register}")
    private Boolean isRegister;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @PostMapping("/register")
    public ResponseModel register(ReqRegister reqRegister){
        ResponseModel responseModel = ResponseModel.getSuccess();
        if(!isRegister){
            responseModel = ResponseModel.getFailed();
            responseModel.setMsg("当前注册通道已关闭");
            logger.error("当前注册通道已关闭（开启配置:{app.user.register = true}");
            return responseModel;
        }
        // 参数检查
        if(StringUtils.isBlank(reqRegister.getUid()) || StringUtils.isBlank(reqRegister.getPwd()) || StringUtils.isBlank(reqRegister.getNickName())){
            responseModel = ResponseModel.getNotParameter();
            responseModel.setMsg("用户名、密码、昵称必需填写");
            return responseModel;
        }
        User regUser = userService.findByUid(reqRegister.getUid());
        if(null!=regUser){
            responseModel = ResponseModel.getFailed();
            responseModel.setMsg("此用户已存在");
            return responseModel;
        }

        // 注册用户
        User user = userService.regsiter(reqRegister);
        if(null==user)
        {
            responseModel = ResponseModel.getFailed();
            responseModel.setMsg("注册失败");
            return responseModel;
        }
        responseModel.addData("user",user);
        return responseModel;
    }
}
