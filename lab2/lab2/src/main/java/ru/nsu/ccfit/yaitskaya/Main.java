package ru.nsu.ccfit.yaitskaya;

import ru.nsu.ccfit.yaitskaya.GUI.FactoryGUI;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import ru.nsu.ccfit.yaitskaya.factory.*;
import ru.nsu.ccfit.yaitskaya.factory.Item.*;
import ru.nsu.ccfit.yaitskaya.factory.Workers.*;
import ru.nsu.ccfit.yaitskaya.threadpool.ThreadPool;

import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int bodyStorageSize = Integer.parseInt(props.getProperty("StorageBodySize", "100"));
        int motorStorageSize = Integer.parseInt(props.getProperty("StorageMotorSize", "100"));
        int accStorageSize = Integer.parseInt(props.getProperty("StorageAccessorySize", "100"));
        int autoStorageSize = Integer.parseInt(props.getProperty("StorageAutoSize", "100"));
        
        int accSuppliersCount = Integer.parseInt(props.getProperty("AccessorySuppliers", "1"));
        int workersCount = Integer.parseInt(props.getProperty("Workers", "2"));
        int dealersCount = Integer.parseInt(props.getProperty("Dealers", "1"));
        
        Storage<Body> bodyStorage = new Storage<>(bodyStorageSize);
        Storage<Motor> motorStorage = new Storage<>(motorStorageSize);
        Storage<Accessory> accessoryStorage = new Storage<>(accStorageSize);
        Storage<Auto> autoStorage = new Storage<>(autoStorageSize);
        ThreadPool pool = new ThreadPool(workersCount);

        List<Supplier<Body>> bodySuppliers = new ArrayList<>();
        List<Supplier<Motor>> motorSuppliers = new ArrayList<>();
        List<Supplier<Accessory>> accessorySuppliers = new ArrayList<>();
        List<Dealer> dealers = new ArrayList<>();

        Supplier<Body> bSup = new Supplier<>(bodyStorage, ItemType.BODY, Body.class, 1000);
        bodySuppliers.add(bSup);
        new Thread(bSup, "BodySupplier").start();

        Supplier<Motor> mSup = new Supplier<>(motorStorage, ItemType.MOTOR, Motor.class, 1000);
        motorSuppliers.add(mSup);
        new Thread(mSup, "MotorSupplier").start();

        for (int i = 0; i < accSuppliersCount; i++) {
            Supplier<Accessory> aSup = new Supplier<>(accessoryStorage, ItemType.ACCESSORY, Accessory.class, 2000);
            accessorySuppliers.add(aSup);
            new Thread(aSup, "AccSupplier-" + i).start();
        }

        Thread controller = new Thread(new ProductionController(bodyStorage, motorStorage, accessoryStorage, autoStorage, pool), "ProductionController");
        controller.start();

        for (int i = 0; i < dealersCount; i++) {
            Dealer d = new Dealer(autoStorage, 3000);
            dealers.add(d);
            new Thread(d, "Dealer-" + i).start();
        }

        SwingUtilities.invokeLater(() -> {
            new FactoryGUI(
                bodyStorage, motorStorage, accessoryStorage, autoStorage,
                bodySuppliers, motorSuppliers, accessorySuppliers, dealers
            );
        });

        System.out.println("Factory started with " + workersCount + " workers and " + dealersCount + " dealers.");
    }
}