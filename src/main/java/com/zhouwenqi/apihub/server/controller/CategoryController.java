package com.zhouwenqi.apihub.server.controller;

import com.zhouwenqi.apihub.server.base.TokenController;
import com.zhouwenqi.apihub.server.entity.Category;
import com.zhouwenqi.apihub.server.entity.Project;
import com.zhouwenqi.apihub.server.model.response.CategoryRsp;
import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.repository.CategoryRepository;
import com.zhouwenqi.apihub.server.service.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 分类
 *
 * Created by zhouwenqi on 2018/10/13.
 */
@RestController
@RequestMapping("/project")
public class CategoryController extends TokenController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 获取项目接口分类列表
     * @param direction 排序方向
     * @param by 排序字段
     * @return
     */
    @GetMapping("categorys")
    @ResponseBody
    public ResponseModel list(String direction, String by){
        ResponseModel responseModel = ResponseModel.getSuccess();
        Project project = getHubProject();
        List<CategoryRsp> list = categoryService.findByProjectId(project.getId(),direction,by);
        responseModel.addData("list",list);
        return responseModel;
    }

    /**
     * 创建项目分类
     * @param category 分类
     * @return
     */
    @PostMapping("category")
    @ResponseBody
    public ResponseModel create(Category category){
        ResponseModel responseModel = ResponseModel.getSuccess();
        if(StringUtils.isBlank(category.getName())){
            responseModel = ResponseModel.getNotParameter();
            responseModel.setMsg("分类名不能为空");
            return responseModel;
        }
        // 获取项目信息
        Project project = getHubProject();
        if(null != category.getParentId()){
            Category parentCategory = categoryService.findById(category.getParentId());
            if(null == parentCategory || !parentCategory.getProjectId().equals(project.getId())){
                responseModel = ResponseModel.getNotParameter();
                responseModel.setMsg("上级分类信息错误");
                return responseModel;
            }
        }
        category.setProjectId(project.getId());
        category.setCreateDate(new Date());
        category.setEditDate(new Date());
        categoryRepository.insert(category);
        responseModel.addData("cagetory",category);
        return responseModel;
    }

    /**
     * 更新项目分类信息
     * @param category 分类
     * @return
     */
    @PutMapping("category")
    @ResponseBody
    public  ResponseModel update(Category category){
        ResponseModel responseModel = ResponseModel.getSuccess();
        if(StringUtils.isBlank(category.getName()) || null == category.getId()){
            responseModel = ResponseModel.getNotParameter();
            return responseModel;
        }
        Category curCategory = categoryService.findById(category.getId());
        if(null == curCategory){
            responseModel = ResponseModel.getNotParameter();
            responseModel.setMsg("分类不存在");
            return responseModel;
        }
        // 获取项目信息
        Project project = getHubProject();
        if(null != category.getParentId()){
            Category parentCategory = categoryService.findById(category.getParentId());
            System.out.println("parent-id:"+parentCategory.getId());
            if(null == parentCategory || !parentCategory.getProjectId().equals(project.getId())){
                responseModel = ResponseModel.getNotParameter();
                responseModel.setMsg("上级分类信息错误");
                return responseModel;
            }
        }
        category.setProjectId(project.getId());
        category.setEditDate(new Date());
        categoryRepository.save(category);
        responseModel.addData("cagetory",category);
        return  responseModel;
    }
}
