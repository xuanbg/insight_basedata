package com.insight.basedata.common.dto;

import com.insight.utils.pojo.SearchDto;

/**
 * @author 宣炳刚
 * @date 2020/6/24
 * @remark
 */
public class ParamSearchDto extends SearchDto {

    /**
     * 模块ID
     */
    private Long moduleId;

    /**
     * 用户ID
     */
    private Long userId;

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
