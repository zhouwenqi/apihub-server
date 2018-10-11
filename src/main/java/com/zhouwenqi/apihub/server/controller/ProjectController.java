package com.zhouwenqi.apihub.server.controller;

import com.zhouwenqi.apihub.server.base.TokenController;
import com.zhouwenqi.apihub.server.entity.Member;
import com.zhouwenqi.apihub.server.entity.Project;
import com.zhouwenqi.apihub.server.entity.User;
import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.repository.ProjectRepository;
import com.zhouwenqi.apihub.server.service.MemberService;
import com.zhouwenqi.apihub.server.service.ProjectService;
import com.zhouwenqi.apihub.server.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 项目
 * Created by zhouwenqi on 2018/9/29.
 */
@RestController
@RequestMapping("/project")
public class ProjectController extends TokenController{
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectRepository projectRepository;

    /**
     * 获取当前项目信息
     * @return
     */
    @GetMapping("info")
    @ResponseBody
    public ResponseModel info(){
        ResponseModel responseModel = ResponseModel.getSuccess();
        responseModel.addData("project",getHubProject());
        return responseModel;
    }

    /**
     * 获取当前项目成员
     * @return
     */
    @GetMapping("members")
    @ResponseBody
    public ResponseModel members(){
        ResponseModel responseModel = ResponseModel.getSuccess();
        Project project = getHubProject();
        List<Member> list = memberService.findByProjectId(project.getId());
        responseModel.addData("list",list);
        return responseModel;
    }

    /**
     * 更新项目信息
     * @param project 项目信息
     * @return
     */
    @PutMapping("info")
    @ResponseBody
    public ResponseModel updateProject(Project project){
        ResponseModel responseModel = ResponseModel.getSuccess();
        if(null==project){
            responseModel = ResponseModel.getNotParameter();
            return responseModel;
        }
        User user = getHubUser();
        Project curProject = getHubProject();
        project.setUserId(user.getId());
        project.setId(curProject.getId());

        if(StringUtils.isNotBlank(project.getName())){
            if(projectService.findIsRepeat(project)){
                responseModel = ResponseModel.getParameterError();
                responseModel.setMsg("项目名称重复");
                return responseModel;
            }
            curProject.setName(project.getName());
        }
        if(StringUtils.isNotBlank(project.getDescription())){
            curProject.setDescription(project.getDescription());
        }
        if(StringUtils.isNotBlank(project.getIco())){
            curProject.setDescription(project.getIco());
        }
        if(null!=project.getStatus()){
            curProject.setStatus(project.getStatus());
        }
        curProject.setCreateDate(new Date());
        curProject.setEditDate(new Date());
        projectRepository.save(curProject);
        return responseModel;
    }
}
