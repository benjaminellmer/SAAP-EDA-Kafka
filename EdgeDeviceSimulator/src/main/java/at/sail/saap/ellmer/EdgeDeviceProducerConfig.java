package at.sail.saap.ellmer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

import java.util.Properties;

public class EdgeDeviceProducerConfig extends Properties {
    public EdgeDeviceProducerConfig() {
        super.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        super.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        super.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    }
}
