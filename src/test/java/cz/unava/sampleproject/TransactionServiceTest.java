package cz.unava.sampleproject;

import cz.unava.sampleproject.component.Host;
import cz.unava.sampleproject.component.Terminal;
import cz.unava.sampleproject.entity.TransactionData;
import cz.unava.sampleproject.enums.ResponseCodeEnum;
import cz.unava.sampleproject.exceptions.TransactionException;
import cz.unava.sampleproject.model.HostResponse;
import cz.unava.sampleproject.model.TerminalResponse;
import cz.unava.sampleproject.rabbitmq.listeners.ArchiveTransactionListener;
import cz.unava.sampleproject.service.TransactionService;
import cz.unava.sampleproject.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class TransactionServiceTest extends BaseSpringBootTest {

    Iterator<TerminalResponse> terminalResponses;
    Iterator<HostResponse> hostResponses;
    final Answer terminalAnswer = new TerminalAnswer();
    final Answer hostAnswer = new HostAnswer();

    @InjectMocks
    TransactionService transactionService = new TransactionServiceImpl();

    @Mock
    Terminal terminal;

    @Mock
    Host host;

    @Mock
    ArchiveTransactionListener archiveTransactionListener;

    private void defineMocks() {
        MockitoAnnotations.initMocks(this);
        when(terminal.readCard(any())).then(terminalAnswer);
        when(terminal.readCardData(any())).then(terminalAnswer);
        when(terminal.processRiskManagement(any())).then(terminalAnswer);
        when(terminal.completion(any())).then(terminalAnswer);

        when(host.authorizeTransaction(any())).then(hostAnswer);
    }

    @Test
    public void testReadCard() {

        final List<TerminalResponse> responses = new ArrayList<>();
        responses.add(new TerminalResponse(ResponseCodeEnum.NOK));
        terminalResponses = responses.iterator();

        defineMocks();

        final TransactionData data = new TransactionData();
        boolean catchedExeception = false;
        try {
            transactionService.processTransaction(data);
        } catch (TransactionException e) {
            catchedExeception = true;
        }
        assertTrue(catchedExeception);
        assertEquals(ResponseCodeEnum.NOK, data.getResponseCode());
    }

    @Test
    public void testReadCardData() {

        final List<TerminalResponse> responses = new ArrayList<>();
        responses.add(new TerminalResponse(ResponseCodeEnum.OK));
        responses.add(new TerminalResponse(ResponseCodeEnum.NOK));
        terminalResponses = responses.iterator();

        defineMocks();

        final TransactionData data = new TransactionData();
        boolean catchedExeception = false;
        try {
            transactionService.processTransaction(data);
        } catch (TransactionException e) {
            catchedExeception = true;
        }
        assertTrue(catchedExeception);
        assertEquals(ResponseCodeEnum.NOK, data.getResponseCode());
    }

    @Test
    public void testRiskManagement() {

        final List<TerminalResponse> responses = new ArrayList<>();
        responses.add(new TerminalResponse(ResponseCodeEnum.OK));
        responses.add(new TerminalResponse(ResponseCodeEnum.OK));
        responses.add(new TerminalResponse(ResponseCodeEnum.NOK));
        terminalResponses = responses.iterator();

        defineMocks();

        final TransactionData data = new TransactionData();
        boolean catchedExeception = false;
        try {
            transactionService.processTransaction(data);
        } catch (TransactionException e) {
            catchedExeception = true;
        }
        assertTrue(catchedExeception);
        assertEquals(ResponseCodeEnum.NOK, data.getResponseCode());
    }

    @Test
    public void testCompletion() {

        final List<HostResponse> respHost = new ArrayList<>();
        respHost.add(new HostResponse(ResponseCodeEnum.OK));
        hostResponses = respHost.iterator();

        final List<TerminalResponse> responses = new ArrayList<>();
        responses.add(new TerminalResponse(ResponseCodeEnum.OK));
        responses.add(new TerminalResponse(ResponseCodeEnum.OK));
        responses.add(new TerminalResponse(ResponseCodeEnum.OK));
        responses.add(new TerminalResponse(ResponseCodeEnum.NOK));
        terminalResponses = responses.iterator();

        defineMocks();

        final TransactionData data = new TransactionData();
        boolean catchedExeception = false;
        try {
            transactionService.processTransaction(data);
        } catch (TransactionException e) {
            catchedExeception = true;
        }
        assertTrue(catchedExeception);
        assertEquals(ResponseCodeEnum.NOK, data.getResponseCode());
    }

    @Test
    public void testBadHostResponse() {

        final List<HostResponse> respHost = new ArrayList<>();
        respHost.add(new HostResponse(ResponseCodeEnum.NOK));
        hostResponses = respHost.iterator();

        final List<TerminalResponse> responses = new ArrayList<>();
        responses.add(new TerminalResponse(ResponseCodeEnum.OK));
        responses.add(new TerminalResponse(ResponseCodeEnum.OK));
        responses.add(new TerminalResponse(ResponseCodeEnum.OK));
        responses.add(new TerminalResponse(ResponseCodeEnum.OK));
        terminalResponses = responses.iterator();

        defineMocks();

        final TransactionData data = new TransactionData();
        boolean catchedExeception = false;
        try {
            transactionService.processTransaction(data);
        } catch (TransactionException e) {
            catchedExeception = true;
        }
        assertTrue(catchedExeception);
        assertEquals(ResponseCodeEnum.NOK, data.getResponseCode());
    }

    @Test
    public void testSuccessfullTransaction() {

        final List<HostResponse> respHost = new ArrayList<>();
        respHost.add(new HostResponse(ResponseCodeEnum.OK));
        hostResponses = respHost.iterator();

        final List<TerminalResponse> responses = new ArrayList<>();
        responses.add(new TerminalResponse(ResponseCodeEnum.OK));
        responses.add(new TerminalResponse(ResponseCodeEnum.OK));
        responses.add(new TerminalResponse(ResponseCodeEnum.OK));
        responses.add(new TerminalResponse(ResponseCodeEnum.OK));
        terminalResponses = responses.iterator();

        defineMocks();

        final TransactionData data = new TransactionData();
        boolean catchedExeception = false;
        try {
            transactionService.processTransaction(data);
        } catch (TransactionException e) {
            catchedExeception = true;
        }
        assertFalse(catchedExeception);
        assertEquals(ResponseCodeEnum.OK, data.getResponseCode());
    }

    //subCLASSes

    class TerminalAnswer implements Answer<TerminalResponse> {

        @Override
        public TerminalResponse answer(InvocationOnMock invocationOnMock) throws Throwable {
            return terminalResponses.next();
        }
    }

    class HostAnswer implements Answer<HostResponse> {

        @Override
        public HostResponse answer(InvocationOnMock invocationOnMock) throws Throwable {
            return hostResponses.next();
        }
    }

}


