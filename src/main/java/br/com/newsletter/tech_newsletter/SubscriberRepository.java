package br.com.newsletter.tech_newsletter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Anotação que marca esta interface como um componente de acesso a dados
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    // A mágica do Spring Data JPA acontece aqui. Não precisamos de mais nada!
}