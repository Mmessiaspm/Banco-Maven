package br.edu.ifpi.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica extends Pessoa {
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    // Construtor padrão
    public PessoaFisica() {
        super();
    }

    // Construtor com parâmetros
    public PessoaFisica(String nome, String cpf) {
        super();
        this.setNome(nome);
        this.cpf = cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }
}
