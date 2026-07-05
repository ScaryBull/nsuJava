package ru.nsu.ccfit.yaitskaya.factory;

import ru.nsu.ccfit.yaitskaya.factory.Item.Accessory;
import ru.nsu.ccfit.yaitskaya.factory.Item.Auto;
import ru.nsu.ccfit.yaitskaya.factory.Item.Body;
import ru.nsu.ccfit.yaitskaya.factory.Item.Motor;
import ru.nsu.ccfit.yaitskaya.threadpool.Task;

public class BuildCarTask implements Task {
    private final Storage<Body> bodyStorage;
    private final Storage<Motor> motorStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Auto> autoStorage;

    public BuildCarTask(Storage<Body> bodyStorage, Storage<Motor> motorStorage, Storage<Accessory> accessoryStorage, Storage<Auto> autoStorage) {
        this.bodyStorage = bodyStorage;
        this.motorStorage = motorStorage;
        this.accessoryStorage = accessoryStorage;
        this.autoStorage = autoStorage;
    }

    @Override
    public void execute() throws InterruptedException {
        Body body = bodyStorage.get();
        Motor motor = motorStorage.get();
        Accessory accessory = accessoryStorage.get();
        Auto auto = new Auto(IdGenerator.generate(), body, motor, accessory);
        autoStorage.put(auto);
    }    
}
