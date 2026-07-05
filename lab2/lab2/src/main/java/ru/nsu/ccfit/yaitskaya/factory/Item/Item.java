package ru.nsu.ccfit.yaitskaya.factory.Item;

public abstract class Item {
    private final long id;

    public Item(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
