package com.example.pokemondreamteam.interfaces.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface BaseController<T> {

    long ACCEPT_RANGE = 50;

    default ResponseEntity<Void> created(String id) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("id", id.toString())
                .build();
    }

    default ResponseEntity<T> created(T body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    default ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok(body);
    }

    default ResponseEntity<List<T>> ok(List<T> body) {
        return ResponseEntity.ok(body);
    }

    default ResponseEntity<List<T>> partialContent(Page<T> page, long acceptRange) {
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .header("content-range", String.valueOf(page.getTotalElements()))
                .header("content-pages", String.valueOf(page.getTotalPages()))
                .header("accept-range", String.valueOf(acceptRange))
                .body(page.getContent());
    }

    default ResponseEntity<Void> noContent() {
        return ResponseEntity.noContent().build();
    }
}
