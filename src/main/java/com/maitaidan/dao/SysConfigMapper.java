package com.maitaidan.dao;

/**
 * Created by Crytis on 2016/5/25.
 * Just test.
 */
public interface SysConfigMapper {
    String getConfig(String key);

    String updateConfig(String key, String value);
}
