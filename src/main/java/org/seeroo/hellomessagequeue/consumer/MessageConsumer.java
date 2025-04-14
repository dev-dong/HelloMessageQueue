package org.seeroo.hellomessagequeue.consumer;

import org.seeroo.hellomessagequeue.model.StockEntity;
import org.seeroo.hellomessagequeue.model.StockRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageConsumer {

    private final StockRepository stockRepository;

    public MessageConsumer(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @RabbitListener(queues = "transactionQueue")
    public void receiveTransaction(StockEntity stockEntity) {
        System.out.println("# received message  = " + stockEntity);

        try {
            stockEntity.setProcessed(true);
            stockEntity.setUpdatedAt(LocalDateTime.now());
            stockRepository.save(stockEntity);
            System.out.println("# StockEntity 저장 완료 ");
        } catch (Exception e) {
            System.out.println("# Entity 수정 에러 " + e.getMessage());
            throw e;
        }
    }
}
