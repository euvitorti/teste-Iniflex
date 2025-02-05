package org.example;

import org.example.EmployeeManager.EmployeeManager;

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

    public static void main(String[] args) {
        while (true) {
            System.out.println("""
            Olá! O que você deseja fazer?
            
            [1] - Listar funcionários
            [2] - Remover um funcionário
            [3] - Sair
            """);

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após nextInt()

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> removeEmployee();
                case 3 -> {
                    System.out.println("Encerrando...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
