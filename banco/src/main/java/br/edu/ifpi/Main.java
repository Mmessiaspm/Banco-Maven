package br.edu.ifpi;
import br.edu.ifpi.Model.PessoaJuridica;

import br.edu.ifpi.Model.PessoaFisica;
import br.edu.ifpi.Model.Funcionario;
import br.edu.ifpi.DAO.ContaCorrenteDAO;
import br.edu.ifpi.DAO.PessoaFisicaDAO;
import br.edu.ifpi.DAO.PessoaJuridicaDAO;
import br.edu.ifpi.DAO.FuncionarioDAO;
import br.edu.ifpi.DAO.ContaPoupancaDAO;
import br.edu.ifpi.Model.ContaPoupanca;
import br.edu.ifpi.Model.ContaCorrente;
import java.util.Scanner;





public class Main{
    public static PessoaFisicaDAO daoPessoasFisica = new PessoaFisicaDAO();
    public static PessoaJuridicaDAO daoPessoasJuridica = new PessoaJuridicaDAO();

    public static FuncionarioDAO daoFuncionario = new FuncionarioDAO();
    public static ContaCorrenteDAO daoContaCorrente = new ContaCorrenteDAO();
    public static ContaPoupancaDAO daoContaPoupanca = new ContaPoupancaDAO();

    public static void MenuPrincipal(){
         
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            // Exibe o menu de opções
            System.out.println("--------------------");
            System.out.println("      MENU DE OPÇÕES");
            System.out.println("--------------------");
            System.out.println("1. Administrativo");
            System.out.println("2. Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            // Lê a opção do usuário
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha
            } else {
                System.out.println("\nEntrada inválida. Por favor, digite um número.\n");
                scanner.nextLine(); // Limpa o buffer do scanner
                continue; // Volta para o início do loop
            }

            // Trata a opção escolhida
            switch (opcao) {
                case 1:
                    System.out.println("\nVocê escolheu a Opção 1.\n");
                    MenuAdmnistrativo();
                    break;
                case 2:
                    System.out.println("\nVocê escolheu a Opção 2.\n");
                    System.out.println("Digite 1 para Pessoa Física ou 2 para Pessoa Jurídica:");
                    int tipoPessoa = scanner.nextInt();
                    scanner.nextLine(); // Consome a quebra de linha
                    if (tipoPessoa == 1) {
                        System.out.print("Digite o CPF da pessoa: ");
                        String cpf = scanner.nextLine();
                        PessoaFisica pessoaFisica = daoPessoasFisica.buscarPorCpf(cpf);
                        if (pessoaFisica != null) {
                            System.out.println("Cliente encontrado: " + pessoaFisica.getNome() + ", CPF: " + pessoaFisica.getCpf());
                            System.out.println("Digite a senha do cliente: ");
                            String senha = scanner.nextLine();
                            if (pessoaFisica.getSenha().equals(senha)) {
                                MenuCliente(pessoaFisica);
                            } else {
                                System.out.println("Senha inválida!");
                            }
                        } else {
                            System.out.println("Cliente não encontrado com o CPF: " + cpf);
                        }
                    } else if (tipoPessoa == 2) {
                        System.out.print("Digite o CNPJ da empresa: ");
                        String cnpj = scanner.nextLine();
                        PessoaJuridica pessoaJuridica = daoPessoasJuridica.buscarPorCnpj(cnpj);
                        if (pessoaJuridica != null) {
                            System.out.println("Empresa encontrada: " + pessoaJuridica.getNome() + ", CNPJ: " + pessoaJuridica.getCnpj());
                            System.out.println("Digite a senha do cliente: ");
                            String senha = scanner.nextLine();
                            if (pessoaJuridica.getSenha().equals(senha)) {
                                MenuCliente(pessoaJuridica);
                            } else {
                                System.out.println("Senha inválida!");
                            }
                        } else {
                            System.out.println("Empresa não encontrada com o CNPJ: " + cnpj);
                        }
                    } else {
                        System.out.println("\nTipo de pessoa inválido. Por favor, tente novamente.\n");
                    }
                    case 0:
                    System.out.println("\nSaindo do programa. Até mais!\n");
                    break;
                default:
                    System.out.println("\nOpção inválida. Por favor, tente novamente.\n");
            }
        }
        scanner.close();
    }


