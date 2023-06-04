package cz.unava.sampleproject.component;

import cz.unava.sampleproject.model.HostDataRequest;
import cz.unava.sampleproject.model.HostResponse;

public interface Host {

    HostResponse authorizeTransaction(final HostDataRequest dataRequest);

}
