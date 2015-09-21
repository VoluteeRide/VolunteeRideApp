package com.volunteeride;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.inject.Inject;

/**
 * This class represents the spring configuration for the Spring-Mongo DB data store.
 *
 * Created by ayazlakdawala on 9/11/2015
 */

@Configuration
@EnableMongoAuditing //Needed to enable @CreatedDate annotation on model objects for auditing.
@EnableMongoRepositories("com.volunteeride.dao")
public class MongoDBConfig extends AbstractMongoConfiguration {


    /**
     * Place holder to read properties from the property files configured.
     * The Property files are loaded by @PropertySource in other supporting Config files
     */
    @Inject
    private Environment env;

    /**
     * Return the name of the database to connect to.
     *
     * @return must not be {@literal null}.
     */
    @Override
    protected String getDatabaseName() {
        return env.getProperty("spring.data.mongodb.database");
    }

    /**
     * Return the {@link Mongo} instance to connect to. Annotate with {@link Bean} in case you want to expose a
     * {@link Mongo} instance to the {@link ApplicationContext}.
     *
     * @return
     * @throws Exception
     */
    @Override
    public MongoClient mongo() throws Exception {
        return new MongoClient();
    }

    /**
     * Return the base package to scan for mapped {@link Document}s. Will return the package name of the configuration
     * class' (the concrete class, not this one here) by default. So if you have a {@code com.acme.AppConfig} extending
     * {@link AbstractMongoConfiguration} the base package will be considered {@code com.acme} unless the method is
     * overriden to implement alternate behaviour.
     *
     * @return the base package to scan for mapped {@link Document} classes or {@literal null} to not enable scanning for
     *         entities.
     */
    @Override
    public String getMappingBasePackage() {
        return "com.volunteeride.model";
    }

    /**
     *
     * This Bean provides a mongo template bean that can be used to communicate and fire queries to mongo db database.
     *
     * @return
     * @throws Exception
     */
    public @Bean MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

        // Throw an exception if write result consists of an error.
        mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);

        return mongoTemplate;
    }

    public MongoDbFactory mongoDbFactory() throws Exception {
        //Optionally use database user credentials
        //UserCredentials userCredentials = new UserCredentials("joe", "secret");
        //return new SimpleMongoDbFactory(new Mongo(), "database", userCredentials);

        return new SimpleMongoDbFactory(mongo(), getDatabaseName());

    }

}
