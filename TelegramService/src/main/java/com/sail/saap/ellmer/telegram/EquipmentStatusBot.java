package com.sail.saap.ellmer.telegram;

import com.sail.saap.ellmer.service.SubscriptionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class EquipmentStatusBot extends TelegramLongPollingBot {
    public static final String DESCRIPTION = """
            Hello you can control me by sending these commands:
            /help - show this menu
            /subscribe $equipmentId - subscribe to equipment status updates
            /unsubscribe $equipmentId - stop the subscription
            """;

    @Autowired
    private SubscriptionService subscriptionService;

    @Override
    public String getBotUsername() {
        return "saap_equipment_status_bot";
    }

    @Override
    public String getBotToken() {
        return "5902495755:AAGNsyj2M7G-HcCF_BjO7MpodHKGcKnwC74";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            if (text.equals("/start") || text.equals("/help")) {
                reply(update, DESCRIPTION);
            } else if (text.startsWith("/subscribe")) {
                String equipmentId = getEquipmentIdIfPossible(update, text);
                if (equipmentId != null) {
                    subscriptionService.addSubscription(update.getMessage().getChatId(), equipmentId);
                    reply(update, "Successfully added subscription!");
                }
            } else if (text.startsWith("/unsubscribe")) {
                String equipmentId = getEquipmentIdIfPossible(update, text);
                if (equipmentId != null) {
                    subscriptionService.removeSubscription(update.getMessage().getChatId(), equipmentId);
                    reply(update, "Successfully removed subscription!");
                }
            } else {
                reply(update, "I do not understand your message, use /help to get help!");
            }
        }
    }

    private String getEquipmentIdIfPossible(Update update, String text) {
        String[] split = text.split(" ");
        if (split.length < 2 || split[1].isEmpty()) {
            handleEquipmentIdError(update);
            return null;
        } else {
            return text.split(" ")[1];
        }
    }

    private void handleEquipmentIdError(Update update) {
        reply(update, "Error! The equipmentId Field is empty.");
    }

    public void sendText(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void reply(Update update, String text) {
        sendText(update.getMessage().getChatId(), text);
    }
}
