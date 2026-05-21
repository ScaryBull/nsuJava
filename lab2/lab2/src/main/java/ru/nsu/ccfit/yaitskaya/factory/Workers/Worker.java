package ru.nsu.ccfit.yaitskaya.factory.Workers;

import ru.nsu.ccfit.yaitskaya.threadpool.*;

public class Worker implements Runnable{
    private final ThreadPool pool;

    public Worker(ThreadPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Task task;
                synchronized (pool) {
                    while (pool.getTaskCount() == 0) {
                        pool.wait();
                    }
                    task = pool.getTaskQueue().poll();
                }

                if (task != null) {
                    task.execute();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
