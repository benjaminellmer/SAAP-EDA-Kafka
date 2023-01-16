package at.sail.saap.ellmer;

import org.apache.kafka.clients.producer.ProducerRecord;

public class Equipment {
    private final String id;
    private EquipmentStatus equipmentStatus;

    public Equipment(String edgeDeviceId, int number) {
        this.id = constructIdByEdgeDeviceId(edgeDeviceId, number);
        equipmentStatus = new EquipmentStatus(id);
    }

    private String constructIdByEdgeDeviceId(String edgeDeviceId, int number) {
        return edgeDeviceId + "_EQUIPMENT_" + number;
    }

    public void startSimulation() {
        Thread t = new Thread(new EquipmentSimulator(this));
        t.start();
    }

    public String getId() {
        return id;
    }

    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

}

