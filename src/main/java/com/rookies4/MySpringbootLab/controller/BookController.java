package com.rookies4.MySpringbootLab.controller;

import com.rookies4.MySpringbootLab.entity.Book;
import com.rookies4.MySpringbootLab.exception.BusinessException;
import com.rookies4.MySpringbootLab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // CORS 허용
public class BookController {

    private final BookRepository bookRepository;

    // 모든 도서 조회
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // ID로 도서 조회
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(book);
    }

    // ISBN으로 도서 조회
    @GetMapping("/isbn/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
    }

    // 저자명으로 도서 조회
    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return bookRepository.findByAuthor(author);
    }

    // 도서 등록
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    // 도서 전체 수정 (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookPut(@PathVariable Long id, @RequestBody Book bookDetail) {
        Book existBook = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));

        // 전체 필드 업데이트
        existBook.setTitle(bookDetail.getTitle());
        existBook.setAuthor(bookDetail.getAuthor());
        existBook.setIsbn(bookDetail.getIsbn());
        existBook.setPrice(bookDetail.getPrice());
        existBook.setPublishDate(bookDetail.getPublishDate());

        // BookDetail 연동
        if (bookDetail.getBookDetail() != null) {
            existBook.setBookDetail(bookDetail.getBookDetail());
            existBook.getBookDetail().setBook(existBook); // 양방향 연결
        }

        Book updatedBook = bookRepository.save(existBook);
        return ResponseEntity.ok(updatedBook);
    }

    // 도서 부분 수정 (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBookPatch(@PathVariable Long id, @RequestBody Book bookDetail) {
        Book existBook = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));

        // 필요한 필드만 부분 수정
        if (bookDetail.getPrice() != null) {
            existBook.setPrice(bookDetail.getPrice());
        }
        if (bookDetail.getTitle() != null) {
            existBook.setTitle(bookDetail.getTitle());
        }
        if (bookDetail.getAuthor() != null) {
            existBook.setAuthor(bookDetail.getAuthor());
        }
        if (bookDetail.getIsbn() != null) {
            existBook.setIsbn(bookDetail.getIsbn());
        }
        if (bookDetail.getPublishDate() != null) {
            existBook.setPublishDate(bookDetail.getPublishDate());
        }

        Book updatedBook = bookRepository.save(existBook);
        return ResponseEntity.ok(updatedBook);
    }

    // 도서 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
