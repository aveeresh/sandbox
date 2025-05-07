package co.in.graspit.tools;

import co.in.graspit.Enum.MyColor;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SettingData {
   private static SettingData instance;
   private final StorePaths sp = StorePaths.getInstance();
   private final JPanel settingPanel = new JPanel(new GridLayout(1, 2));
   private Document doc;
   private final String[] settingDataNames = new String[]{"source_file_path_location"};
   private JLabel labelSourcePath = new JLabel("Source Path", 0);
   private JTextField textSourcePath = new JTextField();

   private SettingData() {
      this.InitializeData();
   }

   public static SettingData getInstance() {
      if (instance == null) {
         instance = new SettingData();
      }

      return instance;
   }

   public void InitializeData() {
      this.labelSourcePath.setBackground(MyColor.PrimaryColorLight.color);
      this.settingPanel.setBackground(MyColor.PrimaryColorLight.color);
      this.settingPanel.add(this.labelSourcePath);
      this.settingPanel.add(this.textSourcePath);
   }

   public JPanel getSettingPanel() {
      this.readedData();
      return this.settingPanel;
   }

   private void readedData() {
      this.textSourcePath.setText(this.sp.getSourceFilePathLocation());
   }

   private void writeData() {
      this.sp.setSourceFilePathLocation(this.textSourcePath.getText());
   }

   public void readSettingXML() {
      try {
         DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
         this.doc = builder.parse(new File("source/Setting/setting.xml"));
         this.doc.getDocumentElement().normalize();
         NodeList nList = this.doc.getElementsByTagName(this.doc.getDocumentElement().getNodeName());

         for(int i = 0; i < nList.getLength(); ++i) {
            if (nList.item(i).getNodeType() == 1) {
               NodeList childNodeList = nList.item(i).getChildNodes();

               for(int j = 0; j < childNodeList.getLength(); ++j) {
                  if (childNodeList.item(j).getNodeType() == 1 && this.settingDataNames[0].equals(childNodeList.item(j).getNodeName())) {
                     this.sp.setSourceFilePathLocation(childNodeList.item(j).getTextContent());
                  }
               }
            }
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   public void saveChanges() {
      try {
         this.writeData();
         NodeList nList = this.doc.getElementsByTagName(this.doc.getDocumentElement().getNodeName());

         NodeList childNodeList;
         int j;
         for(int i = 0; i < nList.getLength(); ++i) {
            if (nList.item(i).getNodeType() == 1) {
               childNodeList = nList.item(i).getChildNodes();

               for(j = 0; j < childNodeList.getLength(); ++j) {
                  if (childNodeList.item(j).getNodeType() == 1 && this.settingDataNames[0].equals(childNodeList.item(j).getNodeName())) {
                     childNodeList.item(j).setTextContent(this.sp.getSourceFilePathLocation());
                  }
               }
            }
         }

         XPath xp = XPathFactory.newInstance().newXPath();
         childNodeList = (NodeList)xp.evaluate("//text()[normalize-space(.)='']", this.doc, XPathConstants.NODESET);

         for(j = 0; j < childNodeList.getLength(); ++j) {
            Node node = childNodeList.item(j);
            node.getParentNode().removeChild(node);
         }

         Transformer transformer = TransformerFactory.newInstance().newTransformer();
         transformer.setOutputProperty("indent", "yes");
         transformer.transform(new DOMSource(this.doc), new StreamResult("source/Setting/setting.xml"));
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }
}
