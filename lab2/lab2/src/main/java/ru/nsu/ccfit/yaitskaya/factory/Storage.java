package ru.nsu.ccfit.yaitskaya.factory;

import java.util.ArrayDeque;
import java.util.Queue;

import ru.nsu.ccfit.yaitskaya.factory.Item.Item;

public class Storage<T extends Item> {
    private final int capacity;
    private final Queue<T> items = new ArrayDeque<>();

    public Storage(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public synchronized void put(T item) throws InterruptedException {
        while (items.size() == capacity) {
            wait();
        }
        
        items.add(item);
        notifyAll();
    }

    public synchronized T get() throws InterruptedException {
        while (items.isEmpty()) {
            wait();
        }

        T item = items.poll();
        notifyAll();
        return item;
    }

    public synchronized int getStorageSize() {
        return items.size();
    }
}
