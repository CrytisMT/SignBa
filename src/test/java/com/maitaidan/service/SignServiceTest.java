package com.maitaidan.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.maitaidan.dao.SignMapper;
import com.maitaidan.model.SignConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by Crytis on 2016/5/23.
 * Just test.
 */
@Test
@ContextConfiguration(locations = "classpath:dispatch-servlet.xml")
public class SignServiceTest extends AbstractTestNGSpringContextTests {

    @Resource
    SignService signService;
    @Resource
    SignMapper signMapper;

    @Test
    public void testGet() throws Exception {
        HashMap<String, String> map = Maps.newHashMap();
//        map.put("a", "b");
//        map.put("c", "d");
//        SignConfig s1 = new SignConfig(1, "smzdm_user_source=F4B87984920BE428215D02BB5788CD30; bdshare_firstime=1461318662016; __jsluid=e5004c93f645fc30f2cf580cb833d2a5; smzdm_user_view=3739903583F765CE36D2163E91B9399C; PHPSESSID=9kmo4k7rvvfsplsu5qk8c78dk3; smzdm_wordpress_360d4e510beef4fe51293184b8908074=qq_ageoi%7C1467878649%7C1aea16f6f3b5835533f688472dd93cc0; smzdm_wordpress_logged_in_360d4e510beef4fe51293184b8908074=qq_ageoi%7C1467878649%7Cba32356ed5025f909d0b1e25302e6980; user-role-smzdm=subscriber; sess=Y2U4ODd8MTQ2Nzg3ODY0OXw2NDY2NzU2MTc5fGY0NzRjYzZkMzVjYjdhNWFiNGYxMTI3YTQ2YzI0OWIy; user=qq_ageoi%7C6466756179; userId=6466756179", 1, null, "", "http://zhiyou.smzdm.com/user/checkin/jsonp_checkin", 0);
//        SignConfig s2 = new SignConfig(2, "", 1, JsonUtils.toJSONString(map), "", "http://mock.com/mockserver/a", 0);
//        ArrayList<SignConfig> objects = Lists.newArrayList(s1);
        SignConfig signConfig = signMapper.getSignConfigByUserId(1);
        SignConfig signConfig2 = signMapper.getSignConfigByUserId(1);
        System.out.println(signConfig);
        signService.doSign(Lists.<SignConfig>newArrayList(signConfig, signConfig2));
    }

    @Test
    public void sign() {
//        String s = HttpUtil.doGetWithCookie("http://zhiyou.smzdm.com/user/checkin/jsonp_checkin", null, "smzdm_user_source=F4B87984920BE428215D02BB5788CD30; smzdm_user_view=3739903583F765CE36D2163E91B9399C; PHPSESSID=9kmo4k7rvvfsplsu5qk8c78dk3; __jsluid=7bfc6b3c5d25243d27d8aa7ec4490dfe; smzdm_wordpress_360d4e510beef4fe51293184b8908074=qq_ageoi%7C1467878649%7C1aea16f6f3b5835533f688472dd93cc0; smzdm_wordpress_logged_in_360d4e510beef4fe51293184b8908074=qq_ageoi%7C1467878649%7Cba32356ed5025f909d0b1e25302e6980; user-role-smzdm=subscriber; sess=Y2U4ODd8MTQ2Nzg3ODY0OXw2NDY2NzU2MTc5fGY0NzRjYzZkMzVjYjdhNWFiNGYxMTI3YTQ2YzI0OWIy; user=qq_ageoi%7C6466756179");
//        System.out.println(s);
//        String s1 = StringEscapeUtils.unescapeJson(s);
//        System.out.println(s1);
        System.out.println(signMapper.getAllSignConfig());
    }

}