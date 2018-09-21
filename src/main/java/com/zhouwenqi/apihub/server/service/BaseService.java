package com.zhouwenqi.apihub.server.service;

import com.zhouwenqi.apihub.server.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

/**
 * Service - 基类
 * Created by zhouwenqi on 2018/9/21.
 */
public class BaseService {
    @Autowired
    protected MongoTemplate mongoTemplate;

    public List<Member> findAll(){
        return mongoTemplate.findAll(Member.class);
    }
}
