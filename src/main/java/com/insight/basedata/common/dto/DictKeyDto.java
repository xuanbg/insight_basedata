package com.insight.basedata.common.dto;

import com.insight.utils.Json;

import java.io.Serializable;
import java.time.LocalDateTime;

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
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

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
