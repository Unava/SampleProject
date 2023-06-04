package cz.unava.sampleproject;

import cz.unava.sampleproject.rabbitmq.listeners.ArchiveTransactionListener;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class SpringBootTestContext {

    @MockBean
    ArchiveTransactionListener archiveTransactionListener;

}
