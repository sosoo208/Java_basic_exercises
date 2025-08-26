package com.rookies4.MySpringbootLab.repository;

import com.rookies4.MySpringbootLab.entity.BookDetail;
import com.rookies4.MySpringbootLab.repository.BookDetailRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface BookDetailRepository extends JpaRepository<BookDetail, Long> {

    @Query("SELECT d FROM BookDetail d JOIN FETCH d.book WHERE d.book.id = :bookId")
    Optional<BookDetail> findByBookId(@Param("bookId") Long bookId);

    @Query("SELECT d FROM BookDetail d JOIN FETCH d.book WHERE d.id = :id")
    Optional<BookDetail> findByIdWithBook(@Param("id") Long id);

    List<BookDetail> findByPublisherContainingIgnoreCase(String publisher);
}
