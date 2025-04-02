import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;

public class AutoYantra
{
    private static void PrintNodeListbyTag(NodeList nl, String tagName)
    {
        System.out.println("Top level container length = " + nl.getLength());
        for(int i=0 ; i<nl.getLength() ; i++ )
        {
            Node firstNode = nl.item(i);

            if(firstNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element firstElement = (Element)firstNode;
                System.out.println("Tag: " + firstElement.getTagName());

                //System.out.println("UUID: " + firstNode.getAttribute("UUID"));
                NodeList ShortName = firstElement.getElementsByTagName(tagName);

                Element firstNameSN = (Element)ShortName.item(0);
                NodeList text = firstNameSN.getChildNodes();
                System.out.println("Tag: " + firstNameSN.getTagName()); 

                System.out.println("Short Name: " + text.item(0).getNodeValue().trim());
                System.out.println("\n");
            }                
        }
    }

    public static void main(String[] args) 
    {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("C:\\Users\\veere\\Downloads\\sandbox\\JSAR\\4.3.1\\CanIf.epd");
            //Document doc = builder.parse("C:\\Users\\veere\\Downloads\\sandbox\\PySAR\\4.3.1\\Lin.epd");
            //Document doc = builder.parse("C:\\Users\\veere\\Downloads\\sandbox\\PySAR\\4.3.1\\Gpt.epd");
            //Document doc = builder.parse("C:\\Users\\veere\\Downloads\\sandbox\\PySAR\\4.3.1\\CanIf.epd");
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("/AR-PACKAGES/AR-PACKAGE/AR-PACKAGES/AR-PACKAGE/ELEMENTS/ECUC-MODULE-DEF/CONTAINERS/*");
            
            //XPathExpression expr = xpath.compile("//SHORT-NAME");
            //XPathExpression expr = xpath.compile("//ECUC-PARAM-CONF-CONTAINER-DEF");
            NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            PrintNodeListbyTag(nl, "PARAMETERS");
        }
        catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
