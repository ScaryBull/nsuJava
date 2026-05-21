package ru.nsu.ccfit.yaitskaya.threadpool;

public interface Task {
    void execute() throws InterruptedException;
}
