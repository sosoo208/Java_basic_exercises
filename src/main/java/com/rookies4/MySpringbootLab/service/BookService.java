package com.rookies4.MySpringbootLab.service;

import com.rookies4.MySpringbootLab.controller.dto.BookDTO;
import com.rookies4.MySpringbootLab.entity.Book;
import com.rookies4.MySpringbootLab.exception.BusinessException;
import com.rookies4.MySpringbootLab.repository.BookRepository;
import com.rookies4.MySpringbootLab.entity.BookDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    // 모든 도서 조회
    public List<BookDTO.Response> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookDTO.Response::fromEntity)
                .toList();
    }

    // ID로 도서 조회
    public BookDTO.Response getBookById(Long id) {
        Book book = findBookById(id);
        return BookDTO.Response.fromEntity(book);
    }

    // ISBN으로 도서 조회
    public BookDTO.Response getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException(
                        "Book Not Found with ISBN: " + isbn, HttpStatus.NOT_FOUND));
        return BookDTO.Response.fromEntity(book);
    }

    // 저자명으로 도서 조회
    public List<BookDTO.Response> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author).stream()
                .map(BookDTO.Response::fromEntity)
                .toList();
    }

    // 도서 등록
    @Transactional
    public BookDTO.Response createBook(BookDTO.Request request) {
        // ISBN 중복 검사
        bookRepository.findByIsbn(request.getIsbn())
                .ifPresent(book -> {
                    throw new BusinessException(
                            "Book with this ISBN already exists", HttpStatus.CONFLICT);
                });

        // DTO -> Entity 변환
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());

        // 필수값 체크
        if (request.getPrice() == null) {
            throw new BusinessException("Price is required", HttpStatus.BAD_REQUEST);
        }
        book.setPrice(request.getPrice());

        book.setPublishDate(request.getPublishDate());

        if (request.getDetailRequest() != null) {
            BookDetail detail = new BookDetail();
            detail.setDescription(request.getDetailRequest().getDescription());
            detail.setLanguage(request.getDetailRequest().getLanguage());
            detail.setPageCount(request.getDetailRequest().getPageCount());
            detail.setPublisher(request.getDetailRequest().getPublisher());
            detail.setCoverImageUrl(request.getDetailRequest().getCoverImageUrl());
            detail.setEdition(request.getDetailRequest().getEdition());
            detail.setBook(book);  // 연관관계 설정
            book.setBookDetail(detail);
        }

        Book savedBook = bookRepository.save(book);
        return BookDTO.Response.fromEntity(savedBook);
    }

    // 도서 정보 수정
    @Transactional
    public BookDTO.Response updateBook(Long id, BookDTO.Request request) {
        Book existingBook = findBookById(id);

        if (request.getTitle() != null) existingBook.setTitle(request.getTitle());
        if (request.getAuthor() != null) existingBook.setAuthor(request.getAuthor());
        if (request.getPrice() != null) existingBook.setPrice(request.getPrice());
        if (request.getPublishDate() != null) existingBook.setPublishDate(request.getPublishDate());

        Book updatedBook = bookRepository.save(existingBook);
        return BookDTO.Response.fromEntity(updatedBook);
    }

    // 도서 삭제
    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BusinessException("Book Not Found with ID: " + id, HttpStatus.NOT_FOUND);
        }
        bookRepository.deleteById(id);
    }

    // 내부 헬퍼 메서드
    private Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        "Book Not Found with ID: " + id, HttpStatus.NOT_FOUND));
    }
}
