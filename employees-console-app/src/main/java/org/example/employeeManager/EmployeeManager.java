package org.example.employeeManager;

import org.example.model.employee.Employee;
import org.example.readXML.EmployeeReader;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeManager {

    private List<Employee> employees;
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato de data
    private static final NumberFormat currencyFormatter = NumberFormat.getInstance(new Locale("pt", "BR"));

    public EmployeeManager() {
        employees = EmployeeReader.fromXML().getEmployees();
    }

    // Aumentar salários em 10%
    public void increaseSalariesBy10Percent() {
        employees = employees.stream()
                .map(employee -> {
                    BigDecimal newSalary = employee.salary().multiply(BigDecimal.valueOf(1.10)); // Aumento de 10%
                    return new Employee(employee.person(), newSalary, employee.role()); // Cria um novo Employee com o salário atualizado
                })
                .collect(Collectors.toList());

        System.out.println("\n📢 Todos os funcionários receberam um aumento de 10% no salário!\n");
    }

    // Remover funcionário pelo nome
    public void removeEmployeeByName(String name) {
        employees.removeIf(employee -> employee.person().name().equalsIgnoreCase(name));
        System.out.printf("\n✅ %s foi removido com sucesso.\n", name);
    }

    // Exibir funcionários em ordem alfabética
    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("📌 Não há funcionários cadastrados.");
            return;
        }

        // Ordena alfabeticamente pelo nome
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(e -> e.person().name()))
                .toList();

        System.out.println("\n🔹 Lista de Funcionários (Ordenados por Nome) 🔹");
        sortedEmployees.forEach(this::formatEmployeeDetails);
    }

    // Agrupar funcionários por cargo
    public Map<String, List<Employee>> groupEmployeesByRole() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::role));
    }

    // Exibir funcionários pelo cargo escolhido
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

    private void formatEmployeeDetails(Employee employee) {
        // Formatar o salário com separador de milhar e duas casas decimais
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String formattedSalary = "R$ " + decimalFormat.format(employee.salary());

        System.out.printf(""" 
        ---------------------------- 
        👤 Nome: %s 
        🎂 Data de Nascimento: %s 
        💼 Cargo: %s 
        💰 Salário: %s 
        ---------------------------- 
        """,
                employee.person().name(),
                employee.person().birthDate().format(dateFormatter), // Formatar data como dd/MM/yyyy
                employee.role(),
                formattedSalary);
    }

    // Exibir funcionários com aniversários no mês escolhido
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

    // Exibir funcionários com aniversários em Outubro (10) e Dezembro (12)
    public void displayDefaultBirthMonths() {
        List<Integer> defaultMonths = Arrays.asList(10, 12);

        for (int month : defaultMonths) {
            displayEmployeesByBirthMonth(month);
        }
    }

    // Exibir o funcionário mais velho
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

    // Exibir o total dos salários dos funcionários
    public void displayTotalSalaries() {
        if (employees.isEmpty()) {
            System.out.println("📌 Nenhum funcionário cadastrado.");
            return;
        }

        BigDecimal totalSalary = employees.stream()
                .map(Employee::salary)
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Soma todos os salários

        String formattedTotal = currencyFormatter.format(totalSalary);

        System.out.printf("\n💰 O total dos salários dos funcionários é: R$ %s\n", formattedTotal);
    }

    // Exibir quantos salários mínimos cada funcionário ganha
    public void displaySalariesInMinimumWages() {
        if (employees.isEmpty()) {
            System.out.println("📌 Nenhum funcionário cadastrado.");
            return;
        }

        BigDecimal minimumWage = new BigDecimal("1212.00");

        System.out.println("\n💰 Salários dos funcionários em múltiplos do salário mínimo:");

        employees.forEach(employee -> {
            BigDecimal salary = employee.salary();
            BigDecimal salaryInMinimumWages = salary.divide(minimumWage, 2, BigDecimal.ROUND_HALF_UP); // Divide e mantém 2 casas decimais

            System.out.printf("👤 %s recebe aproximadamente %.2f salários mínimos.\n",
                    employee.person().name(), salaryInMinimumWages);
        });
    }
}
