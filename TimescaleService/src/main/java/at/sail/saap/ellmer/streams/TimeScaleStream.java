package at.sail.saap.ellmer.streams;

import at.sail.saap.ellmer.model.EquipmentStatusDTO;
import at.sail.saap.ellmer.repository.EquipmentStatusRepository;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeScaleStream {
   private final EquipmentStatusRepository equipmentStatusRepository;

    public TimeScaleStream(EquipmentStatusRepository equipmentStatusRepository) {
        this.equipmentStatusRepository = equipmentStatusRepository;
    }

    @Autowired
    void buildStream(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder.stream("EquipmentStatus");
        Gson gson = new Gson();
        stream.foreach((key, value) -> {
            try {
                EquipmentStatusDTO status = gson.fromJson(value, EquipmentStatusDTO.class);
                equipmentStatusRepository.save(status.toEquipmentStatus());
                System.out.println(status);
            } catch (JsonSyntaxException ex) {
                System.out.println("Found invalid entry: " + value);
            }
        });
    }
}
