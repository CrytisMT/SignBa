package com.maitaidan.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Crytis on 2016/5/25.
 * Just test.
 */
@Slf4j
@Component
public class TimeAspect {
    public void readTime(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Scheduled annotation = signature.getMethod().getAnnotation(Scheduled.class);
        log.info("修改执行时间待定");
        //TODO
    }
}

