package com.github.wenhao.ddd.gateway.client;

import com.github.wenhao.ddd.model.Order;
import org.springframework.stereotype.Component;

// Feign Client in reality
@Component
public class NotificationClient {
    public void notify(Order order) {
        return;
    }
}
