USE library;

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE book;
TRUNCATE TABLE user_role;
TRUNCATE TABLE role;
TRUNCATE TABLE user;



INSERT INTO book (title, author, isbn, status) VALUES
   ('To Kill a Mockingbird', 'Harper Lee', '978-0-06-112008-4', 'AVAILABLE'),
   ('1984', 'George Orwell', '978-0-452-28423-4', 'CHECKED_OUT'),
   ('Pride and Prejudice', 'Jane Austen', '978-1-59308-201-7', 'AVAILABLE'),
   ('The Great Gatsby', 'F. Scott Fitzgerald', '978-0-7432-7356-5', 'UNKNOWN'),
   ('Brave New World', 'Aldous Huxley', '978-0-06-085052-4', 'CHECKED_OUT');

-- Insert role info
INSERT INTO role (name) VALUES ('ROLE_ADMIN'), ('ROLE_EMPLOYEE'), ('ROLE_MANAGER');

-- BCrypt("password123"）
INSERT INTO user (username, password, enabled) VALUES
   ('alice', '$2a$10$Ei4Mz7rgR5CQ9Q5rHxJYU.2MnW3eWwKZ6O8B0/3f7i1FS6G6RjgNi', TRUE), --  'password123'
   ('bob', '$2a$10$JlK6I.SG3ujXRoUJnDgCReF.xpQm5FGuQJW5nDx8W7K9P2tS1feG2', TRUE); --  'password123'

--  1-ADMIN，2-USER
--
INSERT INTO user_role (user_id, role_id) VALUES
     (1, 1), -- Alice-ADMIN
     (1, 2),
     (2, 2),
     (2, 3); -- Bob-MANAGER
SET FOREIGN_KEY_CHECKS = 1;