package com.insight.basedata.dict;

import com.insight.basedata.common.dto.DictKeyDto;
import com.insight.basedata.common.entity.Dict;
import com.insight.basedata.common.entity.DictKey;
import com.insight.basedata.common.entity.DictValue;
import com.insight.basedata.common.entity.LogInfo;
import com.insight.utils.Json;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/6/1
 * @remark 字典服务控制器
 */
@RestController
@RequestMapping("/common/dict")
public class DictController {
    private final DictService service;

    /**
     * 构造方法
     *
     * @param service DictService
     */
    public DictController(DictService service) {
        this.service = service;
    }

    /**
     * 获取字典列表
     *
     * @param loginInfo 用户关键信息
     * @param dto       查询参数DTO
     * @return Reply
     */
    @GetMapping("/v1.0/dicts")
    public Reply getDicts(@RequestHeader("loginInfo") String loginInfo, Search dto) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        return service.getDicts(info, dto);
    }

    /**
     * 获取字典键值集合
     *
     * @param loginInfo 用户关键信息
     * @param id        字典ID
     * @return Reply
     */
    @GetMapping("/v1.0/dicts/{id}")
    public List<DictKeyDto> getDictKeys(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        return service.getDictKeys(info, id);
    }

    /**
     * 获取指定键名的键值集合
     *
     * @param loginInfo 用户关键信息
     * @param key       字典KEY
     * @return Reply
     */
    @GetMapping("/v1.0/dicts/values")
    public List<DictValue> getValues(@RequestHeader("loginInfo") String loginInfo, @RequestParam String key) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        return service.getValues(info.getTenantId(), key);
    }

    /**
     * 新增字典
     *
     * @param loginInfo 用户关键信息
     * @param dict      字典实体
     * @return Reply
     */
    @PostMapping("/v1.0/dicts")
    public Long addDict(@RequestHeader("loginInfo") String loginInfo, @Valid @RequestBody Dict dict) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        return service.addDict(info, dict);
    }

    /**
     * 编辑字典
     *
     * @param loginInfo 用户关键信息
     * @param id        字典ID
     * @param dict      字典实体
     */
    @PutMapping("/v1.0/dicts/{id}")
    public void editDict(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id, @Valid @RequestBody Dict dict) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        dict.setId(id);

        service.editDict(info, dict);
    }

    /**
     * 删除字典
     *
     * @param loginInfo 用户关键信息
     * @param id        字典ID
     */
    @DeleteMapping("/v1.0/dicts/{id}")
    public void deleteDict(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        service.deleteDict(info, id);
    }

    /**
     * 新增字典键值
     *
     * @param loginInfo 用户关键信息
     * @param id        字典ID
     * @param dictKey   字典键值实体
     * @return Reply
     */
    @PostMapping("/v1.0/dicts/{id}/keys")
    public Long addDictKey(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id, @Valid @RequestBody DictKey dictKey) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        dictKey.setDictId(id);

        return service.addDictKey(info, dictKey);
    }

    /**
     * 编辑字典键值
     *
     * @param loginInfo 用户关键信息
     * @param id        字典键值ID
     * @param dictKey   字典键值实体
     */
    @PutMapping("/v1.0/dicts/keys/{id}")
    public void editDictKey(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id, @Valid @RequestBody DictKey dictKey) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        service.editDictKey(info, dictKey);
    }

    /**
     * 删除字典键值
     *
     * @param loginInfo 用户关键信息
     * @param id        字典键值ID
     */
    @DeleteMapping("/v1.0/dicts/keys/{id}")
    public void deleteDictKey(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);
        service.deleteDictKey(info, id);
    }

    /**
     * 获取日志列表
     *
     * @param loginInfo 用户关键信息
     * @param id        行政区划ID
     * @param search    查询实体类
     * @return Reply
     */
    @GetMapping("/v1.0/dicts/{id}/logs")
    public Reply getLogs(@RequestHeader("loginInfo") String loginInfo, @PathVariable Long id, Search search) {
        LoginInfo info = Json.toBeanFromBase64(loginInfo, LoginInfo.class);

        search.setId(id);
        search.setAppId(info.getAppId());
        search.setTenantId(info.getTenantId());
        return service.getLogs(search);
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    @GetMapping("/v1.0/dicts/logs/{id}")
    public LogInfo getLog(@PathVariable Long id) {
        return service.getLog(id);
    }
}
