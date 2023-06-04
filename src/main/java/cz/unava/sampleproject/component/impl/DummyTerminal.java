package cz.unava.sampleproject.component.impl;

import cz.unava.sampleproject.component.Terminal;
import cz.unava.sampleproject.enums.ResponseCodeEnum;
import cz.unava.sampleproject.model.TerminalDataRequest;
import cz.unava.sampleproject.model.TerminalResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DummyTerminal implements Terminal {

    //That's just the emulation of the terminal processing -> need real implementation of the terminal interface

    private static final Logger logger = LoggerFactory.getLogger(Terminal.class);

    @Override
    public TerminalResponse readCard(final TerminalDataRequest requestData) {
        logger.info("Ask terminal to read card with some init data.");
        return new TerminalResponse(ResponseCodeEnum.OK);
    }

    @Override
    public TerminalResponse readCardData(final TerminalDataRequest requestData) {
        logger.info("Ask terminal for card data before risk management");
        return new TerminalResponse(ResponseCodeEnum.OK);
    }

    @Override
    public TerminalResponse processRiskManagement(final TerminalDataRequest requestData) {
        logger.info("Risk management processing.... (PIN etc. depends on configuration)");
        return new TerminalResponse(ResponseCodeEnum.OK);
    }

    @Override
    public TerminalResponse completion(final TerminalDataRequest requestData) {
        logger.info("Process 2nd cryptogram.");
        return new TerminalResponse(ResponseCodeEnum.OK);
    }
}
