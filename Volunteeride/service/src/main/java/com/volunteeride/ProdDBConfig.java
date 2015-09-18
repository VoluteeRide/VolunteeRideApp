package com.volunteeride;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by ayazlakdawala on 9/17/15.
 */
@Configuration
@Profile("prod")
@PropertySource("classpath:mongodbConfig.properties")
@Import({MongoDBConfig.class})
public class ProdDBConfig {
}
