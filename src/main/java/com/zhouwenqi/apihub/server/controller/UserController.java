package com.zhouwenqi.apihub.server.controller;

import com.zhouwenqi.apihub.server.base.BaseController;
import com.zhouwenqi.apihub.server.entity.Member;
import com.zhouwenqi.apihub.server.entity.User;
import com.zhouwenqi.apihub.server.model.RoleLevel;
import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.service.MemberService;
import com.zhouwenqi.apihub.server.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by zhouwenqi on 2018/9/21.
 */
@RestController("userController")
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private MemberService memberService;

    @GetMapping("info")
    @ResponseBody
    public ResponseModel info(){
        ResponseModel responseModel = ResponseModel.getSuccess();

        List<Member> list = memberService.findAll();
        responseModel.addData("list",list);
        return responseModel;
    }
}
