package com.insight.basedata.common.entity;

import com.insight.utils.pojo.BaseXo;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * @author 宣炳刚
 * @date 2020/6/25
 * @remark 报表模板实体
 */
public class Template extends BaseXo {

    /**
     * 唯一ID
     */
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    @NotEmpty(message = "名称不能为空")
    private String name;

    /**
     * 模板内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否失效:0.有效;1.失效
     */
    private Boolean isInvalid;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人ID
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getInvalid() {
        return isInvalid;
    }

    public void setInvalid(Boolean invalid) {
        isInvalid = invalid;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
