package cz.unava.sampleproject.component.impl;

import cz.unava.sampleproject.component.Host;
import cz.unava.sampleproject.enums.ResponseCodeEnum;
import cz.unava.sampleproject.model.HostDataRequest;
import cz.unava.sampleproject.model.HostResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DummyHost implements Host {

    private static final Logger logger = LoggerFactory.getLogger(Host.class);

    @Override
    public HostResponse authorizeTransaction(final HostDataRequest dataRequest) {
        logger.info("Authorizing transaction.");
        return new HostResponse(ResponseCodeEnum.OK);
    }

}
