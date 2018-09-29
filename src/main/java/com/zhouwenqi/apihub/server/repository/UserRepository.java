package com.zhouwenqi.apihub.server.repository;

import com.zhouwenqi.apihub.server.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao - 用户
 * Created by zhouwenqi on 2018/9/28.
 */
@Repository("userRepository")
public interface UserRepository extends MongoRepository<User,String> {

}
