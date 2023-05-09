package com.insight.basedata.area;

import com.insight.basedata.common.dto.AreaListDto;
import com.insight.basedata.common.entity.Area;
import com.insight.basedata.common.entity.LogInfo;
import com.insight.utils.Json;
import com.insight.utils.pojo.auth.LoginInfo;
import com.insight.utils.pojo.base.BusinessException;
import com.insight.utils.pojo.base.Reply;
import com.insight.utils.pojo.base.Search;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/3/15
 * @remark 行政区划服务控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/common/area")
public class AreaController {
    private final AreaService service;

    /**
     * 构造方法
     *
     * @param service AreaService
     */
    public AreaController(AreaService service) {
        this.service = service;
    }

    /**
     * 查询全部省级行政区划
     *
     * @return Reply
     */
    @GetMapping("/v1.0/areas/provinces")
    public List<AreaListDto> getProvinces() {
        return service.getProvinces();
    }

    /**
     * 查询指定行政区划的下级区划
     *
     * @param id 角色ID
     * @return Reply
     */
    @GetMapping("/v1.0/areas/{id}/subs")
    public List<AreaListDto> getAreas(@PathVariable Long id) {
        if (id == null) {
            throw new BusinessException("ID不可为空");
        }

        return service.getAreas(id);
    }

    /**
     * 新增行政区划
     *
     * @param info 用户关键信息
     * @param area 行政区划实体
     * @return Reply
     */
    @PostMapping("/v1.0/areas")
    public Long addArea(@RequestHeader("loginInfo") String info, @Valid @RequestBody Area area) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        return service.addArea(loginInfo, area);
    }

    /**
     * 编辑行政区划
     *
     * @param info 用户关键信息
     * @param id   行政区划ID
     * @param area 行政区划实体
     */
    @PutMapping("/v1.0/areas/{id}")
    public void editArea(@RequestHeader("loginInfo") String info, @PathVariable Long id, @Valid @RequestBody Area area) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);
        area.setId(id);

        service.editArea(loginInfo, area);
    }

    /**
     * 删除行政区划
     *
     * @param info 用户关键信息
     * @param id   行政区划ID
     */
    @DeleteMapping("/v1.0/areas/{id}")
    public void editArea(@RequestHeader("loginInfo") String info, @PathVariable Long id) {
        LoginInfo loginInfo = Json.toBeanFromBase64(info, LoginInfo.class);

        service.deleteArea(loginInfo, id);
    }

    /**
     * 获取日志列表
     *
     * @param loginInfo 用户关键信息
     * @param id        行政区划ID
     * @param search    查询实体类
     * @return Reply
     */
    @GetMapping("/v1.0/areas/{id}/logs")
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
    @GetMapping("/v1.0/areas/logs/{id}")
    public LogInfo getLog(@PathVariable Long id) {
        return service.getLog(id);
    }
}
