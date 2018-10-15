package com.zhouwenqi.apihub.server.controller;

import com.zhouwenqi.apihub.server.base.TokenController;
import com.zhouwenqi.apihub.server.entity.Project;
import com.zhouwenqi.apihub.server.entity.User;
import com.zhouwenqi.apihub.server.exception.FailedException;
import com.zhouwenqi.apihub.server.exception.ParameterErrorException;
import com.zhouwenqi.apihub.server.model.HubStatus;
import com.zhouwenqi.apihub.server.model.ProjectRange;
import com.zhouwenqi.apihub.server.model.RoleLevel;
import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.repository.ProjectRepository;
import com.zhouwenqi.apihub.server.service.MemberService;
import com.zhouwenqi.apihub.server.service.ProjectService;
import com.zhouwenqi.apihub.server.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户
 * Created by zhouwenqi on 2018/9/21.
 */
@RestController("userController")
@RequestMapping("/user")
public class UserController extends TokenController {
    @Autowired
    private UserService userService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectRepository projectRepository;

    /**
     * 获取用户信息
     * @param id 用户id
     * @return
     */
    @GetMapping("info")
    @ResponseBody
    public ResponseModel info(String id){
        ResponseModel responseModel = ResponseModel.getSuccess();
        User user = userService.findById(id);
        if(null==user){
            responseModel = ResponseModel.getFailed();
            responseModel.setMsg("用户不存在");
            return responseModel;
        }
        responseModel.addData("user",user);
        return responseModel;
    }

    /**
     * 获取用户项目列表
     * @return
     */
    @GetMapping("project/list")
    @ResponseBody
    public ResponseModel list(ProjectRange range){
        ResponseModel responseModel = ResponseModel.getSuccess();
        if(null==range){
            range = ProjectRange.ALL;
        }
        List<Project> list = new ArrayList<Project>();
        User user = getHubUser();
        switch (range){
            case CREATE:
                list = projectService.findByCreate(user.getId());
                break;
            case JOIN:
                list = projectService.findByJoin(user.getId());
                break;
            case ALL:
            default:
                list = projectService.findByAll(user.getId());
                break;
        }
        responseModel.addData("list",list);
        return  responseModel;
    }

    /**
     * 获取项目信息
     * @param id 项目id
     * @return
     */
    @GetMapping("project")
    @ResponseBody
    public ResponseModel projectInfo(String id){
        Project project = projectService.findById(id);
        if(null == project){
            throw new ParameterErrorException("项目不存在");
        }
        ResponseModel responseModel = ResponseModel.getSuccess();
        responseModel.addData("project",project);
        return responseModel;
    }

    /**
     * 创建项目
     * @param project
     * @return
     */
    @PostMapping("project")
    @ResponseBody
    public ResponseModel createProejct(Project project){
        ResponseModel responseModel = ResponseModel.getSuccess();
        if(null==project){
            responseModel = ResponseModel.getNotParameter();
            return responseModel;
        }
        // 项目创建人为当前登录用户
        project.setUserId(this.getHubUser().getId());

        // 项目名称参数校验
        if(StringUtils.isBlank(project.getName())){
            responseModel = ResponseModel.getNotParameter();
            responseModel.setMsg("项目名称不能为空");
            return responseModel;
        }
        if(projectService.findIsRepeat(project)){
            responseModel = ResponseModel.getParameterError();
            responseModel.setMsg("项目名称重复");
            return responseModel;
        }

        // 创建项目
        project.setId(null);
        project.setCreateDate(new Date());
        project.setEditDate(new Date());
        project.setStatus(HubStatus.ON);
        projectService.save(project,"project");

        // 以管理员角色加入项目成员
        memberService.join(project.getUserId(),project.getId(),RoleLevel.MANAGER);

        responseModel.addData("project",project);
        return responseModel;
    }
}
