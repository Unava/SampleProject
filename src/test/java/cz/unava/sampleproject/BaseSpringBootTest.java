package cz.unava.sampleproject;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = SampleProjectApplication.class)
@Import(SpringBootTestContext.class)
@Transactional
@Rollback
public class BaseSpringBootTest {

}
