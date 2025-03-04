package org.seeroo.hellomessagequeue.controller;

import org.seeroo.hellomessagequeue.publisher.NewsPublisher;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class NewsController {

    private final NewsPublisher newsPublisher;

    public NewsController(NewsPublisher newsPublisher) {
        this.newsPublisher = newsPublisher;
    }

    @MessageMapping("/subscribe")
    public void handleSubscribe(@Header("newsType") String newsType) {
        System.out.println("[#] newsType: " + newsType);
        String newsMessage = newsPublisher.publish(newsType);
        System.out.println("[#] newsMessage: " + newsMessage);
    }
}
