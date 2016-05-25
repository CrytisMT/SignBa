package com.maitaidan.service;

import com.maitaidan.dao.SignMapper;
import com.maitaidan.model.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Crytis on 2016/5/25.
 * Just test.
 */
@Slf4j
@Service
public class LogService {

    @Resource
    SignMapper signMapper;

    public boolean addLogs(List<Log> logList) {
        return signMapper.addBatchSignLog(logList);
    }

    public boolean addLog(Log log) {
        return signMapper.addSignLog(log);
    }

    public List<Log> getLogByUserId(int userId) {
        return signMapper.getLogByUserId(userId);
    }
}
