package com.zhouwenqi.apihub.server.interceptor;

import com.zhouwenqi.apihub.server.common.JwtUser;
import com.zhouwenqi.apihub.server.entity.User;
import com.zhouwenqi.apihub.server.model.HubStatus;
import com.zhouwenqi.apihub.server.model.response.ResponseModel;
import com.zhouwenqi.apihub.server.model.response.ResponseResult;
import com.zhouwenqi.apihub.server.service.UserService;
import com.zhouwenqi.apihub.server.utils.jwtUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Token校验拦截器
 * Created by zhouwenqi on 2018/9/25.
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod().toLowerCase();
        if("options".equals(method)){
            return true;
        }

        ResponseModel responseModel;
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String path = request.getServletPath();
        String token = null;
        String projectId = null;

        if(null!=request.getHeader("apihub-token")){
            token = request.getHeader("apihub-token");
        }
        if(null!=request.getParameter("apihub-token")){
            token = request.getHeader("apihub-token");
        }

        // 判断token是否存在
        if(StringUtils.isBlank(token)){
            responseModel = ResponseModel.getNotToken();
            ResponseResult.output(responseModel,response);
            return false;
        }

        // 解码token
        try{
            JwtUser jwtUser = jwtUtils.decodeUser(token);
            request.setAttribute("jwtUser",jwtUser);
            User hubUser = userService.findByUid(jwtUser.getUid());
            if(null==hubUser){
                responseModel = ResponseModel.getParameterError();
                responseModel.setMsg("用户信息不存在");
                ResponseResult.output(responseModel,response);
                return false;
            }
            if(!hubUser.getStatus().equals(HubStatus.ON)){
                responseModel = ResponseModel.getParameterError();
                responseModel.setMsg("用户状态异常");
                ResponseResult.output(responseModel,response);
                logger.error("用户状态异常(status:"+hubUser.getStatus()+")");
                return false;
            }
            request.setAttribute("hubUser",hubUser);
        }catch (Exception e){
            logger.error("token解码失败:"+token);
            responseModel = ResponseModel.getTokenError();
            responseModel.setMsg("token无效");
            ResponseResult.output(responseModel,response);
            return false;
        }

        // 校验项目id
        if(antPathMatcher.match("/project/**", path)){
            projectId = request.getHeader("project-id");
            if(StringUtils.isBlank(projectId)){
                responseModel = ResponseModel.getNotParameter();
                responseModel.setMsg("缺少项目id参数");
                ResponseResult.output(responseModel,response);
                return false;
            }
            request.setAttribute("projectId", projectId);
        }
        return true;
    }
}
