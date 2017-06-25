package org.stt.module.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @author wangchao
 *
 */
@ContextConfiguration(locations = { "classpath:config/spring/spring-*.xml" })
public class BaseTest extends AbstractTestNGSpringContextTests {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    public BaseTest(){
        
    }
}
