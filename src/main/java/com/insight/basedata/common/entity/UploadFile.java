package com.insight.basedata.common.entity;

import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2023/8/26
 * @remark
 */
public class UploadFile extends BaseXo {

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件上传令牌
     */
    private String token;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
