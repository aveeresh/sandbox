import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.xpath.*;
import javax.xml.parsers.*;
import javax.io.*;
import java.lang.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ARXmlParser {
    public static void main(String[] args) {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("..\\4.3.1\\CanIf.epd");
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("//AUTOSAR/AR-PACKAGES/AR-PACKAGE/AR-PACKAGES/AR-PACKAGE/ELEMENTS/ECUC-MODULE-DEF/ADMIN-DATA/DOC-REVISIONS/DOC-REVISION/REVISION-LABEL/text()");
            NodeList nl = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);    
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            //e.printStackTrace();
            System.out.println("Exception");
        }
    }
}