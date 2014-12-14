package com.epam.jmp.controller;

import com.epam.jmp.model.Message;
import com.epam.jmp.service.IMessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessagesController {

	private IMessageService messageService;

	@Autowired
	public MessagesController(IMessageService messageService) {
		this.messageService = messageService;
	}

	@RequestMapping(value = "/messages/{id}")
	public @ResponseBody Message getMessage(@PathVariable("id") String id) {

		Message message = messageService.getById(Long.valueOf(id));
		if (message != null) {
			return message;
		}
		return null;
	}

}
