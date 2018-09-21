package com.zhouwenqi.apihub.server.service;

import com.zhouwenqi.apihub.server.entity.Member;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service - 成员
 * Created by zhouwenqi on 2018/9/21.
 */
@Service("memberService")
public class MemberService extends BaseService {
    public List<Member> findAll(){
        return super.findAll();
    }
}
