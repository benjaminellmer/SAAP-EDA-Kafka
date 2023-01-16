package at.sail.saap.ellmer;

import java.util.List;

public class EdgeDeviceSimulator implements Runnable {
    private final EdgeDevice edgeDevice;

    public EdgeDeviceSimulator(EdgeDevice edgeDevice) {
        this.edgeDevice = edgeDevice;
    }

    @Override
    public void run() {
        System.out.println("Starting the simulation for Edge Device: " + edgeDevice.getId());
        List<Equipment> equipments = edgeDevice.getEquipments();
        for(Equipment equipment : equipments) {
            equipment.startSimulation();
        }
    }
}