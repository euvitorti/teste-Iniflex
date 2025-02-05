package org.example.readXML;

import org.example.model.employee.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeReaderTest {

    /**
     * Testa a criação do EmployeeReader a partir do XML padrão.
     * Verifica se o objeto retornado e a lista de funcionários não são nulos
     * e se a lista contém pelo menos um funcionário.
     */
    @Test
    void fromXML() {
        // Invoca o método para criar o EmployeeReader utilizando o caminho padrão
        EmployeeReader reader = EmployeeReader.fromXML();

        // Verifica se o objeto retornado não é nulo
        assertNotNull(reader, "O EmployeeReader não deve ser nulo.");

        // Verifica se a lista de funcionários foi carregada corretamente
        List<Employee> employees = reader.getEmployees();
        assertNotNull(employees, "A lista de funcionários não deve ser nula.");
        assertFalse(employees.isEmpty(), "A lista de funcionários não deve estar vazia.");

        // Imprimir o tamanho da lista para fins de depuração
        System.out.println("Número de funcionários carregados: " + employees.size());
    }

    /**
     * Testa o método getEmployees() garantindo que os atributos essenciais de cada Employee estejam preenchidos.
     */
    @Test
    void getEmployees() {
        // Cria o EmployeeReader utilizando o XML padrão
        EmployeeReader reader = EmployeeReader.fromXML();
        List<Employee> employees = reader.getEmployees();

        // Verifica se a lista não é nula
        assertNotNull(employees, "A lista de funcionários não deve ser nula.");

        // Caso haja funcionários na lista, valida que os atributos essenciais não estão nulos
        if (!employees.isEmpty()) {
            for (Employee employee : employees) {
                // Verifica se o objeto Person associado ao funcionário não é nulo
                assertNotNull(employee.person(), "O objeto Person do funcionário não deve ser nulo.");
                // Verifica se o salário não é nulo
                assertNotNull(employee.salary(), "O salário do funcionário não deve ser nulo.");
                // Verifica se o cargo (role) não é nulo ou vazio
                assertNotNull(employee.role(), "O cargo do funcionário não deve ser nulo.");
                assertFalse(employee.role().trim().isEmpty(), "O cargo do funcionário não deve ser vazio.");
            }
        }
    }

    /**
     * Testa o cenário em que o caminho para o arquivo XML é inválido.
     * Verifica se o EmployeeReader é criado e se a lista de funcionários retorna vazia.
     */
    @Test
    void testFromXMLWithNonExistentFile() {
        // Define um caminho para um arquivo que não existe
        String invalidPath = "nonexistent.xml";
        EmployeeReader reader = EmployeeReader.fromXML(invalidPath);

        // Verifica se o objeto retornado não é nulo
        assertNotNull(reader, "EmployeeReader não deve ser nulo mesmo com arquivo inexistente.");

        // Verifica se a lista de funcionários foi criada e está vazia,
        // já que o arquivo não foi encontrado ou lido corretamente.
        List<Employee> employees = reader.getEmployees();
        assertNotNull(employees, "A lista de funcionários não deve ser nula.");
        assertTrue(employees.isEmpty(), "A lista de funcionários deve estar vazia para arquivo inexistente.");
    }
}
