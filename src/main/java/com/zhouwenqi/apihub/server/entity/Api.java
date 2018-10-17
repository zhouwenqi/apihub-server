package com.zhouwenqi.apihub.server.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhouwenqi.apihub.server.common.IdGetSerialize;
import com.zhouwenqi.apihub.server.common.IdSetSerialize;
import com.zhouwenqi.apihub.server.model.RequestType;
import com.zhouwenqi.apihub.server.model.param.ApiParam;
import com.zhouwenqi.apihub.server.model.param.ApiRequestBody;
import com.zhouwenqi.apihub.server.model.param.ApiResponseBody;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 接口
 * Created by zhouwenqi on 2018/10/15.
 */
public class Api implements Serializable {
    // 接口id
    @Id
    private ObjectId id;
    // 请求方式
    @Indexed
    private RequestType requestType;
    // 接口标题
    private String title;
    // 接口描述
    private String description;
    // 接口请求url
    @Indexed
    private String url;
    // 接口分类id
    @Indexed
    private ObjectId categoryId;
    // 用户id
    @Indexed
    private ObjectId userId;
    // 创建时间
    @Indexed
    private Date createDate;
    // 修改时间
    private Date editDate;
    // 头发参数
    private List<ApiParam> headers;
    // 请求参数
    private List<ApiParam> querys;
    // 请求body
    private ApiRequestBody requestBody;
    // 输出body
    private ApiResponseBody responseBody;

    @JsonSerialize(using = IdGetSerialize.class)
    public ObjectId getId() {
        return id;
    }

    @JsonDeserialize(using = IdSetSerialize.class)
    public void setId(ObjectId id) {
        this.id = id;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ObjectId getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(ObjectId categoryId) {
        this.categoryId = categoryId;
    }

    @JsonSerialize(using = IdGetSerialize.class)
    public ObjectId getUserId() {
        return userId;
    }

    @JsonDeserialize(using = IdSetSerialize.class)
    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public List<ApiParam> getHeaders() {
        return headers;
    }

    public void setHeaders(List<ApiParam> headers) {
        this.headers = headers;
    }

    public List<ApiParam> getQuerys() {
        return querys;
    }

    public void setQuerys(List<ApiParam> querys) {
        this.querys = querys;
    }

    public ApiRequestBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(ApiRequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public ApiResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ApiResponseBody responseBody) {
        this.responseBody = responseBody;
    }
}
