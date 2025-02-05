package org.example.employeeManager;

import org.example.model.employee.Employee;
import org.example.readXML.EmployeeReader;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    private boolean isEmployeeListEmpty() {
        if (employees.isEmpty()) {
            System.out.println("📌 Nenhum funcionário cadastrado.");
            return true;
        }
        return false;
    }

    // Exibir funcionários em ordem alfabética
    public void displayEmployees() {
        if (isEmployeeListEmpty()) return;

        // Ordena alfabeticamente pelo nome
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(e -> e.person().name()))
                .toList();

        System.out.println("\n🔹 Lista de Funcionários (Ordenados por Nome) 🔹");
        sortedEmployees.forEach(this::formatEmployeeDetails);
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
        System.out.printf("\n%s\n",
                employees.removeIf(employee -> employee.person().name().equalsIgnoreCase(name))
                        ? String.format("✅ %s foi removido com sucesso.", name)
                        : String.format("❌ Nenhum funcionário com o nome %s foi encontrado.", name)
        );
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
            System.out.printf("📌 Nenhum funcionário faz aniversário no %d: \n", month);
            return;
        }

        System.out.printf("\n🎉 Funcionários que fazem aniversário no mês %d: ", month);
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
        if (isEmployeeListEmpty()) return;

        Employee oldestEmployee = employees.stream()
                // O método `min()` encontra o menor valor baseado em um critério de comparação.
                // Comparando as datas de nascimento para encontrar a data mais antiga (o funcionário mais velho).
                .min((e1, e2) -> e1.person().birthDate().compareTo(e2.person().birthDate()))
                .orElse(null);

        if (oldestEmployee != null) {
            // Calcula a idade do funcionário mais velho, subtraindo a data de nascimento da data atual
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
        if (isEmployeeListEmpty()) return;

        BigDecimal totalSalary = employees.stream()
                // `map(Employee::salary)` transforma cada funcionário no seu salário (extrai o valor do salário).
                .map(Employee::salary)
                // `reduce(BigDecimal.ZERO, BigDecimal::add)` soma todos os salários.
                // Começa com o valor inicial `BigDecimal.ZERO` e usa o método `add` para somar os valores.
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        String formattedTotal = currencyFormatter.format(totalSalary);

        System.out.printf("\n💰 O total dos salários dos funcionários é: R$ %s\n", formattedTotal);
    }

    // Exibir quantos salários mínimos cada funcionário ganha
    public void displaySalariesInMinimumWages() {
        if (isEmployeeListEmpty()) return;

        BigDecimal minimumWage = new BigDecimal("1212.00");

        System.out.println("\n💰 Exibindo salários dos funcionários em termos de salários mínimos:");

        // Para cada funcionário na lista
        employees.forEach(employee -> {
            // Obtém o salário do funcionário
            BigDecimal salary = employee.salary();

            // Calcula quantos salários mínimos o funcionário recebe
            BigDecimal salaryInMinimumWages = salary.divide(minimumWage, 2, RoundingMode.HALF_UP);

            // Exibe o nome do funcionário e quantos salários mínimos ele recebe, formatado com 2 casas decimais
            System.out.printf("👤 %s recebe aproximadamente %.2f salários mínimos.\n",
                    employee.person().name(), salaryInMinimumWages);
        });
    }

}
