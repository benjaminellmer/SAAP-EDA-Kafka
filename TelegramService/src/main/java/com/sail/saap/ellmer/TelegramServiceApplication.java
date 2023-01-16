package com.sail.saap.ellmer;

import com.sail.saap.ellmer.telegram.EquipmentStatusBot;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TelegramServiceApplication {
    @Autowired
    private EquipmentStatusBot equipmentStatusBot;

    public static void main(String[] args) {
        SpringApplication.run(TelegramServiceApplication.class, args);
    }

    @PostConstruct
    public void initTelegramBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(equipmentStatusBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
