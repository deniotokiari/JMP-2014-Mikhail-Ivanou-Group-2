package com.epam.jmp.repository;

import com.epam.jmp.model.Message;

public interface IMessageCachingRepository {
    Message getById(Long id) ;
}
