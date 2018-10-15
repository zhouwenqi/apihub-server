package com.zhouwenqi.apihub.server.service;

import com.zhouwenqi.apihub.server.entity.Member;
import com.zhouwenqi.apihub.server.model.RoleLevel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service - 成员
 * Created by zhouwenqi on 2018/9/21.
 */
@Service("memberService")
public class MemberService extends BaseService<Member> {

    /**
     * 查询成员信息
     * @param userId 用户ObjectId
     * @param projectId 项目ObjectId
     * @return
     */
    public Member find(ObjectId userId,ObjectId projectId){
        Criteria criteria = Criteria.where("userId").is(userId);
        criteria.and("projectId").is(projectId);
        Query query = new Query(criteria);
        return mongoTemplate.findOne(query,Member.class);
    }

    /**
     * 查询成员信息
     * @param userId 用户字符串id
     * @param projectId 项目字符串id
     * @return
     */
    public Member find(String userId,String projectId){
        return find(new ObjectId(userId),new ObjectId(projectId));
    }

    /**
     * 创建项目成员
     * @param userId 用户id
     * @param projectId 项目id
     * @param roleLevel 成员级别
     * @return
     */
    public Member join(ObjectId userId, ObjectId projectId, RoleLevel roleLevel){
        Member member = find(userId,projectId);
        if(null==member){
            member = new Member();
            member.setUserId(userId);
            member.setProjectId(projectId);
            member.setEditDate(new Date());
            member.setCreateDate(new Date());
            member.setRoleLevel(roleLevel);
            mongoTemplate.save(member,"member");
        }
        return member;
    }

    /**
     * 查询项目成员
     * @param projectId 项目id
     * @return
     */
    public List<Member> findByProjectId(ObjectId projectId){
        Criteria criteria = Criteria.where("projectId").is(projectId);
        List<AggregationOperation> operations = new ArrayList<>();
        operations.add(Aggregation.match(criteria));
        operations.add(Aggregation.lookup("user","userId","_id","user"));
        operations.add(Aggregation.unwind("user"));
        Aggregation aggregation = Aggregation.newAggregation(operations);
        AggregationResults<Member> results = mongoTemplate.aggregate(aggregation,"member",Member.class);
        List<Member> list = results.getMappedResults();
        return null == list ? new ArrayList<Member>() : list;
    }

    /**
     * 查询项目成员
     * @param projectId 项目字符串id
     * @return
     */
    public List<Member> findByProjectId(String projectId){
        ObjectId id = new ObjectId(projectId);
        return findByProjectId(id);
    }
}
