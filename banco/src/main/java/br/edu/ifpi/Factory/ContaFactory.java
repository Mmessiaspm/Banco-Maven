package br.edu.ifpi.Factory;

import br.edu.ifpi.Model.ContaBancaria;
import br.edu.ifpi.Model.Pessoa;

public abstract class ContaFactory {
    public abstract ContaBancaria criarConta(Pessoa titular);
}
