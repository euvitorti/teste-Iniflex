package org.example;

import org.example.employeeManager.EmployeeManager;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeManager manager = new EmployeeManager(); // Instância do gerenciador

    // Função para remover um funcionário
    private static void removeEmployee() {
        System.out.println("""
                [1] - Para remover o funcionário João
                [2] - Escolher outro funcionário
                """);

        int choice = -1;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
        } catch (Exception e) {
            System.out.println("Entrada inválida! Digite um número.");
            scanner.nextLine(); // Consumir o buffer da entrada inválida
        }

        String name = (choice == 1) ? "João" : scanner.next(); // Escolher o nome conforme a opção
        manager.removeEmployeeByName(name);
    }

    // Função para listar os funcionários por cargo
    private static void listEmployeesByRole() {
        System.out.println("Digite a função para listar os funcionários:");
        manager.displayEmployeesByRole(scanner.nextLine());
    }

    // Função para listar os funcionários por mês de aniversário
    private static void listEmployeesByBirthMonth() {
        System.out.println("""
        Escolha uma opção:
        [1] - Listar aniversariantes dos meses 10 e 12
        [2] - Escolher um mês específico
    """);

        int choice = -1;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Entrada inválida! Digite um número.");
            scanner.nextLine(); // Consumir o buffer
        }

        if (choice == 1) {
            manager.displayDefaultBirthMonths(); // Exibe aniversariantes de Outubro e Dezembro
        } else if (choice == 2) {
            System.out.println("Digite o número do mês (1-12): ");
            int month = -1;
            try {
                month = scanner.nextInt();
                scanner.nextLine();
                // Valida mês e exibe aniversariantes
                String message = (month < 1 || month > 12) ? "📌 Mês inválido! Digite um valor entre 1 e 12." : null;
                if (message != null) {
                    System.out.println(message);
                } else {
                    manager.displayEmployeesByBirthMonth(month); // Exibe aniversariantes do mês específico
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida! Digite um número.");
                scanner.nextLine(); // Consumir o buffer
            }
        } else {
            System.out.println("📌 Opção inválida!");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("""
                [1] - Listar funcionários
                [2] - Remover um funcionário
                [3] - Aumentar salário de todos os funcionários em 10%
                [4] - Listar funcionários por cargo
                [5] - Listar funcionários por mês de aniversário
                [6] - Listar funcionário mais velho
                [7] - Exibir o total dos salários
                [8] - Exibir quantos salários mínimos cada funcionário ganha
                [9] - Sair
            """);

            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada inválida! Digite um número.");
                scanner.nextLine();
            }

            switch (choice) {
                case 1 -> manager.displayEmployees();
                case 2 -> removeEmployee();
                case 3 -> manager.increaseSalariesBy10Percent();
                case 4 -> listEmployeesByRole();
                case 5 -> listEmployeesByBirthMonth();
                case 6 -> manager.displayOldestEmployee();
                case 7 -> manager.displayTotalSalaries();
                case 8 -> manager.displaySalariesInMinimumWages();
                case 9 -> {
                    System.out.println("Encerrando...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
