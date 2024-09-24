import java.util.Scanner;

class Banco {
    // Atributos da classe Banco
    private double saque;         // Valor do saque
    private double deposito;      // Valor do depósito
    private double saldoAtual;    // Saldo atual da conta
    private int numConsulta;      // Número de consultas realizadas

    // Construtor da classe Banco que inicializa o saldo com um valor inicial
    public Banco(double saldoInicial) {
        this.saldoAtual = saldoInicial;
        this.numConsulta = 0; // Inicializa o número de consultas em zero
    }

    // Método para obter o valor do saque
    public double getSaque() {
        return saque;
    }

    // Método para definir o valor do saque
    public void setSaque(double valor) {
        double taxa = 0.005; // Define a taxa de 0,5%
        saque = valor; // Armazena o valor do saque
        taxa = saque * taxa; // Calcula a taxa sobre o saque

        // Informa ao usuário sobre a taxa cobrada
        System.out.print("Será cobrado uma taxa no valor de " + taxa);
        double totalSaque = saque + taxa; // Total a ser sacado, incluindo a taxa

        // Verifica se o saldo é suficiente para o saque
        if (totalSaque <= saldoAtual) {
            saldoAtual -= totalSaque; // Atualiza o saldo
            System.out.print("\nO valor total é de " + totalSaque);
        } else {
            System.out.println("\nSaldo insuficiente para o saque."); // Mensagem de erro
        }
    }

    // Método para obter o valor do depósito
    public double getDeposito() {
        return deposito;
    }

    // Método para definir o valor do depósito
    public void setDeposito(double valor) {
        double taxa = 0.01; // Define a taxa de 1%
        deposito = valor; // Armazena o valor do depósito
        taxa = deposito * taxa; // Calcula a taxa sobre o depósito

        // Informa ao usuário sobre a taxa cobrada
        System.out.print("Será cobrado uma taxa de " + taxa);
        double totalDeposito = deposito - taxa; // Total a ser depositado, após a taxa

        // Verifica se o valor do depósito é positivo após a taxa
        if (totalDeposito > 0) {
            saldoAtual += totalDeposito; // Atualiza o saldo
            System.out.print("\nO valor total é de " + totalDeposito);
        } else {
            System.out.println("\nO valor do depósito é insuficiente após a taxa."); // Mensagem de erro
        }
    }

    // Método para realizar uma consulta ao saldo
    public void setConsulta() {
        double taxa = 0.1; // Define a taxa de R$ 0,10
        numConsulta++; // Incrementa o número de consultas realizadas
        System.out.println("Seu saldo atual é de " + saldoAtual + "\nVocê realizou " + numConsulta +
                " consultas. A cada 5 consultas será cobrada uma taxa de R$ 0,10");

        // Verifica se o número de consultas ultrapassou o limite gratuito
        if (numConsulta % 5 == 0) {
            saldoAtual -= taxa; // Deduz a taxa do saldo
            System.out.printf("Número de consultas gratuitas excedido. Taxa de R$ 0,10 cobrada. Saldo atual:\n" + saldoAtual);
        }
    }

    // Método para obter o saldo atual
    public double getSaldoAtual() {
        return saldoAtual;
    }
}

public class Itau {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // Inicializa o scanner para entrada do usuário
        Banco banco = new Banco(1000); // Cria uma nova conta com saldo inicial de R$ 1000

        while (true) { // Loop contínuo para operações
            // Solicita ao usuário qual operação deseja realizar
            System.out.println("Qual operação você quer realizar:\n[1] saque\n[2] deposito\n[3] consulta de saldo");
            int opcao = scan.nextInt(); // Lê a opção do usuário

            // Executa a operação escolhida
            if (opcao == 1) {
                System.out.println("Qual o valor a ser sacado?");
                double valorSaque = scan.nextDouble(); // Lê o valor do saque
                banco.setSaque(valorSaque); // Chama o método de saque
            } else if (opcao == 2) {
                System.out.println("Qual valor você deseja depositar?");
                double valorDeposito = scan.nextDouble(); // Lê o valor do depósito
                banco.setDeposito(valorDeposito); // Chama o método de depósito
            } else if (opcao == 3) {
                banco.setConsulta(); // Chama o método de consulta
            } else {
                System.out.println("Opção inválida"); // Mensagem para opção inválida
            }

            // Pergunta se o usuário deseja realizar outra operação
            System.out.println("\n\n\nDeseja fazer outra operação? [1] sim [2] não");
            int confirma = scan.nextInt(); // Lê a confirmação do usuário

            // Se o usuário não quiser continuar, mostra o resumo
            if (confirma == 2) {
                System.out.println("\n\n\nRESUMO DA SUA OPERAÇÃO\n\nSaque: " + banco.getSaque() + "\nDeposito: " +
                        banco.getDeposito() + "\nSaldo: " + banco.getSaldoAtual());
                break; // Encerra o loop
            }
        }

        scan.close(); // Fecha o scanner
    }
}
