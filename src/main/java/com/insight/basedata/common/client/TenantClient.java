package com.insight.basedata.common.client;

import com.insight.basedata.common.config.FeignClientConfig;
import com.insight.utils.pojo.base.Reply;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 宣炳刚
 * @date 2019-08-31
 * @remark Feign客户端
 */
@FeignClient(name = "base-tenant", configuration = FeignClientConfig.class)
public interface TenantClient {

    /**
     * 查询指定ID的租户绑定的应用集合
     *
     * @param id 租户ID
     * @return Reply
     */
    @GetMapping("/base/tenant/v1.0/tenants/{id}/apps")
    Reply getApps(@PathVariable Long id);
}
