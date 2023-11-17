package com.insight.basedata.common.mapper;

import com.insight.utils.pojo.base.TreeBase;
import com.insight.utils.pojo.file.FileDto;
import com.insight.utils.pojo.file.FileVo;
import com.insight.utils.pojo.file.Folder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2023/11/8
 * @remark 文件DAL
 */
@Mapper
public interface FileMapper {

    /**
     * 按文件哈希值查找文件
     *
     * @param hash   文件哈希值
     * @param userId 用户ID
     * @return 文件ID
     */
    @Select("""
            select *
            from icf_file
            where hash = #{hash}
              and owner_id = #{userId};
            """)
    List<FileVo> getMyFiles(String hash, Long userId);

    /**
     * 按文件哈希值查找文件
     *
     * @param hash 文件哈希值
     * @return 文件ID
     */
    @Select("""
            select *
            from icf_file
            where hash = #{hash}
            order by id limit 1;
            """)
    FileVo getFileByHash(String hash);

    /**
     * 获取指定名称的路径ID
     *
     * @param ownerId 用户ID
     * @return 路径ID
     */
    @Select("""
            select id, parent_id, name
            from icf_file
            where owner_id = #{ownerId}
              and type = 0
              and invalid = 0;
            """)
    List<TreeBase> getFolders(Long ownerId);

    /**
     * 新增文件夹
     *
     * @param folder 文件夹
     */
    @Select("""
            insert icf_file (id, parent_id, type, name, owner_id, created_time)
            values (#{id}, #{parentId}, #{type}, #{name}, #{ownerId}, now());
            """)
    void addFolder(Folder folder);

    /**
     * 新增文件记录
     *
     * @param file 文件DTO
     */
    @Select("""
            insert icf_file (id, parent_id, type, name, ext, domain, path, hash, size, owner_id, created_time) values
            (#{id}, #{parentId}, #{type}, #{name}, #{ext}, #{domain}, #{path}, #{hash}, #{size}, #{ownerId}, now());
            """)
    void addFile(FileDto file);

    /**
     * 获取文件URL
     *
     * @param id 文件ID
     * @return 文件URL
     */
    @Select("select path from icf_file where id = #{id};")
    String getFile(Long id);
}
