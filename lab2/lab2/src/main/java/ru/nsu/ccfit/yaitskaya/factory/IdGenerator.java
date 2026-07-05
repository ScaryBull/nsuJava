package ru.nsu.ccfit.yaitskaya.factory;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final AtomicLong idCounter = new AtomicLong();

    public static long generate() {
        return idCounter.getAndIncrement();
    }
}
