package org.example.readXML;

import org.example.model.person.Person;
import org.example.model.employee.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeReader {
    private static final String XML_FILE_PATH = "employeeFiles/employees.xml";
    private List<Employee> employees;

    private EmployeeReader(List<Employee> employees) {
        this.employees = employees;
    }

    // Método para ler o arquivo XML e retornar um objeto EmployeeReader com a lista de funcionários
    public static EmployeeReader fromXML() {
        List<Employee> employees = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            Document document = readXML.ReadXml.fromXML(XML_FILE_PATH, EmployeeReader.class);
            NodeList nodeList = document.getElementsByTagName("employee");

            // Iterando sobre todos os elementos "employee" no XML
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String birthDateStr = element.getElementsByTagName("birthDate").item(0).getTextContent();
                String salaryStr = element.getElementsByTagName("salary").item(0).getTextContent();
                String role = element.getElementsByTagName("role").item(0).getTextContent();

                // Convertendo a data de nascimento para o formato LocalDate
                LocalDate birthDate = LocalDate.parse(birthDateStr, dateFormatter);

                // Corrigindo o formato do salário (remover ponto e substituir vírgula por ponto)
                salaryStr = salaryStr.replace(".", "").replace(",", ".");
                BigDecimal salary = new BigDecimal(salaryStr);

                // Criando o objeto Person e Employee
                Person person = new Person(name, birthDate);
                Employee employee = new Employee(person, salary, role);

                // Adicionando o funcionário à lista
                employees.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new EmployeeReader(employees); // Retornando o EmployeeReader com a lista de funcionários
    }

    // Método para acessar a lista de funcionários
    public List<Employee> getEmployees() {
        return employees;
    }
}
