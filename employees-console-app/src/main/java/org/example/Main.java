package org.example;

import org.example.employeeManager.EmployeeManager;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeManager manager = new EmployeeManager(); // Criar apenas uma instância do gerenciador

    // Função para remover um funcionário
    private static void removeEmployee() {
        System.out.println("""
                [1] - Para remover o funcionário João
                [2] - Escolher outro funcionário
                """);
        int choice = scanner.nextInt(); // Leitura da escolha do usuário

        // Se o usuário escolheu remover João
        if(choice == 1){
            manager.removeEmployeeByName("João"); // Remove o funcionário João
        } else {
            System.out.println("Digite o nome do funcionário: ");
            String name = scanner.next(); // Leitura do nome do funcionário a ser removido
            manager.removeEmployeeByName(name); // Remove o funcionário com o nome fornecido
        }
    }

    // Função para listar os funcionários por cargo
    private static void listEmployeesByRole() {
        System.out.println("Digite a função para listar os funcionários:");
        String role = scanner.nextLine(); // Leitura do cargo
        manager.displayEmployeesByRole(role); // Exibe os funcionários com o cargo fornecido
    }

    // Função para listar os funcionários por mês de aniversário
    private static void listEmployeesByBirthMonth() {
        System.out.println("""
        Escolha uma opção:
        [1] - Listar aniversariantes dos meses 10 e 12
        [2] - Escolher um mês específico
        """);

        int choice = scanner.nextInt(); // Leitura da escolha do usuário
        scanner.nextLine(); // Consumir a quebra de linha

        // Se o usuário escolheu listar aniversariantes de outubro e dezembro
        if (choice == 1) {
            manager.displayDefaultBirthMonths(); // Exibe os aniversariantes desses meses
        } else if (choice == 2) {
            System.out.println("Digite o número do mês (1-12): ");
            int month = scanner.nextInt(); // Leitura do mês desejado
            scanner.nextLine(); // Consumir a quebra de linha

            // Verifica se o mês digitado é válido
            if (month < 1 || month > 12) {
                System.out.println("📌 Mês inválido! Digite um valor entre 1 e 12.");
            } else {
                manager.displayEmployeesByBirthMonth(month); // Exibe os aniversariantes do mês especificado
            }
        } else {
            System.out.println("📌 Opção inválida!"); // Caso a escolha seja inválida
        }
    }

    public static void main(String[] args) {
        while (true) { // Loop principal do menu
            System.out.println("""
                [1] - Listar funcionários
                [2] - Remover um funcionário
                [3] - Aumentar salário de todos os funcionários em 10%
                [4] - Listar funcionários por cargo
                [5] - Listar por mês de aniversário
                [6] - Listar funcionário mais velho
                [7] - Exibir o total dos salários
                [8] - Exibir quantos salários mínimos cada funcionário ganha
                [10] - Sair
            """);

            int choice = scanner.nextInt(); // Leitura da opção escolhida
            scanner.nextLine(); // Consumir a quebra de linha após nextInt()

            switch (choice) { // Estrutura de controle para cada escolha
                case 1 -> manager.displayEmployees(); // Exibe os funcionários
                case 2 -> removeEmployee(); // Chama a função para remover um funcionário
                case 3 -> manager.increaseSalariesBy10Percent(); // Aumenta o salário de todos em 10%
                case 4 -> listEmployeesByRole(); // Lista os funcionários por cargo
                case 5 -> listEmployeesByBirthMonth(); // Lista os funcionários por mês de aniversário
                case 6 -> manager.displayOldestEmployee(); // Exibe o funcionário mais velho
                case 7 -> manager.displayTotalSalaries(); // Exibe o total de salários
                case 8 -> manager.displaySalariesInMinimumWages(); // Exibe salários em múltiplos do salário mínimo
                case 10 -> {
                    System.out.println("Encerrando..."); // Encerra o programa
                    return;
                }
                default -> System.out.println("Opção inválida!"); // Caso a opção seja inválida
            }
        }
    }
}
