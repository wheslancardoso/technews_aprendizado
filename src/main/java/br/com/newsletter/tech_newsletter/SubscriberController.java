package br.com.newsletter.tech_newsletter;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subscribers") // <- Nova anotação importante!
public class SubscriberController {

    private final SubscriberRepository subscriberRepository;

    public SubscriberController(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @PostMapping
    public Subscriber subscribe(@Valid @RequestBody Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    @GetMapping
    public List<Subscriber> getAllSubscribers() {
        return subscriberRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Subscriber> getSubscriberById(@PathVariable Long id) {
        return subscriberRepository.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscriber> updateSubscriber(@PathVariable Long id, @Valid @RequestBody Subscriber updatedSubscriber) {
        return subscriberRepository.findById(id)
                .map(existingSubscriber -> {
                    existingSubscriber.setEmail(updatedSubscriber.getEmail());
                    return ResponseEntity.ok(subscriberRepository.save(existingSubscriber));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscriber(@PathVariable Long id) {
        subscriberRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}