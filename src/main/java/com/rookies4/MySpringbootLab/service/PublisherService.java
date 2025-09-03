package com.rookies4.MySpringbootLab.service;

import com.rookies4.MySpringbootLab.controller.dto.PublisherDTO;
import com.rookies4.MySpringbootLab.entity.Publisher;
import com.rookies4.MySpringbootLab.exception.ErrorCode;
import com.rookies4.MySpringbootLab.repository.PublisherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public List<PublisherDTO.SimpleResponse> getAllPublishers() {
        return publisherRepository.findAll().stream()
                .map(PublisherDTO.SimpleResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public PublisherDTO.Response getPublisherById(Long id) {
        Publisher publisher = publisherRepository.findByIdWithBooks(id)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.PUBLISHER_NOT_FOUND.formatMessage(id)));
        return PublisherDTO.Response.fromEntity(publisher);
    }

    public PublisherDTO.Response getPublisherByName(String name) {
        Publisher publisher = publisherRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.PUBLISHER_NOT_FOUND.formatMessage(name)));
        return PublisherDTO.Response.fromEntity(publisher);
    }

    public PublisherDTO.Response createPublisher(PublisherDTO.Request request) {
        if (publisherRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATE_PUBLISHER_NAME.formatMessage(request.getName()));
        }

        Publisher publisher = Publisher.builder()
                .name(request.getName())
                .establishedDate(request.getEstablishedDate())
                .address(request.getAddress())
                .build();

        Publisher savedPublisher = publisherRepository.save(publisher);
        return PublisherDTO.Response.fromEntity(savedPublisher);
    }

    public PublisherDTO.Response updatePublisher(Long id, PublisherDTO.Request request) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.PUBLISHER_NOT_FOUND.formatMessage(id)));

        Optional<Publisher> existingPublisher = publisherRepository.findByName(request.getName());
        if (existingPublisher.isPresent() && !existingPublisher.get().getId().equals(id)) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATE_PUBLISHER_NAME.formatMessage(request.getName()));
        }

        publisher.setName(request.getName());
        publisher.setEstablishedDate(request.getEstablishedDate());
        publisher.setAddress(request.getAddress());

        return PublisherDTO.Response.fromEntity(publisher);
    }

    public void deletePublisher(Long id) {
        Publisher publisher = publisherRepository.findByIdWithBooks(id)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.PUBLISHER_NOT_FOUND.formatMessage(id)));

        if (publisher.getBooks() != null && !publisher.getBooks().isEmpty()) {
            throw new IllegalStateException(ErrorCode.PUBLISHER_DELETE_FAIL_HAS_BOOKS
                    .formatMessage(id, publisher.getBooks().size()));
        }

        publisherRepository.delete(publisher);
    }
}
