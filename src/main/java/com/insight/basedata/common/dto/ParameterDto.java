package com.insight.basedata.common.dto;

import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2020/6/24
 * @remark 选项参数DTO
 */
public class ParameterDto extends BaseXo {

    /**
     * 唯一ID
     */
    private Long id;

    /**
     * 配置KEY
     */
    private String key;

    /**
     * 配置键值
     */
    private Object value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
