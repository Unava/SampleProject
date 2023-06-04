package cz.unava.sampleproject.model;

import cz.unava.sampleproject.enums.ResponseCodeEnum;

@lombok.Data
public class HostResponse {

    private ResponseCodeEnum responseCode;

    private Data data;

    public HostResponse(ResponseCodeEnum responseCode) {
        this.responseCode = responseCode;
        this.data = Data.dummy();
    }
}
