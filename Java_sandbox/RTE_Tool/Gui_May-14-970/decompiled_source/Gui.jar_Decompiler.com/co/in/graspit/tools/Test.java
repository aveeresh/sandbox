package co.in.graspit.tools;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Test {
   public static void main(String[] args) {
      try {
         DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
         Document doc = builder.parse(new File("MAE_RteGen.xml"));
         XPath xPath = XPathFactory.newInstance().newXPath();
         String thePath = "/rtegen/SW_Cs/SW_C[1]/ClientServer[1]/Operation/Parameter[2]/@name";
         NodeList nodeList = (NodeList)xPath.compile(thePath).evaluate(doc, XPathConstants.NODESET);

         for(int i = 0; i < nodeList.getLength(); ++i) {
            System.out.println(nodeList.item(i).getNodeValue());
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }
}
