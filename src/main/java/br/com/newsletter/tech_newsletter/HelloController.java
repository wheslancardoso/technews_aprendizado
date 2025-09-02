package br.com.newsletter.tech_newsletter;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional; // <- Novo import
import java.util.List;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Olá, Newsletter!";
    }

}