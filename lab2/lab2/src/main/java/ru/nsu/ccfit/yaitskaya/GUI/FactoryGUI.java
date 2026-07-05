package ru.nsu.ccfit.yaitskaya.GUI;

import ru.nsu.ccfit.yaitskaya.factory.Storage;
import ru.nsu.ccfit.yaitskaya.factory.Item.Accessory;
import ru.nsu.ccfit.yaitskaya.factory.Item.Auto;
import ru.nsu.ccfit.yaitskaya.factory.Item.Body;
import ru.nsu.ccfit.yaitskaya.factory.Item.Item;
import ru.nsu.ccfit.yaitskaya.factory.Item.Motor;
import ru.nsu.ccfit.yaitskaya.factory.Workers.Dealer;
import ru.nsu.ccfit.yaitskaya.factory.Workers.Supplier;

import javax.swing.*;

import java.awt.*;
import java.util.List;

public class FactoryGUI extends JFrame{

    public FactoryGUI(
            Storage<Body> bodyStorage,
            Storage<Motor> motorStorage,
            Storage<Accessory> accessoryStorage,
            Storage<Auto> autoStorage,
            List<Supplier<Body>> bodySuppliers,
            List<Supplier<Motor>> motorSuppliers,
            List<Supplier<Accessory>> accessorySuppliers,
            List<Dealer> dealers) {
        setTitle("Car Factory Control Panel");
        setSize(500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1, 10, 10));

        addStoragePanel("Bodies", bodyStorage);
        addStoragePanel("Motors", motorStorage);
        addStoragePanel("Accessories", accessoryStorage);
        addStoragePanel("Ready Cars", autoStorage);

        addSupplierSlider("Body Supplier Delay", bodySuppliers);
        addSupplierSlider("Motor Supplier Delay", motorSuppliers);
        addSupplierSlider("Accessory Suppliers Delay", accessorySuppliers);
        addDealerSlider("Dealers (Demand) Delay", dealers);

        setVisible(true);
    }

    private void addStoragePanel(String name, Storage<?> storage) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(name + ": 0 / " + storage.getCapacity());
        JProgressBar bar = new JProgressBar(0, storage.getCapacity());
        
        panel.add(label, BorderLayout.NORTH);
        panel.add(bar, BorderLayout.CENTER);
        add(panel);

        new Timer(100, e -> {
            int current = storage.getStorageSize();
            bar.setValue(current);
            label.setText(name + ": " + current + " / " + storage.getCapacity());
        }).start();
    }

    private <T extends Item> void addSupplierSlider(String label, List<Supplier<T>> suppliers) {
        add(new JLabel(label + " (ms)"));
        JSlider slider = new JSlider(0, 5000, 1000);
        slider.setMajorTickSpacing(1000);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        slider.addChangeListener(e -> {
            int value = slider.getValue();
            for (Supplier<T> s : suppliers) {
                s.setDelay(value);
            }
        });
        add(slider);
    }

    private void addDealerSlider(String label, List<Dealer> dealers) {
        add(new JLabel(label + " (ms)"));
        JSlider slider = new JSlider(0, 10000, 3000);
        slider.setMajorTickSpacing(2000);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.addChangeListener(e -> {
            int value = slider.getValue();
            for (Dealer d : dealers) {
                d.setDelay(value);
            }
        });
        add(slider);
    }
}
