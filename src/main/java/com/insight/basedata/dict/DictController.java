package com.insight.basedata.dict;

import com.insight.basedata.common.entity.Dict;
import com.insight.basedata.common.entity.DictKey;
import com.insight.utils.Json;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;
import com.insight.utils.pojo.SearchDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 宣炳刚
 * @date 2020/6/1
 * @remark 字典服务控制器
 */
@CrossOrigin
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
     * @param info 用户关键信息
     * @param dto  查询参数DTO
     * @return Reply
     */
    @GetMapping("/v1.0/dicts")
    public Reply getDicts(@RequestHeader("loginInfo") String info, SearchDto dto) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.getDicts(loginInfo, dto);
    }

    /**
     * 获取字典键值集合
     *
     * @param info 用户关键信息
     * @param id   字典ID
     * @return Reply
     */
    @GetMapping("/v1.0/dicts/{id}")
    public Reply getDictKeys(@RequestHeader("loginInfo") String info, @PathVariable Long id) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.getDictKeys(loginInfo, id);
    }

    /**
     * 获取指定键名的键值集合
     *
     * @param info 用户关键信息
     * @param key  字典键名
     * @return Reply
     */
    @GetMapping("/v1.0/dicts/values")
    public Reply getValues(@RequestHeader("loginInfo") String info, @RequestParam String key) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.getValues(loginInfo, key);
    }

    /**
     * 新增字典
     *
     * @param info 用户关键信息
     * @param dict 字典实体
     * @return Reply
     */
    @PostMapping("/v1.0/dicts")
    public Reply addDict(@RequestHeader("loginInfo") String info, @Valid @RequestBody Dict dict) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.addDict(loginInfo, dict);
    }

    /**
     * 编辑字典
     *
     * @param info 用户关键信息
     * @param id   字典ID
     * @param dict 字典实体
     * @return Reply
     */
    @PutMapping("/v1.0/dicts/{id}")
    public Reply editDict(@RequestHeader("loginInfo") String info, @PathVariable Long id, @Valid @RequestBody Dict dict) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);
        dict.setId(id);

        return service.editDict(loginInfo, dict);
    }

    /**
     * 删除字典
     *
     * @param info 用户关键信息
     * @param id   字典ID
     * @return Reply
     */
    @DeleteMapping("/v1.0/dicts/{id}")
    public Reply deleteDict(@RequestHeader("loginInfo") String info, @PathVariable Long id) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.deleteDict(loginInfo, id);
    }

    /**
     * 新增字典键值
     *
     * @param info    用户关键信息
     * @param id      字典ID
     * @param dictKey 字典键值实体
     * @return Reply
     */
    @PostMapping("/v1.0/dicts/{id}/keys")
    public Reply addDictKey(@RequestHeader("loginInfo") String info, @PathVariable Long id, @Valid @RequestBody DictKey dictKey) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);
        dictKey.setDictId(id);

        return service.addDictKey(loginInfo, dictKey);
    }

    /**
     * 编辑字典键值
     *
     * @param info    用户关键信息
     * @param id      字典键值ID
     * @param dictKey 字典键值实体
     * @return Reply
     */
    @PutMapping("/v1.0/dicts/keys/{id}")
    public Reply editDictKey(@RequestHeader("loginInfo") String info, @PathVariable Long id, @Valid @RequestBody DictKey dictKey) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.editDictKey(loginInfo, dictKey);
    }

    /**
     * 删除字典键值
     *
     * @param info 用户关键信息
     * @param id   字典键值ID
     * @return Reply
     */
    @DeleteMapping("/v1.0/dicts/keys/{id}")
    public Reply deleteDictKey(@RequestHeader("loginInfo") String info, @PathVariable Long id) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.deleteDictKey(loginInfo, id);
    }

    /**
     * 获取日志列表
     *
     * @param info   用户关键信息
     * @param search 查询实体类
     * @return Reply
     */
    @GetMapping("/v1.0/dicts/logs")
    public Reply getLogs(@RequestHeader("loginInfo") String info, SearchDto search) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.getLogs(loginInfo, search);
    }

    /**
     * 获取日志详情
     *
     * @param id 日志ID
     * @return Reply
     */
    @GetMapping("/v1.0/dicts/logs/{id}")
    Reply getLog(@PathVariable Long id) {
        return service.getLog(id);
    }
}
