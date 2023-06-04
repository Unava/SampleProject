package cz.unava.sampleproject.component;

import cz.unava.sampleproject.model.TerminalDataRequest;
import cz.unava.sampleproject.model.TerminalResponse;

public interface Terminal {

    TerminalResponse readCard(final TerminalDataRequest requestData);

    TerminalResponse readCardData(final TerminalDataRequest requestData);

    TerminalResponse processRiskManagement(final TerminalDataRequest requestData);

    TerminalResponse completion(final TerminalDataRequest requestData);

}
