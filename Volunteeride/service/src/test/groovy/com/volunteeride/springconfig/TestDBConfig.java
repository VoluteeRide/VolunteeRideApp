package com.volunteeride.springconfig;

import org.springframework.context.annotation.*;

/**
 * Created by ayazlakdawala on 9/17/15.
 */
@Configuration
@Profile("test")
@PropertySource("classpath:mongodbTestConfig.properties")
@Import({MongoDBConfig.class})
public class TestDBConfig {
}
