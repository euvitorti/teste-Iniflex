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
    private String xmlFilePath;
    private List<Employee> employees;

    /**
     * Construtor privado que inicializa o EmployeeReader com o caminho do arquivo XML e a lista de funcionários.
     *
     * @param xmlFilePath o caminho do arquivo XML
     * @param employees   a lista de funcionários obtida a partir do XML
     */
    private EmployeeReader(String xmlFilePath, List<Employee> employees) {
        this.xmlFilePath = xmlFilePath;
        this.employees = employees;
    }

    /**
     * Método que lê um arquivo XML a partir de um caminho fornecido e instancia um EmployeeReader.
     * <p>
     * Este método realiza as seguintes operações:
     * <ul>
     *   <li>Tenta ler o documento XML usando o caminho informado.</li>
     *   <li>Se o documento não for encontrado (ou seja, for nulo), exibe uma mensagem amigável e retorna um
     *       EmployeeReader com uma lista vazia.</li>
     *   <li>Se o documento for lido com sucesso, percorre todos os elementos <code>&lt;employee&gt;</code> e extrai
     *       os dados necessários (nome, data de nascimento, salário e cargo).</li>
     *   <li>Converte os dados para os tipos apropriados (ex.: formata a data e o salário) e cria os objetos
     *       <code>Person</code> e <code>Employee</code>.</li>
     *   <li>Adiciona cada funcionário à lista.</li>
     * </ul>
     *
     * @param xmlFilePath o caminho do arquivo XML a ser lido
     * @return uma instância de EmployeeReader contendo a lista de funcionários extraída
     */
    public static EmployeeReader fromXML(String xmlFilePath) {
        List<Employee> employees = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            // Tenta ler o documento XML usando o caminho informado.
            Document document = readXML.ReadXml.fromXML(xmlFilePath, EmployeeReader.class);

            // Se o documento for nulo, significa que o arquivo não foi encontrado.
            if (document == null) {
                System.out.println("Arquivo XML não encontrado: " + xmlFilePath);
                return new EmployeeReader(xmlFilePath, employees);
            }

            // Obtém todos os nós com a tag <employee>
            NodeList nodeList = document.getElementsByTagName("employee");

            // Processa cada elemento <employee> encontrado no documento XML
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String birthDateStr = element.getElementsByTagName("birthDate").item(0).getTextContent();
                String salaryStr = element.getElementsByTagName("salary").item(0).getTextContent();
                String role = element.getElementsByTagName("role").item(0).getTextContent();

                // Converte a string da data de nascimento para o objeto LocalDate
                LocalDate birthDate = LocalDate.parse(birthDateStr, dateFormatter);
                // Ajusta a formatação do salário removendo pontos (separadores de milhar) e substituindo vírgula por ponto decimal
                salaryStr = salaryStr.replace(".", "").replace(",", ".");
                BigDecimal salary = new BigDecimal(salaryStr);

                Person person = new Person(name, birthDate);
                Employee employee = new Employee(person, salary, role);
                employees.add(employee);
            }
        } catch (Exception e) {
            // Caso ocorra qualquer exceção, exibe uma mensagem amigável com o detalhe do erro.
            System.out.println("Erro ao ler o arquivo XML: " + e.getMessage());
        }

        // Retorna uma instância de EmployeeReader com a lista de funcionários (que pode estar vazia)
        return new EmployeeReader(xmlFilePath, employees);
    }

    /**
     * Método que utiliza um caminho padrão para o arquivo XML.
     *
     * @return uma instância de EmployeeReader contendo os funcionários lidos a partir do caminho padrão
     */
    public static EmployeeReader fromXML() {
        return fromXML("employeeFiles/employees.xml");
    }

    /**
     * Retorna a lista de funcionários lidos a partir do XML.
     *
     * @return a lista de funcionários
     */
    public List<Employee> getEmployees() {
        return employees;
    }
}
