package com.valign.payroll.webservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;


@Configuration
public class SpringMongoConfig extends AbstractReactiveMongoConfiguration {
	
    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDB;

	@Override
	protected String getDatabaseName() {
		return mongoDB;
	}

	@Override
	public MongoClient reactiveMongoClient() {
		String myHost = System.getenv("MONGODB_HOST");		
					
		return MongoClients.create("mongodb://"+(myHost==null ? mongoHost : myHost)+":"+mongoPort);
	}	

}
