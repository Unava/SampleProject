package cz.unava.sampleproject.rabbitmq.listeners;

import cz.unava.sampleproject.model.ArchiveTransaction;
import cz.unava.sampleproject.rabbitmq.RabbitMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ArchiveTransactionListener {

    private final Logger logger = LoggerFactory.getLogger(ArchiveTransactionListener.class);

    @RabbitListener(queues = RabbitMQConstants.QUEUE_NAME, messageConverter = "jsonMessageConverter", concurrency = "10")
    public void processMessage(final ArchiveTransaction archiveTransaction) {
        logger.info("There is new incoming transaction. ", archiveTransaction);
    }

}
