package com.epam.jmp.repository;

import com.epam.jmp.model.Book;

public interface IBookCachingRepository {
    Book getById(Long id) ;
}
