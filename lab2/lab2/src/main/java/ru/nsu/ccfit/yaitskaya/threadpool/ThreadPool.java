package ru.nsu.ccfit.yaitskaya.threadpool;

import java.util.ArrayDeque;
import java.util.Queue;

import ru.nsu.ccfit.yaitskaya.factory.Workers.Worker;

public class ThreadPool {
    private final Queue<Task> taskQueue = new ArrayDeque<>();
    private final Thread[] threads;

    public ThreadPool(int threadCount) {
        threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            Worker worker = new Worker(this);
            threads[i] = new Thread(worker, "Factory-Worker-" + i);
            threads[i].start();
        }
    }

    public synchronized Queue<Task> getTaskQueue() {
        return taskQueue;
    }

    public synchronized int getTaskCount() {
        return taskQueue.size();
    }

    public synchronized void addTask(Task task) {
        taskQueue.add(task);
        notify();
    }
}
