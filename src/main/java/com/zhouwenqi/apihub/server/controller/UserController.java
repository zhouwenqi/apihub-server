package com.zhouwenqi.apihub.server.controller;

import com.zhouwenqi.apihub.server.base.BaseController;
import com.zhouwenqi.apihub.server.entity.Member;
import com.zhouwenqi.apihub.server.entity.User;
import com.zhouwenqi.apihub.server.model.RoleLevel;
import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.service.MemberService;
import com.zhouwenqi.apihub.server.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public ResponseModel info() throws Exception{
        ResponseModel responseModel = ResponseModel.getSuccess();
        List<User> users = new ArrayList<User>();
        for(int i=0;i<5;i++){
            User user = new User();
            user.setUid("9069964@qq.com:["+i+"]");
            user.setNickName("zhouwenqi");
            user.setCreateDate(new Date());
            user.setEditDate(new Date());
            user.setPhone("18665111530");
            user.setIsAdmin(false);
            user.setPhoto("http://www.microwarp.com/api.jpg");
            user.setPwd(DigestUtils.md5Hex(new String("123456").getBytes()).toLowerCase());
            user.setEmail("9069964@qq.com");
            users.add(user);
        }
        userService.insertAll(users);
        List<Member> list = memberService.findAll();
        responseModel.addData("list",list);
        return responseModel;
    }
}
