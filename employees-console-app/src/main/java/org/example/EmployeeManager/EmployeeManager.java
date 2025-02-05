package org.example.EmployeeManager;

import org.example.model.employee.Employee;
import org.example.readXML.EmployeeReader;

import java.util.List;
import java.util.Iterator;

public class EmployeeManager {

    private List<Employee> employees;

    public EmployeeManager() {
        employees = EmployeeReader.fromXML().getEmployees();
    }

    public void removeEmployeeByName(String name) {
        Iterator<Employee> iterator = employees.iterator();

        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.person().name().equalsIgnoreCase(name)) { // Ignora maiúsculas e minúsculas
                iterator.remove();
                System.out.printf("\n✅ %s foi removido com sucesso.\n", name);
                return;
            }
        }

        System.out.println("❌ Funcionário não encontrado.");
    }

    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("📌 Não há funcionários cadastrados.");
            return;
        }

        System.out.println("\n🔹 Lista de Funcionários 🔹");
        for (Employee employee : employees) {
            System.out.printf("""
            ----------------------------
            👤 Nome: %s
            🎂 Data de Nascimento: %s
            💼 Cargo: %s
            💰 Salário: R$ %s
            ----------------------------
            """,
                    employee.person().name(),
                    employee.person().birthDate(),
                    employee.role(),
                    employee.salary());
        }
    }
}
