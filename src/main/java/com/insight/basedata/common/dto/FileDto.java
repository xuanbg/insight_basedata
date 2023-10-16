package com.insight.basedata.common.dto;

import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2020/12/31
 * @remark 文件DTO
 */
public class FileDto extends BaseXo {

    /**
     * 文件记录ID
     */
    private Long id;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 类型: 0.文件夹, 1.图片, 2.音频, 3.视频, 4.文档, 5.压缩包, 6.其他
     */
    private Integer type;

    /**
     * 文件名
     */
    private String file;

    /**
     * 名称
     */
    private String name;

    /**
     * 文件域名
     */
    private String domain;

    /**
     * 文件URL
     */
    private String url;

    /**
     * 文件哈希值
     */
    private String hash;

    /**
     * 文件字节数
     */
    private Integer size;

    /**
     * 拥有人ID
     */
    private Long ownerId;

    /**
     * 文件是否已存在
     */
    private Boolean existed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        if (type != null) {
            return type;
        }

        if (getExt() == null) {
            return 0;
        }

        return switch (getExt()) {
            case "jpg", "jpeg", "bmp", "png" -> 1;
            case "mp3", "wav" -> 2;
            case "mp4", "mov" -> 3;
            case "pdf", "doc", "docx", "ppt", "pptx", "xls", "xlsx", "txt" -> 4;
            case "zip", "rar", "7z" -> 5;
            default -> 6;
        };
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFullPath() {
        if (file == null) {
            return null;
        }

        var array = file.split(":");
        var path = array.length > 1 ? array[1] : array[0];

        return path.replaceAll("\\\\", "/").trim();
    }

    public String getFullName() {
        var fullPath = getFullPath();
        if (fullPath == null) {
            return null;
        }

        var split = fullPath.lastIndexOf("/");
        return split < 0 ? fullPath : fullPath.substring(split);
    }

    public String getName() {
        if (name != null) {
            return name;
        }

        var fullName = getFullName();
        if (fullName == null) {
            return null;
        }

        var array = fullName.split("\\.");
        return array[0];
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExt() {
        var fullName = getFullName();
        if (fullName == null) {
            return null;
        }

        var array = fullName.split("\\.");
        return array.length > 1 ? array[1] : null;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUrl() {
        if ((getType() == 0)) {
            return null;
        }

        if (url != null) {
            return url;
        }

        return ownerId + switch (getType()) {
            case 1 -> "/picture/";
            case 2 -> "/audio/";
            case 3 -> "/video/";
            case 4 -> "/document/";
            default -> "/other/";
        } + getFullName();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getExisted() {
        return existed != null && existed;
    }

    public void setExisted(Boolean existed) {
        this.existed = existed;
    }
}
