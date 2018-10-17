package com.zhouwenqi.apihub.server.model.param;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhouwenqi.apihub.server.common.IdGetSerialize;
import com.zhouwenqi.apihub.server.common.IdSetSerialize;
import com.zhouwenqi.apihub.server.common.RequestBodyTypeGetSerialize;
import com.zhouwenqi.apihub.server.common.RequestBodyTypeSetSerialize;
import com.zhouwenqi.apihub.server.model.RequestBodyType;

import java.io.Serializable;
import java.util.List;

/**
 * api请求body
 * Created by zhouwenqi on 2018/10/16.
 */
public class ApiRequestBody implements Serializable {
    // body类型
    private RequestBodyType type;
    // form-data参数
    private List<FormParam> formParams;
    // x-www-urlencoded参数
    private List<ApiParam> apiParams;
    // raw参数
    private String raw;

    @JsonSerialize(using = RequestBodyTypeGetSerialize.class)
    public RequestBodyType getType() {
        return type;
    }
    @JsonDeserialize(using = RequestBodyTypeSetSerialize.class)
    public void setType(RequestBodyType type) {
        this.type = type;
    }

    public List<FormParam> getFormParams() {
        return formParams;
    }

    public void setFormParams(List<FormParam> formParams) {
        this.formParams = formParams;
    }

    public List<ApiParam> getApiParams() {
        return apiParams;
    }

    public void setApiParams(List<ApiParam> apiParams) {
        this.apiParams = apiParams;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }
}
