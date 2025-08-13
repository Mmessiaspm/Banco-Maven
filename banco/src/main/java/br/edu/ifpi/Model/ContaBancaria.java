package br.edu.ifpi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "conta_bancaria")
public  class ContaBancaria {
    @Id
    private String numero;
    private double saldo;
    
    @ManyToOne
    @JoinColumn(name = "titular_id")
    private Pessoa titular;
    
  
    // Construtor padrão (necessário para JPA)
    public ContaBancaria() {
    }

   

    //metodos getters e setters
    public String getNumero() {
        return this.numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double valor){
        this.saldo = valor;

    }
    
    public Pessoa getTitular() {
        return this.titular;
    }
    public void setTitular(Pessoa titular) {
        this.titular = titular;
    }
    

    //metodo sacar
    public boolean sacar(double valor) {
        if (valor <= this.saldo) {
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    //metodo depositar
    public void depositar(double valor) {
        this.saldo += valor;
    }
    

}
