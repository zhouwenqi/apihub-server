package com.zhouwenqi.apihub.server.service;

import com.zhouwenqi.apihub.server.entity.Project;
import com.zhouwenqi.apihub.server.model.HubStatus;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service - 项目
 * Created by zhouwenqi on 2018/9/25.
 */
@Service("projectService")
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

    /**
     * 判断项目是否重复
     * @param project 项目信息
     * @return
     */
    public boolean findIsRepeat(Project project){
        Criteria criteria = Criteria.where("userId").is(project.getUserId());
        criteria.and("name").is(project.getName());
        Query query = new Query(criteria);
        Project curProejct = mongoTemplate.findOne(query,Project.class);
        return null!=curProejct;
    }
}
