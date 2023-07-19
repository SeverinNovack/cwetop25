package org.cwe;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.StringReader;


public class Rank24_2022_CWE611_XMLExternalEntityReference {
    public static void main(String[] args) {
        // Potentially malicious XML content
        String maliciousXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<!DOCTYPE foo ["
                + "<!ENTITY xxe SYSTEM \"file:///etc/passwd\">"
                + "]>"
                + "<data>&xxe;</data>";
        bad(maliciousXml);

        System.out.println("------------------------");

        good(maliciousXml);
    }

    private static void bad(String xmlContent) {
        // Bad example: Vulnerable to OS command injection
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlContent))); // Parse XML from untrusted source

            System.out.println(document.toString());
            // Process the XML document
            // ...
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static void good(String xmlContent) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true); // Disallow DTD
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false); // Disable external entities
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false); // Disable parameter entities

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlContent))); // Parse XML from untrusted source

            System.out.println(document.toString());
            // Process the XML document
            // ...
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

