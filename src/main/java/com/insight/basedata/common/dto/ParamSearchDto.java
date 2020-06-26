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
    private String moduleId;

    /**
     * 用户ID
     */
    private String userId;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
