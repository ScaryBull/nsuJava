package ru.nsu.ccfit.yaitskaya.factory.Item;

public enum ItemType {
    BODY {
        @Override
        public Item create(long id) {
            return new Body(id);
        }
    },
    MOTOR {
        @Override
        public Item create(long id) {
            return new Motor(id);
        }
    },
    ACCESSORY {
        @Override
        public Item create(long id) {
            return new Accessory(id);
        }
    };

    public abstract Item create(long id);
}
