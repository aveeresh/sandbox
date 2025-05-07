package co.in.graspit.readWriteComponentData;

import co.in.graspit.enums.ParametersRTE;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AddPort {
   private JPanel panel = new JPanel(new GridLayout(0, 4));
   private ArrayList<JLabel> allLabels = new ArrayList();
   private ArrayList<JTextField> allTextfields = new ArrayList();
   private ArrayList<JComboBox<String>> allDropDownFields = new ArrayList();
   private JTree tree;
   private ArrayList<String> allComponentsList;

   public AddPort(JTree jtree, ArrayList<String> allComp) {
      this.tree = jtree;
      this.allComponentsList = allComp;
      ParametersRTE[] var6;
      int var5 = (var6 = ParametersRTE.values()).length;

      for(int var4 = 0; var4 < var5; ++var4) {
         ParametersRTE para = var6[var4];
         JLabel jlabel = new JLabel(para.value);
         this.allLabels.add(jlabel);
         this.panel.add(jlabel);
         JComboBox txtField;
         if (para == ParametersRTE.FUNCTYPE) {
            txtField = new JComboBox(new String[]{"Rte_Call", "Rte_Read", "Rte_Write", "Rte_IsUpdated"});
            this.allDropDownFields.add(txtField);
            this.allTextfields.add((Object)null);
            this.panel.add(txtField);
         } else if (para == ParametersRTE.REQ_Comp_Name) {
            txtField = new JComboBox(new String[]{"SWX1_SENSOR", "SWX2_LOGICALMANAGER", "SWX3_ACTUATOR", "SWX4"});
            this.allDropDownFields.add(txtField);
            this.allTextfields.add((Object)null);
            this.panel.add(txtField);
         } else if (para == ParametersRTE.PRO_Comp_Type) {
            txtField = new JComboBox(new String[]{"SWCS", "UBSW"});
            this.allDropDownFields.add(txtField);
            this.allTextfields.add((Object)null);
            this.panel.add(txtField);
         } else if (para == ParametersRTE.PRO_Comp_Name) {
            txtField = new JComboBox(new String[]{"SWX1_SENSOR", "SWX2_LOGICALMANAGER", "SWX3_ACTUATOR", "SWX4"});
            this.allDropDownFields.add(txtField);
            this.allTextfields.add((Object)null);
            this.panel.add(txtField);
         } else {
            JTextField txtField = new JTextField();
            if (para == ParametersRTE.REQ_Comp_Type) {
               txtField.setText("SWCS");
               txtField.setEditable(false);
            }

            this.allDropDownFields.add((Object)null);
            this.allTextfields.add(txtField);
            this.panel.add(txtField);
         }
      }

   }

   public int showDialog() {
      return JOptionPane.showConfirmDialog((Component)null, this.panel, "Add Port", 2, -1);
   }

   public void processInputs(int result, String componentName) {
      if (result == 0) {
         this.addPortToCompXml(componentName);
      } else {
         System.out.println("Cancelled");
      }

   }

   public void addPortToCompXml(String componentName) {
      String filePath = componentName + ".xml";
      File xmlFile = new File(filePath);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

      try {
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(xmlFile);
         doc.getDocumentElement().normalize();
         this.addElement(doc, componentName);
         doc.getDocumentElement().normalize();
         XPath xp = XPathFactory.newInstance().newXPath();
         NodeList nl = (NodeList)xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

         for(int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            node.getParentNode().removeChild(node);
         }

         Transformer transformer = TransformerFactory.newInstance().newTransformer();
         DOMSource source = new DOMSource(doc);
         StreamResult result = new StreamResult(xmlFile);
         transformer.setOutputProperty("indent", "yes");
         transformer.transform(source, result);
         System.out.println("XML file updated successfully");
         this.updateTreeComponent(componentName, ((JTextField)this.allTextfields.get(0)).getText());
      } catch (Exception var12) {
         var12.printStackTrace();
      }

   }

   private void addElement(Document doc, String componentName) {
      NodeList componentList = doc.getElementsByTagName(componentName);
      Element emp = null;
      emp = (Element)componentList.item(0);
      Element portElement = doc.createElement("Port");

      for(int portMembers = 0; portMembers < this.allTextfields.size(); ++portMembers) {
         Element member;
         if (this.allTextfields.get(portMembers) != null) {
            member = doc.createElement(((JLabel)this.allLabels.get(portMembers)).getText());
            member.setTextContent(((JTextField)this.allTextfields.get(portMembers)).getText());
            portElement.appendChild(member);
         } else {
            member = doc.createElement(((JLabel)this.allLabels.get(portMembers)).getText());
            member.setTextContent(((JComboBox)this.allDropDownFields.get(portMembers)).getSelectedItem().toString());
            portElement.appendChild(member);
         }
      }

      emp.appendChild(portElement);
   }

   private void updateTreeComponent(String componentName, String portName) {
      DefaultTreeModel model = (DefaultTreeModel)this.tree.getModel();
      DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
      int compIndex = 0;

      for(Iterator var8 = this.allComponentsList.iterator(); var8.hasNext(); ++compIndex) {
         String compName = (String)var8.next();
         if (compName.equals(componentName)) {
            DefaultMutableTreeNode node1 = (DefaultMutableTreeNode)root.getChildAt(compIndex);
            System.out.println("Component" + componentName);
            model.insertNodeInto(new DefaultMutableTreeNode(portName), node1, node1.getChildCount());
            model.reload(root);
         }
      }

   }
}
