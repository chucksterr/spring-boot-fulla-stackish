package com.persistance.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.persistance.models.ChatMessage;

@Controller
public class ChatCtrl {

	@MessageMapping("/chat.sendMsg")
	@SendTo("/topic/public")
	public ChatMessage sendMsg(@Payload ChatMessage chatMessage) {
		
		return chatMessage;
	}
	
	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor access) {
		
		access.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}
}
