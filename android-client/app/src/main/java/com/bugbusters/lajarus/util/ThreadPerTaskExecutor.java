package com.bugbusters.lajarus.util;

import java.util.concurrent.Executor;

/**
 * Created by vasilis on 11/29/16.
 */

public class ThreadPerTaskExecutor implements Executor {
    @Override
    public void execute(Runnable runnable) {
        new Thread(runnable).start();
    }
}
