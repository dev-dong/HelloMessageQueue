package org.seeroo.hellomessagequeue;

import org.seeroo.hellomessagequeue.publisher.LogPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomExceptionHandler {

    private final LogPublisher logPublisher;

    public CustomExceptionHandler(LogPublisher logPublisher) {
        this.logPublisher = logPublisher;
    }

    public void handleException(Exception e) {
        String message = e.getMessage();

        String routingKey;

        if (e instanceof NullPointerException) {
            routingKey = "log.error";
        } else if (e instanceof IllegalArgumentException) {
            routingKey = "log.warn";
        } else {
            routingKey = "log.error";
        }

        logPublisher.publish(routingKey, "Exception이 발생했음 : " + message);
    }

    // 메시지 처리
    public void handleMessage(String message) {
        String routingKey = "log.info";
        logPublisher.publish(routingKey, "Info Log : " + message);
    }
}
