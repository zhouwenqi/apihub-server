package com.zhouwenqi.apihub.server.controller;

import com.zhouwenqi.apihub.server.base.TokenController;
import com.zhouwenqi.apihub.server.entity.Api;
import com.zhouwenqi.apihub.server.entity.Category;
import com.zhouwenqi.apihub.server.entity.Project;
import com.zhouwenqi.apihub.server.entity.User;
import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.service.ApiService;
import com.zhouwenqi.apihub.server.service.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 接口
 * Created by zhouwenqi on 2018/10/16.
 */
@RestController
@RequestMapping("/project")
public class ApiController extends TokenController {
    @Autowired
    private ApiService apiService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("api")
    @ResponseBody
    public ResponseModel create(@RequestBody Api api){
        ResponseModel responseModel = ResponseModel.getSuccess();
        if(null == api){
            responseModel = ResponseModel.getNotParameter();
            return responseModel;
        }

        // 检查项目分类
        if(null == api.getCategoryId()){
            responseModel = ResponseModel.getNotParameter();
            responseModel.setMsg("没有设置分类");
            return responseModel;
        }
        Category category = categoryService.findById(api.getCategoryId());
        if(null == category){
            responseModel = ResponseModel.getNotParameter();
            responseModel.setMsg("没有设置分类");
            return responseModel;
        }
        Project project = getHubProject();
        if(!category.getProjectId().equals(project.getId())){
            responseModel = ResponseModel.getParameterError();
            responseModel.setMsg("分类不正确");
            return responseModel;
        }

        // 接口参数检查
        if(StringUtils.isBlank(api.getTitle())){
            responseModel = ResponseModel.getParameterError();
            responseModel.setMsg("标题不能为空");
            return responseModel;
        }

        if(StringUtils.isBlank(api.getUrl())){
            responseModel = ResponseModel.getParameterError();
            responseModel.setMsg("请求url不能为空");
            return responseModel;
        }

        if(null == api.getRequestType()){
            responseModel = ResponseModel.getParameterError();
            responseModel.setMsg("请求类型不能为空");
            return responseModel;
        }

        User user = getHubUser();
        api.setUserId(user.getId());
        api.setCreateDate(new Date());
        api.setEditDate(new Date());

        apiService.insert(api,"api");
        return responseModel;
    }


}
