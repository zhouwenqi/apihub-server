package com.zhouwenqi.apihub.server.service;

import com.zhouwenqi.apihub.server.common.JwtUser;
import com.zhouwenqi.apihub.server.entity.User;
import com.zhouwenqi.apihub.server.utils.jwtUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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
