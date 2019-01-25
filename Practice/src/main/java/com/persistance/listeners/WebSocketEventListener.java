package com.persistance.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.persistance.models.ChatMessage;

@Component
public class WebSocketEventListener {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
	
	@Autowired
	SimpMessageSendingOperations messagingTemplate;
	
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent connected) {
		
		logger.info("Web socket connection successful");
	}
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent disconnected) {
		
		StompHeaderAccessor access = StompHeaderAccessor.wrap(disconnected.getMessage());
		
		String username = (String) access.getSessionAttributes().get("username");
		
		if(username != null) {
			
			logger.info("User disconnection: " + username);
			
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setType(ChatMessage.MessageType.LEAVE);
			chatMessage.setSender(username);
			
			messagingTemplate.convertAndSend("/topic/publicChatRoom", chatMessage);
		}
	}
}
