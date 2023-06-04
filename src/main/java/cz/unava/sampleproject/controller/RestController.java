package cz.unava.sampleproject.controller;

import cz.unava.sampleproject.exceptions.TransactionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.web.bind.annotation.RestController
public interface RestController {

    @PostMapping(path = "/start")
    void fireTransaction(final Long transactionAmount) throws TransactionException;



}
