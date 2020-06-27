package com.insight.basedata.common.dto;

import com.insight.utils.Json;

import java.io.Serializable;

/**
 * @author 宣炳刚
 * @date 2020/6/1
 * @remark 字典键值实体类
 */
public class DictKeyDto implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 唯一ID
     */
    private String id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 字典ID
     */
    private String dictId;

    /**
     * 排序字段
     */
    private Integer index;

    /**
     * 编码
     */
    private String code;

    /**
     * 键值
     */
    private String value;

    /**
     * 扩展数据
     */
    private Object extend;

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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
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
