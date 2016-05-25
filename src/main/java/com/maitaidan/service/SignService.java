package com.maitaidan.service;

import com.google.common.collect.Lists;
import com.maitaidan.dao.SignMapper;
import com.maitaidan.model.Log;
import com.maitaidan.model.SignConfig;
import com.maitaidan.service.handler.FreeSignHandler;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Crytis on 2016/5/23.
 * Just test.
 */
@Service
public class SignService {
    @Resource
    SignMapper signMapper;
    @Resource
    ThreadPoolTaskExecutor taskExecutor;
    @Resource
    LogService logService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 自由类型的批量签到
     *
     * @param signConfigList
     */
    public void doSign(List<SignConfig> signConfigList) {
        if (CollectionUtils.isEmpty(signConfigList)) {
            return;
        }
        ArrayList<Future<Log>> futures = Lists.newArrayListWithExpectedSize(signConfigList.size());
        ArrayList<Log> results = Lists.newArrayListWithExpectedSize(signConfigList.size());
        for (SignConfig signConfig : signConfigList) {
            futures.add(taskExecutor.submit(new FreeSignHandler(signConfig)));
        }
        for (Future<Log> future : futures) {
            try {
                results.add(future.get());
            } catch (Exception e) {
                logger.error("获取结果异常！{}", e);
            }
        }
        logService.addLogs(results);
    }

    public boolean addFreeSignConfig(SignConfig signConfig) {
        logger.info("添加自由签到配置:{}", signConfig);
        return signMapper.addSignConfig(signConfig);
    }
}
