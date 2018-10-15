package com.zhouwenqi.apihub.server.repository;

import com.zhouwenqi.apihub.server.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao - 分类
 * Created by zhouwenqi on 2018/10/12.
 */
@Repository("categoryRepository")
public interface CategoryRepository extends MongoRepository<Category,String> {
}
