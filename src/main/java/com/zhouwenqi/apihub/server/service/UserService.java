package com.zhouwenqi.apihub.server.service;

import com.zhouwenqi.apihub.server.entity.User;
import org.springframework.stereotype.Service;

/**
 * Service - 用户
 * Created by zhouwenqi on 2018/9/21.
 */
@Service("userService")
public class UserService extends BaseService {
    public User insert(User user){
        mongoTemplate.insert(user,"user");
        return user;
    }
}
