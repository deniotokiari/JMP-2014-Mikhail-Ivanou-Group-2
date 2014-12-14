package com.epam.jmp.service;

import com.epam.jmp.model.Message;
import com.epam.jmp.repository.IMessageCachingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements IMessageService {

    private IMessageCachingRepository mRepository;

    @Autowired
    public MessageService(IMessageCachingRepository repository) {
        mRepository = repository;
    }

    @Cacheable(value = "messagesCache")
    @Override
    public Message getById(Long id) {
        return mRepository.getById(id);
    }
}
