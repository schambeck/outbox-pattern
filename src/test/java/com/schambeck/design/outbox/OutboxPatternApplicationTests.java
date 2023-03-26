package com.schambeck.design.outbox;

import com.schambeck.design.outbox.config.RabbitTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(RabbitTestConfiguration.class)
class OutboxPatternApplicationTests {
    
    @Test
    void contextLoads() {
    }
    
}
