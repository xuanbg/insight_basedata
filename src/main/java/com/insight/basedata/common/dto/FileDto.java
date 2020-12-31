package com.insight.basedata.common.dto;

import com.insight.utils.Json;

import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.io.Serializable;

/**
 * @author 宣炳刚
 * @date 2020/12/31
 * @remark 文件DTO
 */
public class FileDto implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 字节数组
     */
    @NotNull
    private byte[] bytes;

    /**
     * 输入流
     */
    private InputStream stream;

    /**
     * 文件名
     */
    private String name;

    /**
     * 扩展名
     */
    private String ext;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
