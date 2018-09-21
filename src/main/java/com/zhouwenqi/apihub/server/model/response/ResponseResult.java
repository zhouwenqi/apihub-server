package com.zhouwenqi.apihub.server.model.response;

import com.zhouwenqi.apihub.server.utils.jsonUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * Created by zhouwenqi on 2018/6/5.
 */
public class ResponseResult {
    public static void output(ResponseModel responseModel,HttpServletResponse response){
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setStatus(responseModel.getCode());
        String data = jsonUtils.objectToJson(responseModel);
        try {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(data.getBytes("utf-8"));
            outputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
