package com.persistance.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		registry.addEndpoint("/ws").withSockJS();
		//end point that the clients will use to connect to websocket server
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		registry.setApplicationDestinationPrefixes("/app");
		// "/app" is the point of contact between the broker and the socket
		// tied to message-handling methods... which functions will be triggered for messages in the CTRL
		
		registry.enableSimpleBroker("/topic", "/user/queue");
		// to whom the message will be distributed
	}
}
