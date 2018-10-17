package com.zhouwenqi.apihub.server.model.param;

/**
 * 标准参数
 * Created by zhouwenqi on 2018/10/16.
 */
public class ApiParam extends BaseParam {
    // 参数文档
    private ParamDoc doc;

    public ParamDoc getDoc() {
        return doc;
    }

    public void setDoc(ParamDoc doc) {
        this.doc = doc;
    }
}
