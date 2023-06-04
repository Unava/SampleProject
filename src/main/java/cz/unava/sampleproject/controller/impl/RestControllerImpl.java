package cz.unava.sampleproject.controller.impl;

import cz.unava.sampleproject.controller.RestController;
import cz.unava.sampleproject.entity.TransactionData;
import cz.unava.sampleproject.exceptions.TransactionException;
import cz.unava.sampleproject.rabbitmq.producers.ArchiveTransactionProducer;
import cz.unava.sampleproject.repository.TransactionDataRepository;
import cz.unava.sampleproject.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.web.bind.annotation.RestController
public class RestControllerImpl implements RestController {

    private final Logger logger = LoggerFactory.getLogger(RestControllerImpl.class);

    private TransactionService transactionService;

    private TransactionDataRepository transactionDataRepository;

    private ArchiveTransactionProducer archiveTransactionProducer;

    @Override
    public void fireTransaction(final Long transactionAmount) throws TransactionException {
        TransactionData data = new TransactionData();
        data.setTransactionAmount(transactionAmount);
        transactionService.processTransaction(data);
        data = transactionDataRepository.save(data);
        logger.info("Transaction was done with dataId {}", data.getId());

        archiveTransactionProducer.produceArchiveTransactionMessage(data.mapToJson());
    }

    // Setters

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setTransactionDataRepository(TransactionDataRepository transactionDataRepository) {
        this.transactionDataRepository = transactionDataRepository;
    }

    @Autowired
    public void setArchiveTransactionProducer(ArchiveTransactionProducer archiveTransactionProducer) {
        this.archiveTransactionProducer = archiveTransactionProducer;
    }

}
