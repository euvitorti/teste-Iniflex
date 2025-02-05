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

    public static EmployeeReader fromXML() {
        List<Employee> employees = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato de data correto

        try {
            Document document = readXML.ReadXml.fromXML(XML_FILE_PATH, EmployeeReader.class);
            NodeList nodeList = document.getElementsByTagName("employee");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String birthDateStr = element.getElementsByTagName("birthDate").item(0).getTextContent();
                String salaryStr = element.getElementsByTagName("salary").item(0).getTextContent();
                String role = element.getElementsByTagName("role").item(0).getTextContent();

                LocalDate birthDate = LocalDate.parse(birthDateStr, dateFormatter); // Usando o formato correto
                salaryStr = salaryStr.replace(".", "").replace(",", "."); // Remover ponto do milhar e substituir vírgula por ponto
                BigDecimal salary = new BigDecimal(salaryStr);
                Person person = new Person(name, birthDate);
                Employee employee = new Employee(person, salary, role);
                employees.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new EmployeeReader(employees);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
