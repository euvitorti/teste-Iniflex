package org.example.employeeManager;

import org.example.model.employee.Employee;
import org.example.model.person.Person;
import org.example.readXML.EmployeeReader;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeManager {

    private List<Employee> employees;
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat currencyFormatter = NumberFormat.getInstance(new Locale("pt", "BR"));

    public EmployeeManager() {
        employees = EmployeeReader.fromXML().getEmployees();
    }

    // Increase salaries by 10%
    public void increaseSalariesBy10Percent() {
        employees = employees.stream()
                .map(employee -> {
                    BigDecimal newSalary = employee.salary().multiply(BigDecimal.valueOf(1.10)); // 10% increase
                    return new Employee(employee.person(), newSalary, employee.role()); // Create new Employee with updated salary
                })
                .collect(Collectors.toList());

        System.out.println("\n📢 Todos os funcionários receberam um aumento de 10% no salário!\n");
    }

    // Remove employee by name
    public void removeEmployeeByName(String name) {
        employees.removeIf(employee -> employee.person().name().equalsIgnoreCase(name));
        System.out.printf("\n✅ %s foi removido com sucesso.\n", name);
    }

    // Display all employees
    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("📌 Não há funcionários cadastrados.");
            return;
        }

        System.out.println("\n🔹 Lista de Funcionários 🔹");
        employees.forEach(this::formatEmployeeDetails);
    }

    // Group employees by role
    public Map<String, List<Employee>> groupEmployeesByRole() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::role));
    }

    // Display employees by chosen role
    public void displayEmployeesByRole(String role) {
        Map<String, List<Employee>> groupedEmployees = groupEmployeesByRole();
        List<Employee> employeesInRole = groupedEmployees.get(role);

        if (employeesInRole == null || employeesInRole.isEmpty()) {
            System.out.println("📌 Nenhum funcionário encontrado para este cargo.");
            return;
        }

        System.out.println("\n🔹 Funcionários no cargo: " + role + " 🔹");
        employeesInRole.forEach(this::formatEmployeeDetails);
    }

    // Format employee details for better readability
    private void formatEmployeeDetails(Employee employee) {
        String formattedSalary = currencyFormatter.format(employee.salary());

        System.out.printf("""
            ----------------------------
            👤 Nome: %s
            🎂 Data de Nascimento: %s
            💼 Cargo: %s
            💰 Salário: R$ %s
            ----------------------------
            """,
                employee.person().name(),
                employee.person().birthDate().format(dateFormatter), // Format date as dd/MM/yyyy
                employee.role(),
                formattedSalary);
    }

    public void displayEmployeesByBirthMonth(int month) {
        List<Employee> employeesInMonth = employees.stream()
                .filter(employee -> employee.person().birthDate().getMonthValue() == month)
                .collect(Collectors.toList());

        if (employeesInMonth.isEmpty()) {
            System.out.println("📌 Nenhum funcionário faz aniversário no mês " + month + ".");
            return;
        }

        System.out.println("\n🎉 Funcionários que fazem aniversário no mês " + month + ":");
        employeesInMonth.forEach(this::formatEmployeeDetails);
    }

    // Display employees with birthdays in October (10) and December (12)
    public void displayDefaultBirthMonths() {
        List<Integer> defaultMonths = Arrays.asList(10, 12);

        for (int month : defaultMonths) {
            displayEmployeesByBirthMonth(month);
        }
    }

    public void displayOldestEmployee() {
        if (employees.isEmpty()) {
            System.out.println("📌 Nenhum funcionário cadastrado.");
            return;
        }

        Employee oldestEmployee = employees.stream()
                .min((e1, e2) -> e1.person().birthDate().compareTo(e2.person().birthDate())) // Ordena pela data de nascimento mais antiga
                .orElse(null);

        if (oldestEmployee != null) {
            int age = java.time.Period.between(oldestEmployee.person().birthDate(), java.time.LocalDate.now()).getYears();
            System.out.printf("""
        🏆 Funcionário mais velho:
        👤 Nome: %s
        🎂 Idade: %d anos
        """, oldestEmployee.person().name(), age);
        }
    }

}
