package com.rookies4.MySpringbootLab.controller;

import com.rookies4.MySpringbootLab.controller.dto.PublisherDTO;
import com.rookies4.MySpringbootLab.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<PublisherDTO.SimpleResponse>> getAllPublishers() {
        List<PublisherDTO.SimpleResponse> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO.Response> getPublisherById(@PathVariable Long id) {
        PublisherDTO.Response response = publisherService.getPublisherById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<PublisherDTO.Response> getPublisherByName(@RequestParam String name) {
        PublisherDTO.Response response = publisherService.getPublisherByName(name);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PublisherDTO.Response> createPublisher(@RequestBody PublisherDTO.Request request) {
        PublisherDTO.Response response = publisherService.createPublisher(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDTO.Response> updatePublisher(@PathVariable Long id,
                                                                 @RequestBody PublisherDTO.Request request) {
        PublisherDTO.Response response = publisherService.updatePublisher(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}
