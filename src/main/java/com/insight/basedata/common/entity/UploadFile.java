package com.insight.basedata.common.entity;

import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2023/8/26
 * @remark
 */
public class UploadFile extends BaseXo {

    /**
     * 文件ID
     */
    private Long id;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件上传令牌
     */
    private String token;

    /**
     * 七牛文件空间bucket
     */
    private String bucket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
