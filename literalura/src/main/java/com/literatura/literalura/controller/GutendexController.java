package com.literatura.literalura.controller;

import com.literatura.literalura.service.GutendexService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/gutendex")
public class GutendexController {

    private final GutendexService gutendexService;

    public GutendexController(GutendexService gutendexService) {
        this.gutendexService = gutendexService;
    }

    @GetMapping("/books")
    public Map getBooks(@RequestParam String search) {
        return gutendexService.getBooks(search);
    }
}
