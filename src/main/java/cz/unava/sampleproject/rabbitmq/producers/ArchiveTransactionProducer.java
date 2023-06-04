package cz.unava.sampleproject.rabbitmq.producers;

import cz.unava.sampleproject.model.ArchiveTransaction;
import cz.unava.sampleproject.rabbitmq.RabbitMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArchiveTransactionProducer {

    private static final Logger logger = LoggerFactory.getLogger(ArchiveTransactionProducer.class);

    private RabbitTemplate rabbitTemplate;

    public void produceArchiveTransactionMessage(final ArchiveTransaction archiveTransaction) {
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.ROUTING_KEY, archiveTransaction);
        logger.info("ArchiveTransaction message was sent. {}ß", archiveTransaction);
    }

    // SETTERs

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
