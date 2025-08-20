package br.com.newsletter.tech_newsletter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity // 1. Transforma a classe em uma tabela no banco de dados
public class Subscriber {

    @Id // 2. Marca o campo 'id' como a chave primária (identificador único)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 3. Pede para o banco de dados gerar o valor do id automaticamente
    private Long id;


    @NotBlank // <- Lugar correto
    @Email    // <- Lugar correto
    private String email;

    // Getters e Setters (métodos de acesso) virão aqui

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}