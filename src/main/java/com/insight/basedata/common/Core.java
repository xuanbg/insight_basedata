package com.insight.basedata.common;

import com.github.pagehelper.PageHelper;
import com.insight.basedata.common.dto.FileDto;
import com.insight.basedata.common.entity.LogInfo;
import com.insight.basedata.common.mapper.CoreMapper;
import com.insight.utils.ReplyHelper;
import com.insight.utils.SnowflakeCreator;
import com.insight.utils.Util;
import com.insight.utils.pojo.base.BusinessException;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author 宣炳刚
 * @date 2020/6/5
 * @remark
 */
@Component
public class Core {
    private final SnowflakeCreator creator;
    private final CoreMapper mapper;

    /**
     * 构造方法
     *
     * @param creator ID生成器
     * @param mapper  LogMapper
     */
    public Core(SnowflakeCreator creator, CoreMapper mapper) {
        this.creator = creator;
        this.mapper = mapper;
    }

    /**
     * 新增文件夹
     *
     * @param file 文件DTO
     * @return 文件夹ID
     */
    public Long addFolder(FileDto file) {
        var ownerId = file.getOwnerId();
        var array = file.getFullPath().split("/");
        var folders = Arrays.stream(array).limit(array.length - 1).toList();
        var records = mapper.getFolders(ownerId);
        for (var name : folders) {
            if (Util.isEmpty(name)) {
                continue;
            }

            // 查询或保存文件夹
            var parentId = file.getParentId();
            var record = records.stream().filter(i -> i.equals(parentId, name)).findFirst().orElse(null);
            if (record == null) {
                var folder = new FileDto();
                folder.setId(creator.nextId(5));
                folder.setParentId(parentId);
                folder.setName(name);
                folder.setOwnerId(ownerId);

                mapper.addFile(folder);
                file.setParentId(folder.getId());
            } else {
                file.setParentId(record.getId());
            }
        }

        return file.getParentId();
    }

    /**
     * 保存文件记录
     *
     * @param file 文件DTO
     * @return 文件URL
     */
    public String addFile(FileDto file) {
        var array = file.getFullPath().split("/");
        var name = array[array.length - 1];
        var split = name.lastIndexOf(".");
        file.setId(creator.nextId(5));
        file.setName(split == 0 ? name : name.substring(0, split - 1));
        file.setExt(split == 0 ? null : name.substring(split));

        var url = mapper.getFileByHash(file.getHash());
        if (Util.isEmpty(url)) {
            file.setUrl(file.getFullName());
        } else {
            file.setUrl(url);
        }

        mapper.addFile(file);
        return file.getUrl();
    }

    /**
     * 获取文件URL
     *
     * @param id 文件ID
     * @return 文件URL
     */
    public String getFile(Long id) {
        return mapper.getFile(id);
    }

    /**
     * 获取日志列表
     *
     * @param search 查询实体类
     * @return Reply
     */
    public Reply getLogs(Search search) {
        if (search.getId() == null) {
            throw new BusinessException("业务ID不能为空");
        }

        try (var page = PageHelper.startPage(search.getPageNum(), search.getPageSize()).setOrderBy(search.getOrderBy())
                .doSelectPage(() -> mapper.getLogs(search))) {
            var total = page.getTotal();
            return total > 0 ? ReplyHelper.success(page.getResult(), total) : ReplyHelper.resultIsEmpty();
        }
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    public LogInfo getLog(Long id) {
        LogInfo log = mapper.getLog(id);
        if (log == null) {
            throw new BusinessException("ID不存在,未读取数据");
        }

        return log;
    }
}
