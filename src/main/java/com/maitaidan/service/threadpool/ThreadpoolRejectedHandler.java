package com.maitaidan.service.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Crytis on 2016/5/23.
 * Just test.
 */
public class ThreadpoolRejectedHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // TODO: 2016/5/25
    }
}
