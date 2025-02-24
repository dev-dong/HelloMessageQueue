package org.seeroo.hellomessagequeue.producer;

import lombok.RequiredArgsConstructor;
import org.seeroo.hellomessagequeue.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publish(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", message); // Fanout에서 routing key는 무시됨.
        System.out.println("[#] Published Notification: " + message);
    }
}
