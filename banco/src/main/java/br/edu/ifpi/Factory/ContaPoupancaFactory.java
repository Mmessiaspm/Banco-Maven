package br.edu.ifpi.Factory;

import br.edu.ifpi.Model.ContaBancaria;
import br.edu.ifpi.Model.ContaPoupanca;
import br.edu.ifpi.Model.Pessoa;

public class ContaPoupancaFactory extends ContaFactory {
    @Override
    public ContaBancaria criarConta(Pessoa titular) {
        ContaPoupanca c = new ContaPoupanca();
        c.setTitular(titular);
        // Valores padrão para conta poupança
        c.setSaldo(0.0);
        c.setRendimento(0.01);
        return c;
    }
}
