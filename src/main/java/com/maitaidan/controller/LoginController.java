package com.maitaidan.controller;

import com.maitaidan.model.GeneralJSONResult;
import com.maitaidan.util.ConfigUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Crytis on 2016/3/13.
 * Just test.
 */
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @ResponseBody
    @RequestMapping("login")
    public GeneralJSONResult<String> login(String username, String password, HttpServletResponse response) {
        logger.info("logging");
        if (StringUtils.isAnyBlank(username, password)) {
            return new GeneralJSONResult<String>(false, "用户名和密码不能为空！", "登录失败！");
        }

        if (password.equals(ConfigUtil.getPassword(username))) {
            Cookie cookie = new Cookie("singpf", "test");
            cookie.setMaxAge(60000);
            response.addCookie(cookie);

            return new GeneralJSONResult<String>(true, "", "登录成功！");
        }
        return new GeneralJSONResult<String>(false, "用户名密码错误!", "登录失败！");
    }
}
