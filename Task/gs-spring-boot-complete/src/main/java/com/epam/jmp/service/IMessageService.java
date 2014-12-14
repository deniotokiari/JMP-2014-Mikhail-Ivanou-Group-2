package com.epam.jmp.service;

import com.epam.jmp.model.Message;

public interface IMessageService {
    Message getById(Long id);
}
