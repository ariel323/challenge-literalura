package com.literatura.literalura.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@Service
public class GutendexService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Map getBooks(String search) {
        String url = "https://gutendex.com/books/?search=" + search;
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        return response.getBody();
    }

    public Map<String, Object> getBooksByAuthor(String author) {
        return getBooks(author); // Si tu método getBooks ya busca por cualquier texto
    }
}
