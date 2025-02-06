package readXML;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Objects;

public class ReadXml {

    // Método para ler um arquivo XML e retornar um objeto Document
    public static Document fromXML(String filePath, Class<?> className) {
        try {
            // Carrega a classe do carregador de recursos
            ClassLoader classLoader = className.getClassLoader();

            // Obtém o arquivo a partir do caminho fornecido
            File file = new File(Objects.requireNonNull(classLoader.getResource(filePath)).getFile());

            // Cria uma instância da fábrica de documentos
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Cria um construtor de documentos
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Retorna o documento parseado a partir do arquivo
            return builder.parse(file);
        } catch (Exception e) {
            // Caso ocorra algum erro durante o processo, imprime o erro
            System.out.println("Erro ao ler o arquivo XML: " + e.getMessage());
            return null;
        }
    }
}
