package at.sail.saap.ellmer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.TimeUnit;

public class EquipmentSimulator implements Runnable {
    private final Equipment equipment;
    KafkaProducer<String, String> producer;

    public EquipmentSimulator(Equipment equipment) {
        this.equipment = equipment;
        producer = new KafkaProducer<>(new EdgeDeviceProducerConfig());
    }

    @Override
    public void run() {
        System.out.println("\tStarting the simulation for Equipment: " + equipment.getId());
        boolean running = true;
        do {
            try {
                producer.send(equipment.getEquipmentStatus().getProducerRecord());
                TimeUnit.SECONDS.sleep(3);
                equipment.getEquipmentStatus().increment();
            } catch (InterruptedException e) {
                System.out.println("Sleep of Equipment" + equipment.getId() + "was interrupted!");
            }
        } while (running);
        producer.close();
    }
}
