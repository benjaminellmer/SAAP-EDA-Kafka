package at.sail.saap.ellmer;

import java.util.ArrayList;
import java.util.List;

public class EdgeDevice {
    private final String id;
    private final List<Equipment> equipments;

    public EdgeDevice(int equipmentCount, String customerId, int edNumber) {
        this.id = constructIdByCustomerId(customerId, edNumber);
        equipments = new ArrayList<>();
        for (int i = 0; i < equipmentCount; ++i) {
            equipments.add(new Equipment(id, i));
        }
    }

    public void startSimulation() {
        Thread t = new Thread(new EdgeDeviceSimulator(this));
        t.start();
    }

    private String constructIdByCustomerId(String customerId, int number) {
        return customerId + "_ED_" + number;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public String getId() {
        return id;
    }
}

