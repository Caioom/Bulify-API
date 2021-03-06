package com.bulefy.api.config;

import java.net.URISyntaxException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DataConfig {
	
	  	@Bean
	    public BasicDataSource dataSource() throws URISyntaxException {
	       /* Configs para deploy
	        * 
	        * URI dbUri = new URI(System.getenv("DATABASE_URL"));
	        String username = dbUri.getUserInfo().split(":")[0];
	        String password = dbUri.getUserInfo().split(":")[1];
	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
			*/
	    	
	    	//Configs para dev
	    	String username = "root";
	        String password = "root";
	        String dbUrl = "jdbc:mysql://localhost:3306/remedios";
			
	        
	        BasicDataSource basicDataSource = new BasicDataSource();
	        basicDataSource.setUrl(dbUrl);
	        basicDataSource.setUsername(username);
	        basicDataSource.setPassword(password);

	        return basicDataSource;
	    }
}
