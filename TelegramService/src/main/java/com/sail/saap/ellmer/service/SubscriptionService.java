package com.sail.saap.ellmer.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubscriptionService {
    private final Map<String, List<Long>> subscriptions = new HashMap<>();

    public void addSubscription(long chatId, String key) {
        if (subscriptions.containsKey(key)) {
            subscriptions.get(key).add(chatId);
        } else {
            subscriptions.put(key, new ArrayList<>());
            subscriptions.get(key).add(chatId);
        }
    }

    public void removeSubscription(long chatId, String key) {
        if (subscriptions.containsKey(key)) {
            subscriptions.get(key).remove(chatId);
        }
    }

    public List<Long> getSubscribers(String key) {
        return subscriptions.getOrDefault(key, List.of());
    }
}
