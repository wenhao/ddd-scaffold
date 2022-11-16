package com.github.wenhao.ddd.gateway.client;

import com.github.wenhao.ddd.model.Order;
import org.springframework.stereotype.Component;

// Feign Client in reality
@Component
public class PaymentClient {

    public void pay(Order order, String payType) {
        return;
    }
}
