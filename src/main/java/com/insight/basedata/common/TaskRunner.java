package com.insight.basedata.common;

import com.insight.basedata.common.mapper.ConfigMapper;
import com.insight.utils.Json;
import com.insight.utils.Redis;
import com.insight.utils.common.ApplicationContextHolder;
import com.insight.utils.pojo.auth.InterfaceDto;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2019-09-11
 * @remark 初始化数据加载器
 */
public class TaskRunner implements ApplicationRunner {
    private final ConfigMapper mapper = ApplicationContextHolder.getContext().getBean(ConfigMapper.class);

    @Override
    public void run(ApplicationArguments args) {
        List<InterfaceDto> configs = mapper.loadConfigs();
        if (configs != null && !configs.isEmpty()) {
            String json = Json.toJson(configs);
            Redis.set("Config:Interface", json);
        }

        String headKey = "Config:DefaultHead";
        String defaultHead = Redis.get(headKey);
        if (defaultHead == null || defaultHead.isEmpty()) {
            Redis.set(headKey, "head_default.png");
        }

        String hostKey = "Config:FileHost";
        String fileHost = Redis.get(hostKey);
        if (fileHost == null || fileHost.isEmpty()) {
            Redis.set(hostKey, "https://images.i-facture.com/");
        }
    }
}
