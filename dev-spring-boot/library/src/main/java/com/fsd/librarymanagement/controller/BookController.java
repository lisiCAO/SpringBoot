package com.fsd.librarymanagement.controller;

import com.fsd.librarymanagement.entity.Book;
import com.fsd.librarymanagement.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public String listBooks(Model theModel) {
        List<Book> theBooks = bookService.getAllBooks();
        theModel.addAttribute("books", theBooks);
        return "books/list-books";
    }

    @GetMapping("/showFormForAdd")
    public String showFromForAdd(Model theModel) {
        Book theBook = new Book();
        theModel.addAttribute("book", theBook);
        return "books/book-form";
    }

    @PostMapping("/save")
    public String saveBook(@Valid @ModelAttribute("book") Book theBook, BindingResult theBindingResult) {
       if(theBindingResult.hasErrors()) {
           return "/books/book-form";
       }

            if (theBook.getId() == null) {
                bookService.addBook(theBook);
            } else {
                bookService.updateBook(theBook.getId(), theBook);
            }

        return "redirect:/books/list";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") Long theId, Model theModel) {
        Book theBook = bookService.getBookById(theId);

        theModel.addAttribute("book", theBook);

        return "books/book-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") Long theId) {
        bookService.deleteBook(theId);

        return "redirect:/books/list";
    }

}
