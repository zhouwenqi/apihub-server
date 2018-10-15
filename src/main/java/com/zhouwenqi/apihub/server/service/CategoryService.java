package com.zhouwenqi.apihub.server.service;

import com.zhouwenqi.apihub.server.entity.Category;
import com.zhouwenqi.apihub.server.entity.Project;
import com.zhouwenqi.apihub.server.model.HubStatus;
import com.zhouwenqi.apihub.server.model.response.CategoryRsp;
import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service - 分类
 * Created by zhouwenqi on 2018/10/12.
 */
@Service("categoryService")
public class CategoryService extends BaseService<Category> {
    /**
     * 获取项目接口分类信息(默认排序)
     * @param id 项目id
     * @return
     */
    public List<CategoryRsp> findByProjectId(ObjectId id){
        return findByProjectId(id,null,null);
    }

    /**
     * 获取项目接口分类信息
     * @param id 项目id字符串
     * @param direction 分类排序方向
     * @param by 分类排序字段
     * @return
     */
    public List<CategoryRsp> findByProjectId(ObjectId id,String direction,String by){
        Sort.Direction sortDirection = null != direction && direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        if(StringUtils.isBlank(by)){
            by = "createDate";
        }
        Sort sort = new Sort(sortDirection,by);
        Criteria criteria = Criteria.where("projectId").is(id);
        criteria.and("parentId").exists(false);
        Query query = new Query(criteria);
        query.with(sort);

        List<CategoryRsp> list = mongoTemplate.find(query,CategoryRsp.class);
        for(CategoryRsp rsp:list){
            Criteria itemCriteria = Criteria.where("parentId").is(rsp.getId());
            Query itemQuery = new Query(itemCriteria);
            itemQuery.with(sort);
            List<Category> categories = mongoTemplate.find(itemQuery,Category.class);
            rsp.setCategorys(categories);
        }

        /*
        List<AggregationOperation> operations = new ArrayList<>();
        operations.add(Aggregation.lookup("category","_id","parentId","categorys"));
        operations.add(Aggregation.match(criteria));
        operations.add(Aggregation.sort(sort));

        Aggregation aggregation = Aggregation.newAggregation(operations);
        AggregationResults<CategoryRsp> results = mongoTemplate.aggregate(aggregation,"category",CategoryRsp.class);
        List<CategoryRsp> list = results.getMappedResults();
        */
        return null != list ? list : new ArrayList<CategoryRsp>();
    }
}