public static void MenuAdmnistrativo(){
         
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            // Exibe o menu de opções
            System.out.println("--------------------");
            System.out.println("      MENU DE OPÇÕES");
            System.out.println("--------------------");
            System.out.println("1. Inserir novo Funcionário");
            System.out.println("2. Gerente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            // Lê a opção do usuário
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha
            } else {
                System.out.println("\nEntrada inválida. Por favor, digite um número.\n");
                scanner.nextLine(); // Limpa o buffer do scanner
                continue; // Volta para o início do loop
            }

            // Trata a opção escolhida
            switch (opcao) {
                case 1:
                    System.out.println("\nInserir novo funcionário.\n");
                    Funcionario funcionario = new Funcionario();
                    System.out.print("Digite o nome do funcionário: ");
                    funcionario.setNome(scanner.nextLine());
                    System.out.print("Digite o CPF do funcionário: ");
                    funcionario.setCpf(scanner.nextLine());
                    System.out.print("Digite a matrícula do funcionário: ");
                    funcionario.setMatricula(scanner.nextLine());
                    System.out.print("Digite a senha do funcionário: ");
                    funcionario.setSenha(scanner.nextLine());
                    
                    System.out.print("Digite o cargo do funcionário: ");
                    funcionario.setCargo(scanner.nextLine());
                     System.out.print("Digite o salário do funcionário: ");
                    funcionario.setSalario(scanner.nextDouble());
                    scanner.nextLine(); // Consome a quebra de linha após o nextDouble()
                    // Salvando o objeto funcionário no banco de dados
                    daoFuncionario.salvar(funcionario);
                    System.out.println("Funcionário salvo com sucesso!");

                    break;
                case 2:
                    System.out.println("\nPesquisar Funcionário por Matrícula.\n");
                    System.out.print("Digite a Matrícula do Funcionário: ");
                    String matricula = scanner.nextLine();
                    System.out.println("\nDigite a senha do Funcionário: ");
                    String senha = scanner.nextLine();
                    Funcionario funcionarioEncontrado = daoFuncionario.Login(matricula,senha);
                   
                    // Verifica se o funcionário foi encontrado e se a senha está correta
                    if (funcionarioEncontrado != null) {
                        System.out.println("Login feito com sucesso: " + funcionarioEncontrado.getNome());
                    } else {
                        System.out.println("Funcionário não encontrado ou senha inválida.");
                        continue; // Volta para o início do loop
                
                    }
                    MenuGerente();
                    break;
                case 0:
                    MenuPrincipal();;
                    break;
                default:
                    System.out.println("\nOpção inválida. Por favor, tente novamente.\n");
            }
        }
        scanner.close();
    }


    



public static void MenuGerente(){
         
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            // Exibe o menu de opções
            System.out.println("--------------------");
            System.out.println("      MENU DE OPÇÕES");
            System.out.println("--------------------");
            System.out.println("1. Inserir Cliente");
            System.out.println("2. Pesquisar Cliente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            // Lê a opção do usuário
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha
            } else {
                System.out.println("\nEntrada inválida. Por favor, digite um número.\n");
                scanner.nextLine(); // Limpa o buffer do scanner
                continue; // Volta para o início do loop
            }

            // Trata a opção escolhida
            switch (opcao) {
                case 1:
                    System.out.println("\nInserir nov0 Cliente.\n");
                    System.out.println("Digite 1 para Pessoa Física ou 2 para Pessoa Jurídica:");
                    int tipoPessoa = scanner.nextInt();
                    scanner.nextLine(); // Consome a quebra de linha
                    if (tipoPessoa == 1) {
                        PessoaFisica pessoaFisica = new PessoaFisica();
                        System.out.print("Digite o nome da pessoa: ");
                        pessoaFisica.setNome(scanner.nextLine());
                        System.out.print("Digite o CPF da pessoa: ");
                        pessoaFisica.setCpf(scanner.nextLine());
                        System.out.println("Digite a senha do cliente: ");
                        pessoaFisica.setSenha(scanner.nextLine());
                        // Salvando o objeto pessoa no banco de dados
                        daoPessoasFisica.salvar(pessoaFisica);
                    } else if (tipoPessoa == 2) {
                        PessoaJuridica pessoaJuridica = new PessoaJuridica();
                        System.out.print("Digite o nome da empresa: ");
                        pessoaJuridica.setNome(scanner.nextLine());
                        System.out.print("Digite o CNPJ da empresa: ");
                        pessoaJuridica.setCnpj(scanner.nextLine());
                        System.out.println("Digite a senha do cliente: ");
                        pessoaJuridica.setSenha(scanner.nextLine());
                        // Salvando o objeto pessoa no banco de dados
                        daoPessoasJuridica.salvar(pessoaJuridica);
                    } else {
                        System.out.println("\nTipo de pessoa inválido. Por favor, tente novamente.\n");
                    }
                        System.out.println("Cliente salvo com sucesso!");
                        
                    break;
                   case 2:
                    System.out.println("\nPesquisar cliente.\n");
                    System.out.println("Digite 1 para Pessoa Física ou 2 para Pessoa Jurídica:");
                    tipoPessoa = scanner.nextInt();
                    scanner.nextLine(); // Consome a quebra de linha
                    if (tipoPessoa == 1) {      
                    System.out.print("Digite o CPF da pessoa: ");
                    String cpf = scanner.nextLine();
                    PessoaFisica pessoaFisicaEncontrada = daoPessoasFisica.buscarPorCpf(cpf);   
                    if (pessoaFisicaEncontrada != null) {
                        System.out.println("Cliente encontrado: " + pessoaFisicaEncontrada.getNome() + ", CPF: " + pessoaFisicaEncontrada.getCpf());
                        MenuGerentePessoaFisica(pessoaFisicaEncontrada);

                    } else {
                        System.out.println("Cliente não encontrada com o CPF: " + cpf);
                    }
                } else if (tipoPessoa == 2) {
                    System.out.print("Digite o CNPJ da empresa: ");
                    String cnpj = scanner.nextLine();
                    PessoaJuridica pessoaJuridicaEncontrada = daoPessoasJuridica.buscarPorCnpj(cnpj);
                    if (pessoaJuridicaEncontrada != null) {
                        System.out.println("Empresa encontrada: " + pessoaJuridicaEncontrada.getNome() + ", CNPJ: " + pessoaJuridicaEncontrada.getCnpj());
                        MenuGerentePessoaJuridica(pessoaJuridicaEncontrada);
                    } else {
                        System.out.println("Empresa não encontrada com o CNPJ: " + cnpj);
                    }
                } else {
                    System.out.println("\nTipo de pessoa inválido. Por favor, tente novamente.\n");
                }
                    break;
                case 0:
                    MenuPrincipal();;
                    break;
                default:
                    System.out.println("\nOpção inválida. Por favor, tente novamente.\n");
            }
        }
        scanner.close();
    }

