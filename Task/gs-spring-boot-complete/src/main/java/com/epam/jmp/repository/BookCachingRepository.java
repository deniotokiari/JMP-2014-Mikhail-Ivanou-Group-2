package com.epam.jmp.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.epam.jmp.model.Book;

@Component
public class BookCachingRepository implements IBookCachingRepository {
	private List<Book> mBooks;

	@PostConstruct
	public void populateCacheableBooks() {
		mBooks = new ArrayList<>();
		mBooks.add(new Book(1L, "Hello Android", "http://amzn.to/uCfyDr"));
		mBooks.add(new Book(2L, "Beginning Android 3", "http://amzn.to/wTGcsi"));
		mBooks.add(new Book(3L, "Beginning Android 4 Games Development", "http://www.apress.com/mobile/android/9781430239871"));
		mBooks.add(new Book(4L, "CommonsWare Collection", "http://commonsware.com/"));
	}

	@Override
	public Book getById(Long id) {
		for (Book book : mBooks) {
			if (book.getId().equals(id)) {
				return book;
			}
		}
		return null;
	}
}
