'use strict';

var chatPage = document.querySelector('#chat-page');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(){
	username = document.querySelector('#name').value.trim();
	
	if(username){
		
		var socket = new SockJS("/ws");
		stompClient = Stomp.over(socket);
		
		stompClient.connect({}, onConnected, onError);
	}
	event.preventDefault;
}

connect();

function onConnected(){
	
	// Subscribe to a topic
	stompClient.subscribe('/topic/public', onMessageReceived);
	
	// tell your name to the broker
	stompClient.send("/app/chat.addUser",{},
			
					  JSON.stringify({sender: username, type : 'JOIN'})
	)
	
	connnectingElement.classList.add('hidden');
}

function onError(error){
	
	connectingElement.textContent = 'Could not connect to Websocket server. Please refresh this page to try again';
	connectingElement.style.color = 'red';
}

function sendMessage(event){
	
	var messageContent = messageInput.value.trim();
	
	if(messageContent && stompclient){// if message box IS NOT empty and there is an active web socket connection
		
		var chatMessage = {
				
				sender : username,
				content: messageInput.value;
				type:    'CHAT';
		};
		
		stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
		messageInput.value = '';
	}
	event.preventDefault();
}

function onMessageReceived(payload){
	
	var message = JSON.parse(payload.body);
	
	var messageElement = document.createElement('li');
	
	if(message.type === 'JOIN'){
		
		messageElement.classList.add('event-message');
		message.content = message.sender + ' joined!';
	
	}else if (message.type === 'LEAVE'){
		
		messageElement.classList.add('event-message');
		message.content = message.sender + ' left!';		
		
	}else{
		
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

// usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)