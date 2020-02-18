package it.joeg.inus.be.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

/**
 *
 */
@Configuration
public class MongoDBConfig {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${test.spring.data.mongodb.uri}")
    private String mongoUriTest;
    @Value("${test.spring.data.mongodb.username}")
    private String usernameTest;
    @Value("${test.spring.data.mongodb.password}")
    private String passwordTest;

    @Value("${production.spring.data.mongodb.uri}")
    private String mongoUriProduction;
    @Value("${production.spring.data.mongodb.username}")
    private String usernameProduction;
    @Value("${production.spring.data.mongodb.password}")
    private String passwordProduction;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        String uri;
        if (activeProfile.equalsIgnoreCase("test")) {
            uri = mongoUriTest.replace("[user]", usernameTest).replace("[password]", passwordTest).replace("[database]", database);
        } else if (activeProfile.equalsIgnoreCase("production")) {
            uri = mongoUriProduction.replace("[user]", usernameProduction).replace("[password]", passwordProduction).replace("[database]", database);
        } else {
            uri = mongoUri;
        }
        return new SimpleMongoClientDbFactory(uri);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }
}
