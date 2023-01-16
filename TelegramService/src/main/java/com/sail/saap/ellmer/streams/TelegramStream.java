package com.sail.saap.ellmer.streams;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sail.saap.ellmer.model.EquipmentStatusDTO;
import com.sail.saap.ellmer.service.SubscriptionService;
import com.sail.saap.ellmer.telegram.EquipmentStatusBot;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelegramStream {
    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private EquipmentStatusBot equipmentStatusBot;

    @Autowired
    void buildStream(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder.stream("EquipmentStatus");
        Gson gson = new Gson();
        stream.foreach((key, value) -> {
            try {
                EquipmentStatusDTO status = gson.fromJson(value, EquipmentStatusDTO.class);
                subscriptionService.getSubscribers(key).forEach(it -> {
                    equipmentStatusBot.sendText(it, status.toString());
                });
            } catch (JsonSyntaxException ex) {
                System.out.println("Found invalid entry: " + value);
            }
        });
    }
}
