package com.insight.basedata.area;

import com.insight.util.ReplyHelper;
import com.insight.util.pojo.Reply;
import org.springframework.web.bind.annotation.*;

/**
 * @author 宣炳刚
 * @date 2020/3/15
 * @remark 行政区划服务控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/common/basedata/area")
public class AreaController {
    private final AreaService service;

    /**
     * 构造方法
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
    public Reply getProvinces() {
        return service.getProvinces();
    }

    /**
     * 查询指定行政区划的下级区划
     *
     * @param id 角色ID
     * @return Reply
     */
    @GetMapping("/v1.0/areas/{id}/subs")
    public Reply getAreas(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return ReplyHelper.invalidParam();
        }

        return service.getAreas(id);
    }
}
