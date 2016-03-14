package com.maitaidan.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ResourceBundle;

/**
 * Created by Crytis on 2016/3/13.
 * Just test.
 */
public class ConfigUtil {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("user");

    public static String getPassword(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        if (!resourceBundle.containsKey(username)) {
            return null;
        }
        return resourceBundle.getString(username);
    }
}
