USE library;

TRUNCATE TABLE book;

INSERT INTO book (title, author, isbn, status) VALUES
   ('To Kill a Mockingbird', 'Harper Lee', '978-0-06-112008-4', 'AVAILABLE'),
   ('1984', 'George Orwell', '978-0-452-28423-4', 'CHECKED_OUT'),
   ('Pride and Prejudice', 'Jane Austen', '978-1-59308-201-7', 'AVAILABLE'),
   ('The Great Gatsby', 'F. Scott Fitzgerald', '978-0-7432-7356-5', 'UNKNOWN'),
   ('Brave New World', 'Aldous Huxley', '978-0-06-085052-4', 'CHECKED_OUT');
