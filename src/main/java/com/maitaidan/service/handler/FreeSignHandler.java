package com.maitaidan.service.handler;

import com.google.common.base.Preconditions;
import com.maitaidan.model.Log;
import com.maitaidan.model.SignConfig;
import com.maitaidan.util.HttpUtil;
import com.maitaidan.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by Crytis on 2016/5/23.
 * 全自由的签到器，可以自定参数进行请求
 */
public class FreeSignHandler implements Callable<Log>, SignHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private SignConfig signConfig;

    public FreeSignHandler(SignConfig signConfig) {
        this.signConfig = signConfig;
    }

    @Override
    public Log call() throws Exception {
        if (signConfig == null || StringUtils.isBlank(signConfig.getUrl())) return null;
        Map<String, String> params = null;
        if (StringUtils.isNotBlank(signConfig.getParams())) {
            params = JsonUtils.parse(signConfig.getParams(), Map.class);
        }
        Log log = new Log();
        Preconditions.checkArgument(0 != signConfig.getId(), "signConfig id是空");
        Preconditions.checkArgument(0 != signConfig.getUserId(), "signConfig userId是空");
        log.setUserId(signConfig.getUserId());
        log.setSignConfigId(signConfig.getId());
        switch (signConfig.getMethod()) {
            case 1:
                log.setContent(HttpUtil.doPostWithCookie(signConfig.getUrl(), params, signConfig.getCookie()));
                break;
            case 2:
                log.setContent(HttpUtil.doGetWithCookie(signConfig.getUrl(), params, signConfig.getCookie()));
                break;
            default:
                logger.error("既不是get也不是post!");
                return null;

        }
        return log;
    }
}
