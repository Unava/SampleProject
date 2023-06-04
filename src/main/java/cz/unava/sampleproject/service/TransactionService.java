package cz.unava.sampleproject.service;

import cz.unava.sampleproject.entity.TransactionData;
import cz.unava.sampleproject.exceptions.TransactionException;

public interface TransactionService {

    void processTransaction(final TransactionData data) throws TransactionException;

}
