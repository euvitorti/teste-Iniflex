package org.example;

import org.example.employeeManager.EmployeeManager;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeManager manager = new EmployeeManager(); // Criar apenas uma instância

    private static void addEmployee() {
        manager.displayEmployees();
    }

    private static void removeEmployee() {
        System.out.println("""
                [1] - Para remover o funcionário João
                [2] - Escolher outro funcionário
                """);
        int choice = scanner.nextInt();

        if(choice == 1){
            manager.removeEmployeeByName("João");
        } else{
            System.out.println("Digite o nome do funcionário: ");
            String name = scanner.next();
            manager.removeEmployeeByName(name);
        }
    }

    private static void increaseSalaries() {
        manager.increaseSalariesBy10Percent();
    }

    private static void listEmployeesByRole() {
        System.out.println("Digite a função para listar os funcionários:");
        String role = scanner.nextLine();
        manager.displayEmployeesByRole(role);
    }

    private static void listEmployeesByBirthMonth() {
        System.out.println("""
        Escolha uma opção:
        [1] - Listar aniversariantes dos meses 10 e 12
        [2] - Escolher um mês específico
        """);

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        if (choice == 1) {
            manager.displayDefaultBirthMonths();
        } else if (choice == 2) {
            System.out.println("Digite o número do mês (1-12): ");
            int month = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            if (month < 1 || month > 12) {
                System.out.println("📌 Mês inválido! Digite um valor entre 1 e 12.");
            } else {
                manager.displayEmployeesByBirthMonth(month);
            }
        } else {
            System.out.println("📌 Opção inválida!");
        }
    }

    private static void displayOldestEmployee() {
        manager.displayOldestEmployee();
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("""
            Olá! O que você deseja fazer?
            
            [1] - Listar funcionários
            [2] - Remover um funcionário
            [3] - Aumentar salário de todos os funcionários em 10%
            [4] - Listar funcionários por cargo
            [5] - Listar por mês de aniversário
            [6] - Funcionário mais velho
            [10] - Sair
            """);

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após nextInt()

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> removeEmployee();
                case 3 -> increaseSalaries();
                case 4 -> listEmployeesByRole();
                case 5 -> listEmployeesByBirthMonth();
                case 6 -> displayOldestEmployee();
                case 10 -> {
                    System.out.println("Encerrando...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