public static void MenuGerentePessoaFisica(PessoaFisica pessoa){
         
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            // Exibe o menu de opções
            System.out.println("--------------------");
            System.out.println("      MENU DE OPÇÕES");
            System.out.println("--------------------");
            System.out.println("1. Cadastrar Conta Corrente ou Conta Poupança");
            System.out.println("0. Voltar ao Menu Gerente");
            System.out.print("Escolha uma opção: ");

            // Lê a opção do usuário
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha
            } else {
                System.out.println("\nEntrada inválida. Por favor, digite um número.\n");
                scanner.nextLine(); // Limpa o buffer do scanner
                continue; // Volta para o início do loop
            }

            // Trata a opção escolhida
            switch (opcao) {
                case 1:
                    System.out.println("\nInserir nova Conta.\n");
                    System.out.println("Digite 1 para Conta Corrente  ou 2 para Conta Poupança:");
                    int tipoConta = scanner.nextInt();
                    scanner.nextLine(); // Consome a quebra de linha
                    if (tipoConta == 1) {

                        ContaCorrente contaCorrente = new ContaCorrente();
                        // Atribuindo a pessoa como titular da conta
                        contaCorrente.setTitular(pessoa);
                       
                        System.out.print("Digite o número da conta: ");
                        contaCorrente.setNumero(scanner.nextLine());
                        
                        System.out.print("Digite o saldo inicial: ");
                        contaCorrente.setSaldo(scanner.nextDouble());
                        scanner.nextLine(); // Consome a quebra de linha após o nextDouble()
                        System.out.print("Digite o limite de crédito: ");
                        contaCorrente.setLimite(scanner.nextDouble());
                        scanner.nextLine(); // Consome a quebra de linha após o nextDouble()
                        // Salvando o objeto contaCorrente no banco de dados
                        daoContaCorrente.salvar(contaCorrente);
                    } else if (tipoConta == 2) {
                        ContaPoupanca contaPoupanca = new ContaPoupanca();

                        // Atribuindo a pessoa como titular da conta
                        contaPoupanca.setTitular(pessoa);

                        System.out.print("Digite o número da conta: ");
                        contaPoupanca.setNumero(scanner.nextLine());
                        System.out.print("Digite o saldo inicial: ");           
                        contaPoupanca.setSaldo(scanner.nextDouble());
                        scanner.nextLine(); // Consome a quebra de linha após o nextDouble()
                        System.out.print("Digite a taxa de rendimento: ");
                        contaPoupanca.setRendimento(scanner.nextDouble());
                        scanner.nextLine(); // Consome a quebra de linha após o nextDouble()
                        // Salvando o objeto contaPoupanca no banco de dados
                        daoContaPoupanca.salvar(contaPoupanca);
                    } else {
                        System.out.println("\nTipo de conta inválido. Por favor, tente novamente.\n");
                  }
                        System.out.println("Conta salva com sucesso!"); 

                    break;
                case 0:
                    MenuGerente();;
                    break;
                default:
                    System.out.println("\nOpção inválida. Por favor, tente novamente.\n");
            }
        }
        scanner.close();
    }



