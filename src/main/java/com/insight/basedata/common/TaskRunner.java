package com.insight.basedata.common;

import com.insight.basedata.common.mapper.ConfigMapper;
import com.insight.utils.Util;
import com.insight.utils.common.ApplicationContextHolder;
import com.insight.utils.pojo.auth.InterfaceDto;
import com.insight.utils.redis.Redis;
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
        if (Util.isNotEmpty(configs)) {
            for (var config : configs) {
                var hash = config.getHash();
                Redis.setHash("Config:Interface", hash, config);
            }
        }

        String headKey = "Config:DefaultHead";
        String defaultHead = Redis.get(headKey);
        if (Util.isEmpty(defaultHead)) {
            Redis.set(headKey, "head_default.png");
        }

        String hostKey = "Config:FileHost";
        String fileHost = Redis.get(hostKey);
        if (Util.isEmpty(fileHost)) {
            Redis.set(hostKey, "https://images.i-facture.com/");
        }
    }
}
