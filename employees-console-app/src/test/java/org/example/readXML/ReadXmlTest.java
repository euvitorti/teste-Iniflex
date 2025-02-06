package org.example.readXML;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import readXML.ReadXml;

import static org.junit.jupiter.api.Assertions.*;

class ReadXmlTest {

    @Test
    void fromXML() {
        // Dado: um arquivo XML válido 'test.xml' localizado na pasta de recursos de teste.
        String filePath = "test.xml";

        // Quando: fazemos o parse do arquivo XML utilizando ReadXml.fromXML.
        Document document = ReadXml.fromXML(filePath, this.getClass());

        // Então: o documento retornado não deve ser nulo.
        assertNotNull(document, "O documento não deve ser nulo para um arquivo XML válido");

        // E: o elemento raiz deve ser "root", conforme esperado.
        assertEquals("root", document.getDocumentElement().getTagName(), "O elemento raiz deve ser 'root'");
    }

    @Test
    void fromXML_withNonExistentFile() {
        // Dado: um arquivo XML inexistente "nonexistent.xml".
        String filePath = "nonexistent.xml";

        // Quando: tentamos fazer o parse do arquivo XML utilizando ReadXml.fromXML.
        Document document = ReadXml.fromXML(filePath, this.getClass());

        // Então: o documento retornado deve ser nulo, indicando falha ao fazer o parse.
        assertNull(document, "O documento deve ser nulo para um arquivo XML inexistente");
    }

    @Test
    void fromXML_withInvalidXML() {
        // Dado: um arquivo XML inválido 'invalid.xml' localizado na pasta de recursos de teste.
        // Esse arquivo deve existir, mas conter um XML malformado.
        String filePath = "invalid.xml";

        // Quando: tentamos fazer o parse do arquivo XML inválido utilizando ReadXml.fromXML.
        Document document = ReadXml.fromXML(filePath, this.getClass());

        // Então: o documento retornado deve ser nulo, pois o XML é malformado e não pode ser parseado.
        assertNull(document, "O documento deve ser nulo para um arquivo XML malformado");
    }
}