public static void MenuGerentePessoaJuridica(PessoaJuridica pessoa){
         
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            // Exibe o menu de opções
            System.out.println("--------------------");
            System.out.println("      MENU DE OPÇÕES");
            System.out.println("--------------------");
            System.out.println("1. Cadastrar Conta Corrente:");
            System.out.println("0. Voltar ao Menu Gerente");
            System.out.print("Escolha uma opção: ");

            // Lê a opção do usuário
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha
            } else {
                System.out.println("\nEntrada inválida. Por favor, digite um número.\n");
                scanner.nextLine(); // Limpa o buffer do scanner
                continue; // Volta para o início do loop
            }

            // Trata a opção escolhida
            switch (opcao) {
                case 1:
                    System.out.println("\nInserir nova Conta.\n");
                
                    scanner.nextLine(); // Consome a quebra de linha
                    
                        ContaCorrente contaCorrente = new ContaCorrente();
                        // Atribuindo a pessoa como titular da conta
                        contaCorrente.setTitular(pessoa);
                       
                        System.out.print("Digite o número da conta: ");
                        contaCorrente.setNumero(scanner.nextLine());
                        
                        System.out.print("Digite o saldo inicial: ");
                        contaCorrente.setSaldo(scanner.nextDouble());
                        scanner.nextLine(); // Consome a quebra de linha após o nextDouble()
                        System.out.print("Digite o limite de crédito: ");
                        contaCorrente.setLimite(scanner.nextDouble());
                        scanner.nextLine(); // Consome a quebra de linha após o nextDouble()
                        // Salvando o objeto contaCorrente no banco de dados
                        daoContaCorrente.salvar(contaCorrente);
                    
                        System.out.println("Conta salva com sucesso!"); 

                    break;
                case 0:
                    MenuGerente();;
                    break;
                default:
                    System.out.println("\nOpção inválida. Por favor, tente novamente.\n");
            }
        }
        scanner.close();
    }

public static void MenuCliente(PessoaFisica pessoa){
         
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            // Exibe o menu de opções
            System.out.println("--------------------");
            System.out.println("      MENU DE OPÇÕES");
            System.out.println("--------------------");
            System.out.println("1. Conta Corrente");
            System.out.println("2. Conta Poupança");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            // Lê a opção do usuário
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha
            } else {
                System.out.println("\nEntrada inválida. Por favor, digite um número.\n");
                scanner.nextLine(); // Limpa o buffer do scanner
                continue; // Volta para o início do loop
            }

            // Trata a opção escolhida
            switch (opcao) {
                case 1:
                    System.out.println("\nOperaçoes Conta Corrente.\n");
                    ContaCorrente contaCorrente = daoContaCorrente.buscarPorTitular(pessoa);
                    if (contaCorrente == null) {
                        System.out.println("Nenhuma conta corrente encontrada para o titular: " + pessoa.getNome());
                    } else {
                    System.out.println("Conta Corrente encontrada: " + contaCorrente.getNumero() + ", Saldo: " + contaCorrente.getSaldo());
                    System.out.println("Digite 1 para Sacar, 2 para Depositar");
                    int operacao = scanner.nextInt();
                    scanner.nextLine(); // Consome a quebra de linha
                    if (operacao == 1) {
                        System.out.print("Digite o valor a ser sacado: ");
                        double valorSaque = scanner.nextDouble();
                        scanner.nextLine(); // Consome a quebra de linha
                        if (contaCorrente.sacar(valorSaque)) {
                            System.out.println("Saque realizado com sucesso! Novo saldo: " + contaCorrente.getSaldo());
                            daoContaCorrente.update(contaCorrente);
                        } else {
                            System.out.println("Saldo insuficiente para o saque.");
                        }
                    } else if (operacao == 2) {
                        System.out.print("Digite o valor a ser depositado: ");
                        double valorDeposito = scanner.nextDouble();
                        scanner.nextLine(); // Consome a quebra de linha
                        contaCorrente.depositar(valorDeposito);
                        System.out.println("Depósito realizado com sucesso! Novo saldo: " + contaCorrente.getSaldo());
                        daoContaCorrente.update(contaCorrente);
                    } else {
                        System.out.println("Operação inválida.");
                    }
                }
                   
                    break;

                case 2:
                    System.out.println("\nOperaçoes Conta Poupanca.\n");
                    ContaPoupanca contaPoupanca = daoContaPoupanca.buscarPorTitular(pessoa);
                    if (contaPoupanca == null) {
                        System.out.println("Nenhuma conta poupança encontrada para o titular: " + pessoa.getNome());
                    } else {
                        System.out.println("Conta Poupança encontrada: " + contaPoupanca.getNumero() + ", Saldo: " + contaPoupanca.getSaldo());
                        System.out.println("Digite 1 para Sacar, 2 para Depositar");
                        int operacao = scanner.nextInt();
                        scanner.nextLine(); // Consome a quebra de linha
                        if (operacao == 1) {
                            System.out.print("Digite o valor a ser sacado: ");
                            double valorSaque = scanner.nextDouble();
                            scanner.nextLine(); // Consome a quebra de linha
                            if (contaPoupanca.sacar(valorSaque)) {
                                System.out.println("Saque realizado com sucesso! Novo saldo: " + contaPoupanca.getSaldo());
                                daoContaPoupanca.update(contaPoupanca);
                            } else {
                                System.out.println("Saldo insuficiente para o saque.");
                            }
                        } else if (operacao == 2) {
                            System.out.print("Digite o valor a ser depositado: ");
                            double valorDeposito = scanner.nextDouble();
                            scanner.nextLine(); // Consome a quebra de linha
                            contaPoupanca.depositar(valorDeposito);
                            System.out.println("Depósito realizado com sucesso! Novo saldo: " + contaPoupanca.getSaldo());
                            daoContaPoupanca.update(contaPoupanca);
                        } else {
                            System.out.println("Operação inválida.");
                        }
                    }
                    break;  
                case 0:
                    MenuGerente();;
                    break;
                default:
                    System.out.println("\nOpção inválida. Por favor, tente novamente.\n");
            }
        }
        scanner.close();
    }

