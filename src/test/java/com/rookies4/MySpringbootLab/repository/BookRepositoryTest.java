package com.rookies4.MySpringbootLab.repository;

import com.rookies4.MySpringbootLab.entity.Book;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("prod")
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    @Disabled
    void testCreateBook(){
        Book book1 = new Book();
        book1.setTitle("스프링 부트 입문");
        book1.setAuthor("홍길동");
        book1.setIsbn("9788956746425");
        book1.setPrice(30000);
        book1.setPublishDate(LocalDateTime.of(2025, 5, 7, 0, 0));

        Book book2 = new Book();
        book2.setTitle("JPA 프로그래밍");
        book2.setAuthor("박둘리");
        book2.setIsbn("9788956746432");
        book2.setPrice(35000);
        book2.setPublishDate(LocalDateTime.of(2025, 4, 30, 0, 0));

        Book savedBook1 = bookRepository.save(book1);
        assertThat(savedBook1).isNotNull();
        Book savedBook2 = bookRepository.save(book2);
        assertThat(savedBook2).isNotNull();
    }

    @Test
    void testFindByIsbn(){
        Book book =
                bookRepository.findByIsbn("9788956746425")
                        .orElseThrow(() -> new RuntimeException("Book Not Found"));
    }

    @Test
    void testFindByAuthor(){
        Book book = bookRepository.findFirstByAuthor("박둘리")
                .orElseThrow(() -> new RuntimeException("Book Not Found"));

        assertThat(book.getTitle()).isEqualTo("JPA 프로그래밍");
        assertThat(book.getAuthor()).isEqualTo("박둘리");
    }

    @Test
    void testUpdateBook() {
        Book book = bookRepository.findByIsbn("9788956746425")
                .orElseThrow(() -> new RuntimeException("Book Not Found"));

        book.setPrice(32000);
        book.setTitle("스프링 부트 입문");
        Book updatedBook = bookRepository.save(book);

        assertThat(updatedBook.getPrice()).isEqualTo(32000);
        assertThat(updatedBook.getTitle()).isEqualTo("스프링 부트 입문");
    }

    @Test
    void testDeleteBook() {
        Book book = bookRepository.findByIsbn("9788956746425")
                .orElseThrow(() -> new RuntimeException("Book Not Found"));

        bookRepository.delete(book);

        Optional<Book> deletedBook = bookRepository.findByIsbn("9788956746425");
        assertThat(deletedBook).isEmpty();
    }
}