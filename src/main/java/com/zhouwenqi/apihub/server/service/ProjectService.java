package com.zhouwenqi.apihub.server.service;

import com.zhouwenqi.apihub.server.entity.Project;
import com.zhouwenqi.apihub.server.model.HubStatus;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Service - 项目
 * Created by zhouwenqi on 2018/9/25.
 */
public class ProjectService extends BaseService<Project> {
    /**
     * 查询用户创建的项目列表
     * @param userId 用户ObjectId
     * @return
     */
    public List<Project> findByUserId(ObjectId userId){
        Criteria criteria = Criteria.where("userId").is(userId);
        criteria.and("status").is(HubStatus.ON);
        Query query = new Query(criteria);
        return mongoTemplate.find(query,Project.class);
    }

    /**
     * 查询用户创建的项目列表
     * @param userId 用户字符串id
     * @return
     */
    public List<Project> findByUserId(String userId){
        ObjectId objectId = new ObjectId(userId);
        return findByUserId(objectId);
    }
}
