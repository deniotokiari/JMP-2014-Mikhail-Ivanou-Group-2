package com.epam.jmp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.epam.jmp.model.Book;
import com.epam.jmp.repository.IBookCachingRepository;

@Service
public class BookService implements IBookService {

    private IBookCachingRepository mRepository;

    @Autowired
    public BookService(IBookCachingRepository repository) {
        mRepository = repository;
    }

    @Cacheable(value = "booksCache")
    @Override
    public Book getById(Long id) {
        return mRepository.getById(id);
    }
}
