package com.maitaidan.service;

import com.maitaidan.dao.SignMapper;
import com.maitaidan.model.SignConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Crytis on 2016/5/25.
 * Just test.
 */
@Slf4j
@Service
public class SignTask {

    @Resource
    private SignService signService;
    @Resource
    SignMapper signMapper;
    private Date runtime;

    @Scheduled(cron = "0/1 0/1 * * * ?")
    public void run() {
        List<SignConfig> allSignConfig = signMapper.getAllSignConfig();
        signService.doSign(allSignConfig);
    }
}
