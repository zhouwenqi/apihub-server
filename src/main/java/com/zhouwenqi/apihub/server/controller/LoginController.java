package com.zhouwenqi.apihub.server.controller;

import com.zhouwenqi.apihub.server.base.BaseController;
import com.zhouwenqi.apihub.server.entity.User;
import com.zhouwenqi.apihub.server.model.HubStatus;
import com.zhouwenqi.apihub.server.model.request.ReqLogin;
import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.service.MemberService;
import com.zhouwenqi.apihub.server.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * 登录
 * Created by zhouwenqi on 2018/9/21.
 */
@RestController
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private MemberService memberService;
    @Value("${app.user.login}")
    private Boolean isLogin;

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    @ResponseBody
    public ResponseModel login(ReqLogin reqLogin){
        ResponseModel responseModel = ResponseModel.getSuccess();
        if(!isLogin){
            responseModel = ResponseModel.getFailed();
            responseModel.setMsg("登录通道已关闭");
            logger.error("登录通道已关闭（开启配置:{app.user.login = true})");
            return responseModel;
        }
        // 校验参数
        if(null==reqLogin){
            responseModel = ResponseModel.getNotParameter();
            return responseModel;
        }
        if(StringUtils.isBlank(reqLogin.getUid()) || StringUtils.isBlank(reqLogin.getPwd())){
            responseModel = ResponseModel.getNotParameter();
            return responseModel;
        }
        // 获取用户信息
        User user = userService.findByUid(reqLogin.getUid());
        if(null == user){
            responseModel = ResponseModel.getFailed();
            responseModel.setMsg("登录帐号或密码错误");
            logger.error("登录帐号不存在:["+reqLogin.getUid()+"]");
            return responseModel;
        }
        String pwd = DigestUtils.md5Hex(reqLogin.getPwd()).toLowerCase();
        if(!user.getPwd().equals(pwd)){
            responseModel = ResponseModel.getFailed();
            responseModel.setMsg("登录帐号或密码错误");
            logger.error("登录密码错误 ("+reqLogin.getUid()+","+reqLogin.getPwd()+")");
            return responseModel;
        }

        if(!user.getStatus().equals(HubStatus.ON)){
            responseModel = ResponseModel.getFailed();
            responseModel.setMsg("帐号状态异常,请联系管理员");
            logger.error("帐号状态异常 (uid:"+reqLogin.getUid()+",status:"+user.getStatus()+")");
            return responseModel;
        }

        String token = userService.getToken(user);
        logger.info("登录成功(token:"+token+")");
        responseModel.addData("user",user);
        responseModel.addData("token",token);
        return responseModel;
    }
}
