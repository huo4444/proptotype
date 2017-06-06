package com.huo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import com.gemstone.gemfire.cache.GemFireCache;

import java.util.Properties;

@SpringBootApplication
@EnableGemfireRepositories
@EnableWebSocket
public class WebsocketserviceApplication {
	@Bean
	Properties gemfireProperties() {
		Properties gemfireProperties = new Properties();
		gemfireProperties.setProperty("name", "DataGemFireApplication");
		gemfireProperties.setProperty("mcast-port", "0");
		gemfireProperties.setProperty("log-level", "config");
		return gemfireProperties;
	}

	@Bean
	CacheFactoryBean gemfireCache() {
		CacheFactoryBean gemfireCache = new CacheFactoryBean();
		gemfireCache.setClose(true);
		gemfireCache.setProperties(gemfireProperties());
		return gemfireCache;
	}

	@Bean
	LocalRegionFactoryBean<String, Person> helloRegion(final GemFireCache cache) {
		LocalRegionFactoryBean<String, Person> helloRegion = new LocalRegionFactoryBean<>();
		helloRegion.setCache(cache);
		helloRegion.setClose(false);
		helloRegion.setName("hello");
		helloRegion.setPersistent(false);
		return helloRegion;
	}


	@Autowired
	PersonRepository personRepository;
	public static void main(String[] args) {
		SpringApplication.run(WebsocketserviceApplication.class, args);

	}
}
