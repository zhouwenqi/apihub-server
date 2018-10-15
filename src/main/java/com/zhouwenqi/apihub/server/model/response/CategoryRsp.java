package com.zhouwenqi.apihub.server.model.response;

import com.zhouwenqi.apihub.server.entity.Category;

import java.util.List;

/**
 * 分类输出
 * Created by zhouwenqi on 2018/10/15.
 */
public class CategoryRsp extends Category {
    private List<Category> categorys;

    public List<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }
}
