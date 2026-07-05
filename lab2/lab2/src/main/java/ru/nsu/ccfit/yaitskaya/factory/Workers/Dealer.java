package ru.nsu.ccfit.yaitskaya.factory.Workers;

import ru.nsu.ccfit.yaitskaya.factory.Storage;
import ru.nsu.ccfit.yaitskaya.factory.Item.Auto;

public class Dealer implements Runnable {
    private final Storage<Auto> autoStorage;
    private int delay;

    public Dealer(Storage<Auto> autoStorage, int delay) {
        this.autoStorage = autoStorage;
        this.delay = delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(delay);
                Auto car = autoStorage.get();
                System.out.println("Dealer bought car: " + car.getId());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
