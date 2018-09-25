package com.zhouwenqi.apihub.server.service;

import com.zhouwenqi.apihub.server.entity.Member;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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
}
