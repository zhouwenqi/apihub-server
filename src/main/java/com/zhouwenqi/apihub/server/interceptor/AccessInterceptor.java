package com.zhouwenqi.apihub.server.interceptor;

import com.zhouwenqi.apihub.server.common.JwtUser;
import com.zhouwenqi.apihub.server.entity.Member;
import com.zhouwenqi.apihub.server.entity.Project;
import com.zhouwenqi.apihub.server.entity.User;
import com.zhouwenqi.apihub.server.model.RoleLevel;
import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.model.response.ResponseResult;
import com.zhouwenqi.apihub.server.service.MemberService;
import com.zhouwenqi.apihub.server.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限校验拦截器
 * Created by zhouwenqi on 2018/9/25.
 */
public class AccessInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod().toLowerCase();
        if(method.equals("options")){
            return true;
        }

        ResponseModel responseModel;
        JwtUser jwtUser  = (JwtUser)request.getAttribute("jwtUser");
        String projectId = request.getAttribute("projectId").toString();

        // 校验项目信息
        Project hubProject = projectService.findById(projectId);
        if(null==hubProject){
            responseModel = ResponseModel.getParameterError();
            responseModel.setMsg("项目不存在");
            ResponseResult.output(responseModel,response);
            return false;
        }
        request.setAttribute("hubProject",hubProject);

        // 校验成员信息
        Member hubMember = memberService.find(jwtUser.getId(),projectId);
        if(null==hubMember){
            responseModel = ResponseModel.getNotAuthority();
            responseModel.setMsg("禁止越界访问");
            ResponseResult.output(responseModel,response);
            logger.error("越界访问(uid:"+jwtUser.getUid()+", projectId:"+projectId+")");
            return false;
        }
        request.setAttribute("hubMember",hubMember);

        // 只有开发人员和管理员有更新数据的权限
        List<String> methods = new ArrayList<String>();
        methods.add("delete");
        methods.add("post");
        methods.add("put");
        if(methods.contains(method)){
            if(!hubMember.getRoleLevel().equals(RoleLevel.MANAGER) && !hubMember.getRoleLevel().equals(RoleLevel.BUILD)){
                responseModel = ResponseModel.getNotAuthority();
                ResponseResult.output(responseModel,response);
                logger.error("没有权限(uid:"+jwtUser.getUid()+", roleLevel:"+hubMember.getRoleLevel()+")");
                return false;
            }
        }
        return true;
    }
}