// Overloaded method for PessoaJuridica
public static void MenuCliente(PessoaJuridica pessoa){
         
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            // Exibe o menu de opções
            System.out.println("--------------------");
            System.out.println("      MENU DE OPÇÕES");
            System.out.println("--------------------");
            System.out.println("1. Conta Corrente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            // Lê a opção do usuário
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha
            } else {
                System.out.println("\nEntrada inválida. Por favor, digite um número.\n");
                scanner.nextLine(); // Limpa o buffer do scanner
                continue; // Volta para o início do loop
            }

            // Trata a opção escolhida
            switch (opcao) {
                case 1:
                    System.out.println("\nOperaçoes Conta Corrente.\n");
                    ContaCorrente contaCorrente = daoContaCorrente.buscarPorTitular(pessoa);
                    if (contaCorrente == null) {
                        System.out.println("Nenhuma conta corrente encontrada para o titular: " + pessoa.getNome());
                    } else {
                        System.out.println("Conta Corrente encontrada: " + contaCorrente.getNumero() + ", Saldo: " + contaCorrente.getSaldo());
                        System.out.println("Digite 1 para Sacar, 2 para Depositar");
                        int operacao = scanner.nextInt();
                        scanner.nextLine(); // Consome a quebra de linha
                        if (operacao == 1) {
                            System.out.print("Digite o valor a ser sacado: ");
                            double valorSaque = scanner.nextDouble();
                            scanner.nextLine(); // Consome a quebra de linha
                            if (contaCorrente.sacar(valorSaque)) {
                                System.out.println("Saque realizado com sucesso! Novo saldo: " + contaCorrente.getSaldo());
                                daoContaCorrente.update(contaCorrente);
                            } else {
                                System.out.println("Saldo insuficiente para o saque.");
                            }
                        } else if (operacao == 2) {
                            System.out.print("Digite o valor a ser depositado: ");
                            double valorDeposito = scanner.nextDouble();
                            scanner.nextLine(); // Consome a quebra de linha
                            contaCorrente.depositar(valorDeposito);
                            System.out.println("Depósito realizado com sucesso! Novo saldo: " + contaCorrente.getSaldo());
                            daoContaCorrente.update(contaCorrente);
                        } else {
                            System.out.println("Operação inválida.");
                        }
                    }
                    break;
                case 0:
                    MenuGerente();;
                    break;
                default:
                    System.out.println("\nOpção inválida. Por favor, tente novamente.\n");
            }
        }
        scanner.close();
    }


    public static void main(String[] args) {

        System.out.println("Bem-vindo ao Sistema Bancário!");

        MenuPrincipal();        

       
}
}