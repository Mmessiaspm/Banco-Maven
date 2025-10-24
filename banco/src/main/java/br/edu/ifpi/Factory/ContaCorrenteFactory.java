package br.edu.ifpi.Factory;

import br.edu.ifpi.Model.ContaBancaria;
import br.edu.ifpi.Model.ContaCorrente;
import br.edu.ifpi.Model.Pessoa;

public class ContaCorrenteFactory extends ContaFactory {
    @Override
    public ContaBancaria criarConta(Pessoa titular) {
        ContaCorrente c = new ContaCorrente();
        c.setTitular(titular);
        // Valores padrão para conta corrente
        c.setSaldo(0.0);
        c.setLimite(100.0);
        return c;
    }
}
