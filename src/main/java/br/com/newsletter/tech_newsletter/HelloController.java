package br.com.newsletter.tech_newsletter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable; // <- Novo import
import java.util.Optional; // <- Novo import
import java.util.List;

@RestController
public class HelloController {

    // 1. Declaramos a necessidade da nossa dependência
    private final SubscriberRepository subscriberRepository;

    // 2. Criamos o construtor que recebe a dependência
    public HelloController(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Olá, Newsletter!";
    }

    // Um novo método para salvar assinantes virá aqui
    @PostMapping("/subscribe")
    public Subscriber subscribe(@RequestBody Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    @GetMapping("/subscribers")
    public List<Subscriber> getAllSubscribers() {
        return subscriberRepository.findAll();
    }

    @GetMapping("/subscribers/{id}")
    public Optional<Subscriber> getSubscriberById(@PathVariable Long id) {
        return subscriberRepository.findById(id);
    }
}