package com.huo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class WebsocketserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketserviceApplication.class, args);
	}
}
