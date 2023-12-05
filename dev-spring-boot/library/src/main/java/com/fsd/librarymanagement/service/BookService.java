package com.fsd.librarymanagement.service;

import com.fsd.librarymanagement.entity.Book;


import java.util.List;


public interface BookService {
    Book addBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
}
