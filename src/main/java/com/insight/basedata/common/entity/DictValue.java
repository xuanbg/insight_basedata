package com.insight.basedata.common.entity;

import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2020/6/1
 * @remark 字典键值实体类
 */
public class DictValue extends BaseXo {

    /**
     * 排序
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
}
