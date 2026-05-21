package ru.nsu.ccfit.yaitskaya.factory.Workers;

import java.util.logging.Level;
import java.util.logging.Logger;

import ru.nsu.ccfit.yaitskaya.factory.IdGenerator;
import ru.nsu.ccfit.yaitskaya.factory.Storage;
import ru.nsu.ccfit.yaitskaya.factory.Item.Item;
import ru.nsu.ccfit.yaitskaya.factory.Item.ItemType;

public class Supplier<T extends Item> implements Runnable {
    private static final Logger logger = Logger.getLogger(Supplier.class.getName());

    private final Storage<T> storage;
    private final ItemType type;
    private final Class<T> itemClass;
    private int delay;

    public Supplier(Storage<T> storage, ItemType type, Class<T> itemClass, int delay) {
        this.storage = storage;
        this.type = type;
        this.itemClass = itemClass;
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
                long id = IdGenerator.generate();
                T item = itemClass.cast(type.create(id));
                storage.put(item);
                logger.log(Level.INFO, "{0} ID: {1}", new Object[]{itemClass.getSimpleName(), id});
                System.out.println("Produced: " + item.getClass().getSimpleName() + " ID: " + item.getId());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.info("supplier " + itemClass.getSimpleName() + " interrupted");
        }
    }
}
