package com.zhouwenqi.apihub.server.service;

import com.zhouwenqi.apihub.server.common.JwtUser;
import com.zhouwenqi.apihub.server.entity.User;
import com.zhouwenqi.apihub.server.model.HubStatus;
import com.zhouwenqi.apihub.server.model.request.ReqRegister;
import com.zhouwenqi.apihub.server.repository.UserRepository;
import com.zhouwenqi.apihub.server.repository.UserVerifyRepository;
import com.zhouwenqi.apihub.server.utils.jwtUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Service - 用户
 * Created by zhouwenqi on 2018/9/21.
 */
@Service("userService")
public class UserService extends BaseService<User> {

    /**
     * 根据用户名查询用户信息
     * @param uid 用户名
     * @return
     */
    public User findByUid(String uid){
        System.out.println("uid:"+uid);
        Criteria criteria = Criteria.where("uid").is(uid);
        Query query = new Query(criteria);
        return mongoTemplate.findOne(query,User.class);
    }

    /**
     * 注册用户
     * @param reqRegister 用户信息
     * @return
     */
    public User regsiter(ReqRegister reqRegister){
        String pwd = DigestUtils.md5Hex(reqRegister.getPwd()).toLowerCase();
        User user = new User();
        user.setUid(reqRegister.getUid());
        user.setNickName(reqRegister.getNickName());
        user.setPhone(reqRegister.getPhone());
        user.setPhoto(reqRegister.getPhoto());
        user.setPwd(pwd);
        user.setCreateDate(new Date());
        user.setEditDate(new Date());
        user.setIsAdmin(false);
        user.setStatus(HubStatus.ON);
        super.insert(user,"user");
        return user;
    }

    /**
     * 获取用户token
     * @param user 用户信息
     * @return
     */
    public String getToken(User user){
        JwtUser jwtUser = new JwtUser();
        jwtUser.setId(user.getId().toString());
        jwtUser.setUid(user.getUid());
        return jwtUtils.encode(jwtUser);
    }

}
