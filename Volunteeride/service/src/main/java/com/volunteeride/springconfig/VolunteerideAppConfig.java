package com.volunteeride.springconfig;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * This class represents the spring configuration for the Volunteeride app supporting Mongo DB data store.
 *
 * @author ayazlakdawala
 * @version Titanium R9.7
 * @since 9/11/2015
 */

@Configuration
@EnableMongoRepositories("com.volunteeride.repositories.mongo")
@ComponentScan(basePackages = "com.volunteeride")
public class VolunteerideAppConfig extends AbstractMongoConfiguration {

    /**
     * Return the name of the database to connect to.
     *
     * @return must not be {@literal null}.
     */
    @Override
    protected String getDatabaseName() {
        return "volunteeride";
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
        return new MongoTemplate(mongoDbFactory());
    }

    public MongoDbFactory mongoDbFactory() throws Exception {
        //Optionally use database user credentials
        //UserCredentials userCredentials = new UserCredentials("joe", "secret");
        //return new SimpleMongoDbFactory(new Mongo(), "database", userCredentials);

        return new SimpleMongoDbFactory(mongo(), getDatabaseName());

    }

}
