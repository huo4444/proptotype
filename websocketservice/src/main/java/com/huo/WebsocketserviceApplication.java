package com.huo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import com.gemstone.gemfire.cache.GemFireCache;


@SpringBootApplication
@ComponentScan(basePackages = "com.huo")
@ImportResource("gemfire-context.xml")
@EnableWebSocket
public class WebsocketserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketserviceApplication.class, args);
	}
}
