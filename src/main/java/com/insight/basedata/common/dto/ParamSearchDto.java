package com.insight.basedata.common.dto;

import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2020/6/24
 * @remark
 */
public class ParamSearchDto extends BaseXo {

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
}
