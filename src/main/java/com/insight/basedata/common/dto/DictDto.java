package com.insight.basedata.common.dto;

import com.insight.utils.pojo.BaseXo;

/**
 * @author 宣炳刚
 * @date 2020/6/1
 * @remark 字典实体类
 */
public class DictDto extends BaseXo {

    /**
     * 唯一ID
     */
    private Long id;

    /**
     * 应用ID
     */
    private Long appId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
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
}
