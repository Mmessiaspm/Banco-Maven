package br.edu.ifpi.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;   
    
     @OneToMany(mappedBy = "titular", cascade = CascadeType.ALL, orphanRemoval = true)
    // mappedBy = "titular": indica o campo 'titular' na entidade ContaBancaria que é o proprietário do relacionamento
    // cascade = CascadeType.ALL: operações de persistência (salvar, remover) na Pessoa propagarão para ContaBancaria
    // orphanRemoval = true: se uma ContaBancaria for removida da lista de 'contasBancaria' de uma Pessoa, ela será deletada do banco de dados
    private List<ContaBancaria> contasBancaria = new ArrayList<>(); // Use List para coleções em JPA e inicialize para evitar NullPointerException
    private String senha;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public List<ContaBancaria> getContasBancaria() {
        return contasBancaria;
    }
    public void setContasBancaria(List<ContaBancaria> contasBancaria) {
        this.contasBancaria = contasBancaria;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
