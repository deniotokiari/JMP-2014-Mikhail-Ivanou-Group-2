package com.epam.jmp.service;

import com.epam.jmp.model.Book;

public interface IBookService {
    Book getById(Long id);
}
