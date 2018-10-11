package com.zhouwenqi.apihub.server.service;

import com.zhouwenqi.apihub.server.entity.Project;
import com.zhouwenqi.apihub.server.model.HubStatus;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if(null!=project.getId()){
            criteria.and("id").ne(project.getId());
        }
        Query query = new Query(criteria);
        Project curProejct = mongoTemplate.findOne(query,Project.class);
        return null!=curProejct;
    }

    /**
     * 获取创建的项目列表
     * @param userId 用户id
     * @return
     */
    public List<Project> findByCreate(ObjectId userId){
        Criteria criteria = Criteria.where("userId").is(userId);
        criteria.and("status").is(HubStatus.ON);
        Query query = new Query(criteria);
        List<Project> list = mongoTemplate.find(query,Project.class);
        return null != list ? list : new ArrayList<Project>();
    }

    /**
     * 获取参与的项目列表
     * @param userId 用户id
     * @return
     */
    public List<Project> findByJoin(ObjectId userId) {
        Criteria criteria = Criteria.where("userId").is(userId);
        criteria.and("status").is(HubStatus.ON);
        List<AggregationOperation> operations = new ArrayList<>();
        operations.add(Aggregation.match(criteria));
        operations.add(Aggregation.lookup("member","id","projectId","projectId"));
        Aggregation aggregation = Aggregation.newAggregation(operations);
        AggregationResults<Project> results = mongoTemplate.aggregate(aggregation,"project",Project.class);
        List<Project> list = results.getMappedResults();
        return null != list ? list : new ArrayList<Project>();
    }

    /**
     * 查询所有我参与的项目（包括已关闭的）
     * @param userId 用户id
     * @return
     */
    public List<Project> findByAll(ObjectId userId){
        Criteria criteria = Criteria.where("userId").is(userId);
        criteria.and("status").is(HubStatus.ON);
        criteria.and("status").is(HubStatus.DISABLED);
        List<AggregationOperation> operations = new ArrayList<>();
        operations.add(Aggregation.match(criteria));
        operations.add(Aggregation.lookup("member","id","projectId","projectId"));
        Aggregation aggregation = Aggregation.newAggregation(operations);
        AggregationResults<Project> results = mongoTemplate.aggregate(aggregation,"project",Project.class);
        List<Project> list = results.getMappedResults();
        return null != list ? list : new ArrayList<Project>();
    }
}
