package org.example;

import org.example.model.employee.Employee;
import org.example.readXML.EmployeeReader;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static void addEmployee(){
        EmployeeReader.fromXML(); // Carrega os dados (só faz isso uma vez)
        List<Employee> employees = EmployeeReader.fromXML().getEmployees(); // Obtém os funcionários armazenados

        System.out.println("Funcionários cadastrados com sucesso!");

//        // Agora você pode percorrer a lista e fazer o que quiser:
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }
    }

    public static void main(String[] args) {
        System.out.printf("""
        Olá! O que você deseja fazer?
        
        [1] - Adicionar funcionário
        
        """);

        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                addEmployee();
                break;
            case 2:
        }
    }
}