package com.maitaidan.dao;

import com.maitaidan.model.Log;
import com.maitaidan.model.SignConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Crytis on 2016/5/23.
 * Just test.
 */
public interface SignMapper {

    SignConfig getSignConfigByUserId(@Param("userId") int userId);

    boolean addSignConfig(SignConfig signConfig);

    List<SignConfig> getAllSignConfig();

    boolean addSignLog(Log log);

    boolean addBatchSignLog(@Param("logList") List<Log> logList);

    List<Log> getLogByUserId(int userId);
}
