package br.com.newsletter.tech_newsletter;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Subscriber subscribe(@Valid @RequestBody Subscriber subscriber) { // <- Adicione @Valid aqui
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

    @DeleteMapping("/subscribers/{id}")
    public ResponseEntity<Void> deleteSubscriber(@PathVariable Long id) {
        subscriberRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/subscribers/{id}")
    public ResponseEntity<Subscriber> updateSubscriber(@PathVariable Long id, @Valid @RequestBody Subscriber updatedSubscriber) {

        return subscriberRepository.findById(id)
                .map(existingSubscriber -> {
                    existingSubscriber.setEmail(updatedSubscriber.getEmail());
                    return ResponseEntity.ok(subscriberRepository.save(existingSubscriber));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}