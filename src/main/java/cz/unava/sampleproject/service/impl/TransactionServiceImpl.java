package cz.unava.sampleproject.service.impl;

import cz.unava.sampleproject.component.Host;
import cz.unava.sampleproject.component.Terminal;
import cz.unava.sampleproject.entity.TransactionData;
import cz.unava.sampleproject.enums.ResponseCodeEnum;
import cz.unava.sampleproject.exceptions.TransactionException;
import cz.unava.sampleproject.model.HostDataRequest;
import cz.unava.sampleproject.model.HostResponse;
import cz.unava.sampleproject.model.TerminalDataRequest;
import cz.unava.sampleproject.model.TerminalResponse;
import cz.unava.sampleproject.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private Terminal terminal;

    private Host host;

    @Transactional
    @Override
    public void processTransaction(final TransactionData data) throws TransactionException {
        logger.info("Transaction was performed.");
        data.setStartTransaction();

        final TerminalResponse cardReadResponse = terminal.readCard(TerminalDataRequest.generateDummy());
        if (ResponseCodeEnum.NOK.equals(cardReadResponse.getResponseCode())) {
            errorReportHandling(data, "Unable to read card.");
        }

            // process TerminalResponse data and prepare follow TerminalDataRequest data

        final TerminalResponse cardDataResponse = terminal.readCardData(TerminalDataRequest.generateDummy());
        if (ResponseCodeEnum.NOK.equals(cardDataResponse.getResponseCode())) {
            errorReportHandling(data, "Unable to read card data.");
        }

            // Validate terminal data and prepare data for emv risk management

        final TerminalResponse riskManagementResponse = terminal.processRiskManagement(TerminalDataRequest.generateDummy());
        if (ResponseCodeEnum.NOK.equals(riskManagementResponse.getResponseCode())) {
            errorReportHandling(data, "Risk management was failed. - Bad PIN, Signature or some of others conditions.");
        }

            // Prepare host message for authorization

        final HostResponse hostResponse = host.authorizeTransaction(HostDataRequest.generateDummy());
        if (ResponseCodeEnum.NOK.equals(hostResponse.getResponseCode())) {
            errorReportHandling(data, "Unable to authorize transaction with the host.");
        }

            // Take cryptogram and do second authorization

        final TerminalResponse completionResponse = terminal.completion(TerminalDataRequest.generateDummy());
        if (ResponseCodeEnum.NOK.equals(completionResponse.getResponseCode())) {
            errorReportHandling(data, "Completion was failed.");
        }

        data.setEndTransaction();
        data.setResponseCode(ResponseCodeEnum.OK);
        logger.info("Transaction was performed successfully!");
    }

    private void errorReportHandling(final TransactionData data, final String message) throws TransactionException {
        data.setResponseCode(ResponseCodeEnum.NOK);
        throw new TransactionException(message);
    }


    // SETTERs

    @Autowired
    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    @Autowired
    public void setHost(Host host) {
        this.host = host;
    }

}
