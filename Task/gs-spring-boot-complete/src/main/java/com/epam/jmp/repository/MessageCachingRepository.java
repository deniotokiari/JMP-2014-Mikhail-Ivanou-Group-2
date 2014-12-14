package com.epam.jmp.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.epam.jmp.model.Message;

@Component
public class MessageCachingRepository implements IMessageCachingRepository {
	private List<Message> mMessages;

	@PostConstruct
	public void populateCacheableMessages() {
		mMessages = new ArrayList<>();
		mMessages.add(new Message(1L, "Hello, George. Pleased to meet you. How are you?"));
		mMessages.add(new Message(2L, "Hi, Nick. I'm fine, thanks. And how are you?"));
		mMessages.add(new Message(3L, "Thanks, not so well"));
		mMessages.add(new Message(4L, "How do you do, Mr Brown? It's nice to meet you. I'm Dick Smith, your new student."));
		mMessages.add(new Message(5L, "How do you do, Nick. Glad to meet you too. Where are you from?"));
		mMessages.add(new Message(6L, "I'm from Brighton."));
		mMessages.add(new Message(7L, "Hello, Kate. This is my friend, Fred Black."));
	}

	@Override
	public Message getById(Long id) {
		for (Message message : mMessages) {
			if (message.getId().equals(id)) {
				return message;
			}
		}
		return null;
	}
}
