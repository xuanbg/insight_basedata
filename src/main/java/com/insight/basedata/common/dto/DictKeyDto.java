package com.insight.basedata.common.dto;

import com.insight.utils.pojo.BaseXo;

/**
 * @author 宣炳刚
 * @date 2020/6/1
 * @remark 字典键值实体类
 */
public class DictKeyDto extends BaseXo {

    /**
     * 唯一ID
     */
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 字典ID
     */
    private Long dictId;

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

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
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
}
