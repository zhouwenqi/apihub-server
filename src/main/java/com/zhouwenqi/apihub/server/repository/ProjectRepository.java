package com.zhouwenqi.apihub.server.repository;

import com.zhouwenqi.apihub.server.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Dao - 项目
 * Created by zhouwenqi on 2018/9/29.
 */
public interface ProjectRepository extends MongoRepository<Project,String> {
}
