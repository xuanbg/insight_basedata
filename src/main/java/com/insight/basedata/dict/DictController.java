package com.insight.basedata.dict;

import com.insight.basedata.common.entity.Dict;
import com.insight.basedata.common.entity.DictKey;
import com.insight.utils.Json;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;
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
     * @param info    用户关键信息
     * @param keyword 查询关键词
     * @param page    分页页码
     * @param size    每页记录数
     * @return Reply
     */
    @GetMapping("/v1.0/dicts")
    public Reply getDicts(@RequestHeader("loginInfo") String info, @RequestParam(required = false) String keyword,
                          @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.getDicts(loginInfo, keyword, page, size);
    }

    /**
     * 获取字典键值集合
     *
     * @param info 用户关键信息
     * @param id   字典ID
     * @return Reply
     */
    @GetMapping("/v1.0/dicts/{id}")
    public Reply getDictKeys(@RequestHeader("loginInfo") String info, @PathVariable String id) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.getDictKeys(loginInfo, id);
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
     * @param dict 字典实体
     * @return Reply
     */
    @PutMapping("/v1.0/dicts")
    public Reply editDict(@RequestHeader("loginInfo") String info, @Valid @RequestBody Dict dict) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.editDict(loginInfo, dict);
    }

    /**
     * 删除字典
     *
     * @param info 用户关键信息
     * @param id   字典ID
     * @return Reply
     */
    @DeleteMapping("/v1.0/dicts")
    public Reply deleteDict(@RequestHeader("loginInfo") String info, @RequestBody String id) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.deleteDict(loginInfo, id);
    }

    /**
     * 新增字典键值
     *
     * @param info    用户关键信息
     * @param dictKey 字典键值实体
     * @return Reply
     */
    @PostMapping("/v1.0/dicts/keys")
    public Reply addDictKey(@RequestHeader("loginInfo") String info, @Valid @RequestBody DictKey dictKey) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.addDictKey(loginInfo, dictKey);
    }

    /**
     * 编辑字典键值
     *
     * @param info    用户关键信息
     * @param dictKey 字典键值实体
     * @return Reply
     */
    @PutMapping("/v1.0/dicts/keys")
    public Reply editDictKey(@RequestHeader("loginInfo") String info, @Valid @RequestBody DictKey dictKey) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.editDictKey(loginInfo, dictKey);
    }

    /**
     * 删除字典键值
     *
     * @param info 用户关键信息
     * @param id   字典ID
     * @return Reply
     */
    @DeleteMapping("/v1.0/dicts/keys")
    public Reply deleteDictKey(@RequestHeader("loginInfo") String info, @RequestBody String id) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.deleteDictKey(loginInfo, id);
    }
}
