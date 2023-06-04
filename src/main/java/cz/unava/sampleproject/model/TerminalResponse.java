package cz.unava.sampleproject.model;

import cz.unava.sampleproject.enums.ResponseCodeEnum;

@lombok.Data
public class TerminalResponse {

    private ResponseCodeEnum responseCode;

    private Data data;

    public TerminalResponse(ResponseCodeEnum responseCode) {
        this.responseCode = responseCode;
        this.data = Data.dummy(); // just for this emulation
    }
}
