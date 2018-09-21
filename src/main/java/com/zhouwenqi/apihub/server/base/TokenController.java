package com.zhouwenqi.apihub.server.base;

import com.zhouwenqi.apihub.server.common.JwtUser;
import com.zhouwenqi.apihub.server.entity.Member;
import com.zhouwenqi.apihub.server.entity.Project;
import com.zhouwenqi.apihub.server.entity.User;
import com.zhouwenqi.apihub.server.utils.aesUtils;
import org.springframework.web.context.request.RequestAttributes;

/**
 * Created by zhouwenqi on 2018/6/5.
 */
public class TokenController extends BaseController {
    public Project getCurProject(){
        return (Project)getContextAttribute().getAttribute("curProject", RequestAttributes.SCOPE_REQUEST);
    }
    public JwtUser getJwtUser(){
        return (JwtUser)getContextAttribute().getAttribute("jwtUser", RequestAttributes.SCOPE_REQUEST);
    }
    public User getCurUser(){
        return (User)getContextAttribute().getAttribute("curUser", RequestAttributes.SCOPE_REQUEST);
    }
    public Member getCurMember(){
        return (Member)getContextAttribute().getAttribute("curMember", RequestAttributes.SCOPE_REQUEST);
    }


    /**
     * 解密入参
     * @param text 加密内容
     * @return 解密内容
     * @throws Exception
     */
    public String getDecrypt(String text) throws Exception{
        return aesUtils.decrypt(text);
    }

    /**
     * 解密id
     * @param text 加密id
     * @return
     * @throws Exception
     */
    public Long getOpenId(String text){
        Long id;
        try{
            String value = getDecrypt(text);
            id = Long.parseLong(value);
        }catch (Exception e){
            id = null;
        }
        return id;
    }
}
