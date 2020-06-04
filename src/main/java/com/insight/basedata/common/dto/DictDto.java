package com.insight.basedata.common.dto;

import com.insight.utils.Json;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 宣炳刚
 * @date 2020/6/1
 * @remark 字典实体类
 */
public class DictDto implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 唯一ID
     */
    private String id;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
