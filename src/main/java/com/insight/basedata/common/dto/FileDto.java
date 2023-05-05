package com.insight.basedata.common.dto;

import com.insight.utils.pojo.base.BaseXo;
import jakarta.validation.constraints.NotNull;

import java.io.InputStream;

/**
 * @author 宣炳刚
 * @date 2020/12/31
 * @remark 文件DTO
 */
public class FileDto extends BaseXo {

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
}
