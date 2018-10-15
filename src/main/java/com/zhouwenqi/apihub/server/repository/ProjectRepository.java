package com.zhouwenqi.apihub.server.repository;

import com.zhouwenqi.apihub.server.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Dao - 项目
 * Created by zhouwenqi on 2018/9/29.
 */
@Repository("projectRepository")
public interface ProjectRepository extends MongoRepository<Project,String> {
}
