package co.in.graspit.readWriteComponentData;

import co.in.graspit.tools.WriteAndReadXMLDocFile;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AddComponent {
   private final WriteAndReadXMLDocFile xmlFile = WriteAndReadXMLDocFile.getInstance();
   private JTextField componentName = new JTextField("ComponentName");
   private JTextField cyclicTime = new JTextField("200ms");
   private JTextField componentType = new JTextField("SWX1_SWS");
   private JPanel panel = new JPanel(new GridLayout(0, 1));
   private JPanel addCompPanel = new JPanel(new GridLayout(0, 1));
   private JTextField compName = new JTextField("");
   private JTree tree;
   private DocumentBuilder builder;
   private XPath xp;

   public AddComponent(JTree jtree) {
      try {
         this.builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
         this.xp = XPathFactory.newInstance().newXPath();
      } catch (ParserConfigurationException var3) {
         var3.printStackTrace();
         this.builder = null;
         this.xp = null;
      }

      this.panel.setBackground(new Color(232, 232, 255));
      this.panel.add(new JLabel("Component Name"));
      this.panel.add(this.componentName);
      this.panel.add(new JLabel("Component Type"));
      this.panel.add(this.componentType);
      this.panel.add(new JLabel("Cyclic Time"));
      this.panel.add(this.cyclicTime);
      this.addCompPanel.add(new JLabel("Abbreviation"));
      this.addCompPanel.add(this.compName);
      this.tree = jtree;
   }

   public void showDialogToAddComponent(JTree tree) {
      this.responce(JOptionPane.showConfirmDialog((Component)null, this.addCompPanel, "Add Component", 2, -1), tree);
   }

   private void responce(int result, JTree tree) {
      if (result == 0) {
         try {
            Document doc = this.builder.parse(new File("MAE_RteGen.xml"));
            String path = "//SW_Cs/SW_C[@abbreviation='" + this.compName.getText().trim() + "']";
            NodeList nodeList = (NodeList)this.xp.compile(path).evaluate(doc, XPathConstants.NODESET);
            if (nodeList.getLength() <= 0 && this.compName.getText().trim().length() > 0) {
               System.out.println("Added: " + this.compName.getText());
               String pathSup = "//SW_Cs[1]";
               NodeList nodeSupList = (NodeList)this.xp.compile(pathSup).evaluate(doc, XPathConstants.NODESET);
               Element element = (Element)nodeSupList.item(0);
               Element newComponent = doc.createElement("SW_C");
               element.appendChild(newComponent);
               newComponent.setAttribute("abbreviation", this.compName.getText().trim());
               NodeList nl = (NodeList)this.xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

               for(int i = 0; i < nl.getLength(); ++i) {
                  Node node = nl.item(i);
                  node.getParentNode().removeChild(node);
               }

               Transformer tr = TransformerFactory.newInstance().newTransformer();
               tr.setOutputProperty("indent", "yes");
               tr.transform(new DOMSource(doc), new StreamResult(new File("MAE_RteGen.xml")));
               this.xmlFile.setTreeData(tree);
            } else if (nodeList.getLength() > 0) {
               this.xmlFile.writeLog("Can't Add Component Already Exist: " + this.compName.getText());
            } else {
               this.xmlFile.writeLog("Abbreviation can not be Empty.");
            }
         } catch (Exception var13) {
            var13.printStackTrace();
         }

         this.compName.setText("");
      } else {
         System.out.println("Cancelled");
      }

   }

   public int showDialog() {
      return JOptionPane.showConfirmDialog((Component)null, this.panel, "Add Component", 2, -1);
   }

   public void processInputs(int result) {
      if (result == 0) {
         if (this.componentName.getText() != null) {
            this.writeComponentToXml(this.componentName.getText());
         }

         System.out.println(" " + this.componentName.getText() + " " + this.componentType.getText());
      } else {
         System.out.println("Cancelled");
      }

   }

   public void writeComponentToXml(String componentName) {
      String filePath = "componentList.xml";
      File xmlFile = new File(filePath);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

      try {
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(xmlFile);
         addElement(doc, componentName);
         doc.getDocumentElement().normalize();
         XPath xp = XPathFactory.newInstance().newXPath();
         NodeList nl = (NodeList)xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

         for(int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            node.getParentNode().removeChild(node);
         }

         Transformer transformer = TransformerFactory.newInstance().newTransformer();
         transformer.setOutputProperty("indent", "yes");
         transformer.transform(new DOMSource(doc), new StreamResult(xmlFile));
         System.out.println("XML file updated successfully");
         this.addComponentFile(componentName);
         this.updateTreeComponent(componentName);
      } catch (Exception var11) {
         var11.printStackTrace();
      }

   }

   private void addComponentFile(String componentName) {
      try {
         DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
         Document dom = builder.newDocument();
         Element root = dom.createElement(componentName);
         dom.appendChild(root);
         Attr attr = dom.createAttribute("id");
         attr.setValue("1");
         root.setAttributeNode(attr);
         Element cycTime = dom.createElement("Cyclic-Time");
         cycTime.setTextContent(this.cyclicTime.getText());
         Element compType = dom.createElement("COMPONENT_TYPE");
         compType.setTextContent(this.componentType.getText());
         root.appendChild(cycTime);
         root.appendChild(compType);
         Transformer tr = TransformerFactory.newInstance().newTransformer();
         tr.setOutputProperty("indent", "yes");
         tr.transform(new DOMSource(dom), new StreamResult(new File(componentName + ".xml")));
      } catch (Exception var9) {
         var9.printStackTrace();
      }

   }

   private static void addElement(Document doc, String componentName) {
      NodeList componentList = doc.getElementsByTagName("ELEMENTS");
      Element emp = null;

      for(int i = 0; i < componentList.getLength(); ++i) {
         emp = (Element)componentList.item(i);
         Element componentElement = doc.createElement("COMPONENT-NAME");
         componentElement.appendChild(doc.createTextNode(componentName));
         emp.appendChild(componentElement);
         createComponentXml(componentName);
      }

   }

   private static void createComponentXml(String componentName) {
   }

   private void updateTreeComponent(String componentName) {
      DefaultTreeModel model = (DefaultTreeModel)this.tree.getModel();
      DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
      model.insertNodeInto(new DefaultMutableTreeNode(componentName), root, root.getChildCount());
      model.reload(root);
   }
}
