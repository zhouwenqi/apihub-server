package com.zhouwenqi.apihub.server.controller;

import com.zhouwenqi.apihub.server.base.BaseController;
import com.zhouwenqi.apihub.server.entity.User;
import com.zhouwenqi.apihub.server.entity.UserVerify;
import com.zhouwenqi.apihub.server.model.HubStatus;
import com.zhouwenqi.apihub.server.model.request.ReqVerify;
import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.model.response.ResultCode;
import com.zhouwenqi.apihub.server.repository.UserRepository;
import com.zhouwenqi.apihub.server.service.MailService;
import com.zhouwenqi.apihub.server.service.UserService;
import com.zhouwenqi.apihub.server.service.UserVerifyService;
import com.zhouwenqi.apihub.server.utils.regexUtils;
import com.zhouwenqi.apihub.server.utils.stringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

/**
 * 验证
 * Created by zhouwenqi on 2018/9/26.
 */
@RestController
@RequestMapping("/verify")
public class UserVerifyController extends BaseController {
    @Autowired
    private UserVerifyService userVerifyService;
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(RegisterController.class);

    /**
     * 获取验证码
     * @param email email
     * @param codeType 验证码类型(0:注册新帐号,1:帐号验证)
     * @return
     */
    @GetMapping("send")
    public ResponseModel send(String email,Integer codeType){
        ResponseModel responseModel = ResponseModel.getSuccess();
        // 参数验校
        if(StringUtils.isBlank(email) || null==codeType){
            responseModel = ResponseModel.getNotParameter();
            return responseModel;
        }
        User user = userService.findByUid(email);
        if(codeType != 0){
            if(null == user){
                responseModel = ResponseModel.getFailed();
                responseModel.setMsg("用户不存在");
                return responseModel;
            }
            if(!user.getStatus().equals(HubStatus.ON)){
                responseModel = ResponseModel.getFailed();
                responseModel.setMsg("用户状态异常");
                return responseModel;
            }
        } else {
            if(null != user){
                responseModel = ResponseModel.getFailed();
                responseModel.setMsg("此帐号已被注册");
                return responseModel;
            }
        }

        // 设置过期时间
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MINUTE,5);
        Date expireDate = calendar.getTime();
        // 生成验证码
        String code = stringUtils.getVerifyCode(6).toUpperCase();
        UserVerify userVerify = userVerifyService.find(email,codeType);
        if(null==userVerify || userVerify.getExpireDate()==null || userVerify.getExpireDate().before(currentDate)){
            userVerify = new UserVerify();
            userVerify.setCode(code);
            userVerify.setEmail(email);
            userVerify.setCodeType(codeType);
            userVerify.setCreateDate(new Date());
            userVerify.setExpireDate(expireDate);
            if(user != null){
                userVerify.setUserId(user.getId());
            }
            userVerifyService.insert(userVerify,"userVerify");
            mailService.sendVerifyEmail(email,userVerify);
            logger.info("验证码已发送(email = "+email+", code = " + code + ", codeType = "+codeType);
        }
        responseModel.setMsg("验证码已发送到:"+email);
        return responseModel;
    }

    /**
     * 验证码校验
     * @param reqVerify 校验信息
     * @return
     */
    @GetMapping(value = "vaild")
    @ResponseBody
    public ResponseModel vaild(ReqVerify reqVerify){
        ResponseModel responseModel = ResponseModel.getSuccess();
        if(StringUtils.isBlank(reqVerify.getEmail()) || StringUtils.isBlank(reqVerify.getVerifyCode()) || null==reqVerify.getCodeType()){
            responseModel = ResponseModel.getNotParameter();
            return responseModel;
        }

        UserVerify userVerify = userVerifyService.find(reqVerify);
        if(null==userVerify){
            responseModel = ResponseModel.getFailed();
            responseModel.setMsg("无效的验证码");
            return responseModel;
        }
        if(userVerify.getExpireDate().before(new Date())){
            responseModel = ResponseModel.getFailed();
            responseModel.setMsg("验证码已过期");
            return  responseModel;
        }
        return responseModel;
    }

    /**
     * 重置密码
     * @param reqVerify 重置信息
     * @return
     */
    @PutMapping(value = "resetPassword")
    @ResponseBody
    public ResponseModel resetPassword(ReqVerify reqVerify){
        ResponseModel responseModel = ResponseModel.getSuccess();
        reqVerify.setCodeType(1);
        responseModel =  vaild(reqVerify);
        if(responseModel.getCode()!= ResultCode.RESULT_SUCCESS){
            return responseModel;
        }
        if(!regexUtils.isPassword(reqVerify.getPassword())){
            responseModel = ResponseModel.getFailed();
            responseModel.setMsg("密码必须包含6-18位字母和数字组合");
            return responseModel;
        }
        User user = userService.findByUid(reqVerify.getEmail());
        if(null==user){
            responseModel = ResponseModel.getFailed();
            responseModel.setMsg("此帐号不存在");
            return responseModel;
        }

        // 保存用户信息
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setPwd(DigestUtils.md5Hex(reqVerify.getPassword()).toLowerCase());
        updateUser.setEditDate(new Date());
        userRepository.save(user);
        // 让验证码过期
        userVerifyService.lost(reqVerify);
        return responseModel;
    }

}
