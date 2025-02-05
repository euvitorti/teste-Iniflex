package readXML;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadXml {
    public static Document fromXML(String filePath, Class<?> className) throws Exception {
        ClassLoader classLoader = className.getClassLoader();
        File file = new File(classLoader.getResource(filePath).getFile());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(file);
    }
}
