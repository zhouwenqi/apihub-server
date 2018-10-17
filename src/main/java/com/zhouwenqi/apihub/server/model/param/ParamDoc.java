package com.zhouwenqi.apihub.server.model.param;

import com.zhouwenqi.apihub.server.model.ParamType;

import java.io.Serializable;
import java.util.List;

/**
 * 参数文档
 * Created by zhouwenqi on 2018/10/16.
 */
public class ParamDoc implements Serializable {
    // 参数值
    private String value;
    // 参数说明
    private String description;
    // 参数示例
    private String example;
    // 参数类型
    private ParamType type;
    // 参数指定值
    private List<String> data;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public ParamType getType() {
        return type;
    }

    public void setType(ParamType type) {
        this.type = type;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
