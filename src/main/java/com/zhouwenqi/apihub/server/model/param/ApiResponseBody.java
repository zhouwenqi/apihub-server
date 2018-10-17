package com.zhouwenqi.apihub.server.model.param;

import java.io.Serializable;
import java.util.List;

/**
 * api输出body
 * Created by zhouwenqi on 2018/10/16.
 */
public class ApiResponseBody implements Serializable {
    // 输出内容
    private String body;
    // 输出参数描述
    private List<ParamDoc> docs;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<ParamDoc> getDocs() {
        return docs;
    }

    public void setDocs(List<ParamDoc> docs) {
        this.docs = docs;
    }
}
