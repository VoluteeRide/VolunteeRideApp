package com.volunteeride.springconfig;

import org.springframework.context.annotation.*;

/**
 * Created by ayazlakdawala on 9/17/15.
 */
@Configuration
@Profile("prod")
@PropertySource("classpath:mongodbConfig.properties")
@Import({MongoDBConfig.class})
public class ProdDBConfig {
}
