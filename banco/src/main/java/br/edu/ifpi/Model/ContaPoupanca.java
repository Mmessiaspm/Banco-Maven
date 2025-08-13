
package br.edu.ifpi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "conta_poupanca")
public class ContaPoupanca extends ContaBancaria {
    private double rendimento;
 
    public ContaPoupanca() {
    }
 
    public double getRendimento() {
       return this.rendimento;
    }
 
    public void setRendimento(double var1) {
       this.rendimento = var1;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= getSaldo()) {
            setSaldo(getSaldo() - valor);
            return true;
        } else {
            System.out.println("Saldo insuficiente");
            return false;
        }
    }
 }
