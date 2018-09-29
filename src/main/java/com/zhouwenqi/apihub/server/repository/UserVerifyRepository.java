package com.zhouwenqi.apihub.server.repository;

import com.zhouwenqi.apihub.server.entity.UserVerify;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao - 验证码
 * Created by zhouwenqi on 2018/9/28.
 */
@Repository("userVerifyRepository")
public interface UserVerifyRepository extends MongoRepository<UserVerify,String> {
}
