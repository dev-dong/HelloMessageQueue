package org.seeroo.hellomessagequeue.model;

import lombok.*;

@Getter
@Setter
public class NotificationMessage {
    private String message;

    public NotificationMessage() {
    }

    public NotificationMessage(String message) {
        this.message = message;
    }
}
