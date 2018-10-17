package com.zhouwenqi.apihub.server.model.param;

import com.zhouwenqi.apihub.server.model.FormParamType;

/**
 * form-data 参数
 * Created by zhouwenqi on 2018/10/16.
 */
public class FormParam extends ApiParam {
    // 参数类型
    private FormParamType type;

    public FormParamType getType() {
        return type;
    }

    public void setType(FormParamType type) {
        this.type = type;
    }
}
