package com.insight.basedata.common.entity;

import com.insight.utils.pojo.base.BaseXo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author 宣炳刚
 * @date 2020/6/24
 * @remark 选项参数实体
 */
public class Parameter extends BaseXo {

    /**
     * 唯一ID
     */
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 模块ID
     */
    private Long moduleId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 配置KEY
     */
    @NotEmpty(message = "参数键不能为空")
    private String key;

    /**
     * 配置键值
     */
    @NotNull(message = "参数值不能为空")
    private Object value;

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

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
