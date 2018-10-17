package com.zhouwenqi.apihub.server.repository;

import com.zhouwenqi.apihub.server.entity.Api;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao - 接口
 * Created by zhouwenqi on 2018/10/16.
 */
@Repository("apiRespository")
public interface ApiRespository extends MongoRepository<Api,String> {
}
