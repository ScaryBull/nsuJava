package ru.nsu.ccfit.yaitskaya.factory;

import ru.nsu.ccfit.yaitskaya.factory.Item.Accessory;
import ru.nsu.ccfit.yaitskaya.factory.Item.Auto;
import ru.nsu.ccfit.yaitskaya.factory.Item.Body;
import ru.nsu.ccfit.yaitskaya.factory.Item.Motor;
import ru.nsu.ccfit.yaitskaya.threadpool.ThreadPool;

public class ProductionController implements Runnable {
    private final Storage<Auto> autoStorage;
    private final ThreadPool threadPool;
    private final Storage<Body> bodyStorage;
    private final Storage<Motor> motorStorage;
    private final Storage<Accessory> accessoryStorage;

    public ProductionController(Storage<Body> bodyStorage, Storage<Motor> motorStorage, Storage<Accessory> accessoryStorage, Storage<Auto> autoStorage, ThreadPool threadPool) {
        this.bodyStorage = bodyStorage;
        this.motorStorage = motorStorage;
        this.accessoryStorage = accessoryStorage;
        this.autoStorage = autoStorage;
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (autoStorage) {
                    while (autoStorage.getStorageSize() + threadPool.getTaskCount() >= autoStorage.getCapacity()) {
                        autoStorage.wait();
                    }
                }

                threadPool.addTask(new BuildCarTask(bodyStorage, motorStorage, accessoryStorage, autoStorage));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
