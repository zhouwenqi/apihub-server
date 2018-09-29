package com.zhouwenqi.apihub.server.controller;

import com.zhouwenqi.apihub.server.base.TokenController;
import com.zhouwenqi.apihub.server.entity.Project;
import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目
 * Created by zhouwenqi on 2018/9/29.
 */
@RestController
@RequestMapping("/project")
public class ProjectController extends TokenController{
    @GetMapping("info")
    public ResponseModel info(String projectId){
        ResponseModel responseModel = ResponseModel.getSuccess();
        return responseModel;
    }
    @PostMapping("info")
    public ResponseModel create(Project project){
        ResponseModel responseModel = ResponseModel.getSuccess();

        return responseModel;
    }

}
