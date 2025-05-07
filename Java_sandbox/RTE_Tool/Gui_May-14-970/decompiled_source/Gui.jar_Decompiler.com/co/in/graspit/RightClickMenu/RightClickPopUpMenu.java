package co.in.graspit.RightClickMenu;

import co.in.graspit.Enum.MyColor;
import co.in.graspit.readWriteComponentData.ReadAllComponents;
import co.in.graspit.tools.WriteAndReadXMLDocFile;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
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
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RightClickPopUpMenu extends JPopupMenu {
   private final WriteAndReadXMLDocFile xmlFile = WriteAndReadXMLDocFile.getInstance();
   private JTree tree;
   private ReadAllComponents readAllComp;
   private DocumentBuilder dBuilder;
   private ArrayList<JMenuItem> anItem = new ArrayList(3);
   private JPanel panel = new JPanel(new GridLayout(1, 1));
   private JLabel jLabel = new JLabel("", 0);
   private JPanel editPanel = new JPanel(new GridLayout(1, 2));
   private JLabel jEditLabel = new JLabel("", 0);
   private JTextField jEditText = null;
   private final JPanel editPanelBoth = new JPanel(new GridLayout(1, 2));
   private final JTextField jEditTextBoth1 = new JTextField();
   private final JTextField jEditTextBoth2 = new JTextField();
   private final JTextField jEditTextBoth3 = new JTextField();
   private final JTextField jEditTextBoth4 = new JTextField();
   private final JTextField jEditTextBoth5 = new JTextField();
   private JPanel clientPanel = new JPanel(new GridLayout(2, 2));
   private JPanel senderPanel = new JPanel(new GridLayout(5, 2));
   private JPanel runnablePanel = new JPanel(new GridLayout(2, 2));
   private JPanel operationPanel = new JPanel(new GridLayout(2, 2));
   private JPanel cyclicPanel = new JPanel(new GridLayout(1, 2));
   private JLabel jCyclicLabel = new JLabel("", 0);
   private JComboBox<String> comboBoxCyclic = new JComboBox(new String[]{"50ms", "100ms", "150ms", "200ms"});

   public RightClickPopUpMenu(JTree tree, ReadAllComponents readAllComp, MouseEvent mE) {
      this.tree = tree;
      this.readAllComp = readAllComp;

      try {
         this.dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      } catch (ParserConfigurationException var5) {
         var5.printStackTrace();
         this.dBuilder = null;
      }

   }

   public void addDeleteButton(final MouseEvent mE) {
      this.anItem.add(new JMenuItem("Delete"));
      int size = this.anItem.size() - 1;
      this.add((JMenuItem)this.anItem.get(size));
      ((JMenuItem)this.anItem.get(size)).addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            RightClickPopUpMenu.this.onClickDeleteEvent(mE);
         }
      });
   }

   public void addClientServerButton(final MouseEvent mE) {
      this.anItem.add(new JMenuItem("Add Client Server"));
      int size = this.anItem.size() - 1;
      this.add((JMenuItem)this.anItem.get(size));
      ((JMenuItem)this.anItem.get(size)).addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            RightClickPopUpMenu.this.clientPanel();
            RightClickPopUpMenu.this.onClickClientServerEvent(mE);
         }
      });
   }

   public void addSenderReceiverButton(final MouseEvent mE) {
      this.anItem.add(new JMenuItem("Add Sender Receiver"));
      int size = this.anItem.size() - 1;
      this.add((JMenuItem)this.anItem.get(size));
      ((JMenuItem)this.anItem.get(size)).addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            RightClickPopUpMenu.this.senderPanel();
            RightClickPopUpMenu.this.onClickSenderReceiverEvent(mE);
         }
      });
   }

   public void addRunnableButton(final MouseEvent mE) {
      this.anItem.add(new JMenuItem("Add Runnable"));
      int size = this.anItem.size() - 1;
      this.add((JMenuItem)this.anItem.get(size));
      ((JMenuItem)this.anItem.get(size)).addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            RightClickPopUpMenu.this.runnablePanel();
            RightClickPopUpMenu.this.onClickRunnableEvent(mE);
         }
      });
   }

   public void addOperationButton(final MouseEvent mE) {
      this.anItem.add(new JMenuItem("Add Operation"));
      int size = this.anItem.size() - 1;
      this.add((JMenuItem)this.anItem.get(size));
      ((JMenuItem)this.anItem.get(size)).addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            RightClickPopUpMenu.this.operationPanel();
            RightClickPopUpMenu.this.onClickOperationEvent(mE);
         }
      });
   }

   public void addEditButton(final MouseEvent mE) {
      this.anItem.add(new JMenuItem("Edit"));
      int size = this.anItem.size() - 1;
      this.add((JMenuItem)this.anItem.get(size));
      ((JMenuItem)this.anItem.get(size)).addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            RightClickPopUpMenu.this.changeAndAddInEditPanel();
            RightClickPopUpMenu.this.onClickEditEvent(mE);
         }
      });
   }

   public void addChangeCyclicTimeButton(final MouseEvent mE) {
      this.anItem.add(new JMenuItem("Cyclic-Time"));
      int size = this.anItem.size() - 1;
      this.add((JMenuItem)this.anItem.get(size));
      ((JMenuItem)this.anItem.get(size)).addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            RightClickPopUpMenu.this.changeAndAddInCyclicPanel();
            RightClickPopUpMenu.this.onClickCyclicEvent(mE);
         }
      });
   }

   public void addInItButton(final MouseEvent mE) {
      this.anItem.add(new JMenuItem("Init"));
      int size = this.anItem.size() - 1;
      this.add((JMenuItem)this.anItem.get(size));
      ((JMenuItem)this.anItem.get(size)).addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            RightClickPopUpMenu.this.changeAndAddInPanel();
            RightClickPopUpMenu.this.onClickInItEvent(mE);
         }
      });
   }

   public void addCustomButton(final MouseEvent mE) {
      this.anItem.add(new JMenuItem("Custom Element"));
      int size = this.anItem.size() - 1;
      this.add((JMenuItem)this.anItem.get(size));
      ((JMenuItem)this.anItem.get(size)).addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            RightClickPopUpMenu.this.editBothPanel();
            RightClickPopUpMenu.this.onClickCustomEvent(mE);
         }
      });
   }

   private void changeAndAddInPanel() {
      this.jLabel.setBackground(MyColor.WhiteColor.color);
      this.panel.add(this.jLabel);
      this.panel.setBackground(MyColor.WhiteColor.color);
   }

   private void changeAndAddInEditPanel() {
      this.jEditLabel.setBackground(MyColor.WhiteColor.color);
      this.jEditText = new JTextField();
      this.editPanel.add(this.jEditLabel);
      this.editPanel.add(this.jEditText);
      this.editPanel.setBackground(MyColor.WhiteColor.color);
   }

   private void changeAndAddInCyclicPanel() {
      this.jCyclicLabel.setBackground(MyColor.WhiteColor.color);
      this.cyclicPanel.add(this.jCyclicLabel);
      this.cyclicPanel.add(this.comboBoxCyclic);
      this.cyclicPanel.setBackground(MyColor.WhiteColor.color);
   }

   private void clientPanel() {
      this.clientPanel.add(new JLabel("Direction"));
      this.clientPanel.add(this.jEditTextBoth1);
      this.clientPanel.add(new JLabel("Interface"));
      this.clientPanel.add(this.jEditTextBoth2);
      this.clientPanel.setBackground(MyColor.WhiteColor.color);
   }

   private void senderPanel() {
      this.senderPanel.add(new JLabel("Base Type"));
      this.senderPanel.add(this.jEditTextBoth1);
      this.senderPanel.add(new JLabel("Direction"));
      this.senderPanel.add(this.jEditTextBoth2);
      this.senderPanel.add(new JLabel("Name"));
      this.senderPanel.add(this.jEditTextBoth3);
      this.senderPanel.add(new JLabel("Size"));
      this.senderPanel.add(this.jEditTextBoth4);
      this.senderPanel.add(new JLabel("CAN_ID"));
      this.senderPanel.add(this.jEditTextBoth5);
      this.senderPanel.setBackground(MyColor.WhiteColor.color);
   }

   private void runnablePanel() {
      this.runnablePanel.add(new JLabel("Cycle"));
      this.runnablePanel.add(this.jEditTextBoth1);
      this.runnablePanel.add(new JLabel("Name"));
      this.runnablePanel.add(this.jEditTextBoth2);
      this.runnablePanel.setBackground(MyColor.WhiteColor.color);
   }

   private void operationPanel() {
      this.operationPanel.add(new JLabel("Name"));
      this.operationPanel.add(this.jEditTextBoth1);
      this.operationPanel.add(new JLabel("Return"));
      this.operationPanel.add(this.jEditTextBoth2);
      this.operationPanel.setBackground(MyColor.WhiteColor.color);
   }

   private void editBothPanel() {
      this.editPanelBoth.add(this.jEditTextBoth1);
      this.editPanelBoth.add(this.jEditTextBoth2);
      this.editPanelBoth.setBackground(MyColor.WhiteColor.color);
   }

   private void onClickDeleteEvent(MouseEvent e) {
      TreePath path = this.tree.getPathForLocation(e.getX(), e.getY());
      System.out.println("path: " + path);
      if (path != null) {
         System.out.println(path.getLastPathComponent().toString());
         System.out.println(path.getPathCount());
         if (path.getPathCount() > 2) {
            this.jLabel.setText("Do you want to Delete this Port");
            this.ConfirmDialogPort(JOptionPane.showConfirmDialog((Component)null, this.panel, "Confirm Delete", 2, -1), path, e);
         } else if (path.getPathCount() > 1) {
            this.jLabel.setText("Do you want to Delete this Component");
            this.ConfirmDialogComponent(JOptionPane.showConfirmDialog((Component)null, this.panel, "Confirm Delete", 2, -1), path, e);
         }
      }

   }

   private void ConfirmDialogPort(int result, TreePath path, MouseEvent e) {
      if (result == 0) {
         System.out.println("Delete Port");
         this.DeletePort(path, e.getSource());
         this.tree.remove(this.tree.getComponentAt(e.getX(), e.getY()));
         this.tree.removeSelectionPath(path);
         this.tree.repaint();
         this.tree.revalidate();
      } else {
         System.out.println("Cancelled");
      }

   }

   private void ConfirmDialogComponent(int result, TreePath path, MouseEvent e) {
      if (result == 0) {
         System.out.println("Delete Component");
         this.DeleteComponent(path);
         this.tree.remove(this.tree.getComponentAt(e.getX(), e.getY()));
         this.tree.removeSelectionPath(path);
         this.tree.repaint();
         this.tree.revalidate();
      } else {
         System.out.println("Cancelled");
      }

   }

   private void onClickClientServerEvent(MouseEvent mE) {
      this.clientComponentName(JOptionPane.showConfirmDialog((Component)null, this.clientPanel, "Add Client Server", 2, -1), mE);
   }

   private void onClickSenderReceiverEvent(MouseEvent mE) {
      this.senderComponentName(JOptionPane.showConfirmDialog((Component)null, this.senderPanel, "Add Sender Receiver", 2, -1), mE);
   }

   private void onClickRunnableEvent(MouseEvent mE) {
      this.runnableComponentName(JOptionPane.showConfirmDialog((Component)null, this.runnablePanel, "Add Sender Receiver", 2, -1), mE);
   }

   private void onClickOperationEvent(MouseEvent mE) {
      this.operationComponentName(JOptionPane.showConfirmDialog((Component)null, this.operationPanel, "Add Sender Receiver", 2, -1), mE);
   }

   private void onClickEditEvent(MouseEvent mE) {
      this.jEditLabel.setText("Component");
      this.editComponentName(JOptionPane.showConfirmDialog((Component)null, this.editPanel, "Edit Component", 2, -1), mE);
   }

   private void onClickCyclicEvent(MouseEvent mE) {
      this.jCyclicLabel.setText("Cyclic-Time");
      this.editCyclicTime(JOptionPane.showConfirmDialog((Component)null, this.cyclicPanel, "Edit Cyclic Time Element", 2, -1), mE);
   }

   private void onClickInItEvent(MouseEvent mE) {
      this.jEditLabel.setText("InIt");
      this.editInIt(JOptionPane.showConfirmDialog((Component)null, this.editPanel, "Edit InIt Element", 2, -1), mE);
   }

   private void onClickCustomEvent(MouseEvent mE) {
      this.editCustom(JOptionPane.showConfirmDialog((Component)null, this.editPanelBoth, "Edit Custom Element", 2, -1), mE);
   }

   private void DeletePort(TreePath path, Object eSource) {
      if (this.dBuilder == null) {
         System.out.println("Document Builder is NULL. pop");
      } else {
         try {
            XPath xp = XPathFactory.newInstance().newXPath();
            String componenFileXml = path.getPathComponent(1).toString();
            Document doc = this.dBuilder.parse(new File("MAE_RteGen.xml"));
            String pathComponent = "//SW_Cs/SW_C[@abbreviation='" + componenFileXml + "']";
            NodeList nodeList = (NodeList)xp.compile(pathComponent).evaluate(doc, XPathConstants.NODESET);
            DefaultMutableTreeNode multiNode = (DefaultMutableTreeNode)path.getPathComponent(1);
            int index = multiNode.getIndex((DefaultMutableTreeNode)path.getPathComponent(2));

            int i;
            Node node;
            for(int i = 0; i < nodeList.getLength(); ++i) {
               NodeList subList = nodeList.item(i).getChildNodes();
               int count = 0;

               for(i = 0; i < subList.getLength(); ++i) {
                  node = subList.item(i);
                  if (node.getNodeType() == 1) {
                     if (index == count) {
                        node.getParentNode().removeChild(node);
                        break;
                     }

                     ++count;
                  }
               }
            }

            DefaultTreeModel model = (DefaultTreeModel)this.tree.getModel();
            MutableTreeNode mNode = (MutableTreeNode)path.getLastPathComponent();
            model.removeNodeFromParent(mNode);
            NodeList nl = (NodeList)xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

            for(i = 0; i < nl.getLength(); ++i) {
               node = nl.item(i);
               node.getParentNode().removeChild(node);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("MAE_RteGen.xml"));
            transformer.setOutputProperty("indent", "yes");
            transformer.transform(source, result);
            System.out.println("XML file updated successfully");
            this.updateData(path);
         } catch (Exception var17) {
            var17.printStackTrace();
         }

      }
   }

   private void DeleteComponent(TreePath path) {
      try {
         if (this.dBuilder == null) {
            System.out.println("Document Builder is NULL. pop");
            return;
         }

         XPath xp = XPathFactory.newInstance().newXPath();
         String componenFileXml = path.getPathComponent(1).toString();
         Document doc = this.dBuilder.parse(new File("MAE_RteGen.xml"));
         String pathComponent = "//SW_Cs/SW_C[@abbreviation='" + componenFileXml + "']";
         NodeList nodeList = (NodeList)xp.compile(pathComponent).evaluate(doc, XPathConstants.NODESET);

         for(int i = 0; i < nodeList.getLength(); ++i) {
            nodeList.item(i).getParentNode().removeChild(nodeList.item(i));
         }

         NodeList nl = (NodeList)xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

         for(int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            node.getParentNode().removeChild(node);
         }

         Transformer tr = TransformerFactory.newInstance().newTransformer();
         tr.setOutputProperty("indent", "yes");
         tr.transform(new DOMSource(doc), new StreamResult(new File("MAE_RteGen.xml")));
         DefaultTreeModel model = (DefaultTreeModel)this.tree.getModel();
         MutableTreeNode mNode = (MutableTreeNode)path.getLastPathComponent();
         model.removeNodeFromParent(mNode);
      } catch (Exception var11) {
         var11.printStackTrace();
      }

   }

   private void updateData(TreePath path) {
      this.readAllComp.populateJTable(path);
   }

   private void clientComponentName(int response, MouseEvent mE) {
      if (response == 0) {
         this.clientComponentNameOK(this.jEditTextBoth1.getText(), this.jEditTextBoth2.getText(), mE);
      } else {
         System.out.println("Cancelled");
      }

   }

   private void senderComponentName(int response, MouseEvent mE) {
      if (response == 0) {
         this.senderComponentNameOK(this.jEditTextBoth1.getText(), this.jEditTextBoth2.getText(), this.jEditTextBoth3.getText(), this.jEditTextBoth4.getText(), this.jEditTextBoth5.getText(), mE);
      } else {
         System.out.println("Cancelled");
      }

   }

   private void runnableComponentName(int response, MouseEvent mE) {
      if (response == 0) {
         this.runnableComponentNameOK(this.jEditTextBoth1.getText(), this.jEditTextBoth2.getText(), mE);
      } else {
         System.out.println("Cancelled");
      }

   }

   private void operationComponentName(int response, MouseEvent mE) {
      if (response == 0) {
         this.operationComponentNameOK(this.jEditTextBoth1.getText(), this.jEditTextBoth2.getText(), mE);
      } else {
         System.out.println("Cancelled");
      }

   }

   private void editComponentName(int response, MouseEvent mE) {
      if (response == 0) {
         System.out.println("GetText: " + this.jEditText.getText());
         this.editComponentNameOK(this.jEditText.getText(), mE);
      } else {
         System.out.println("Cancelled");
      }

   }

   private void editCyclicTime(int response, MouseEvent mE) {
      if (response == 0) {
         System.out.println("Cyclic-Time: " + this.comboBoxCyclic.getSelectedItem());
         this.changeCyclicTime(this.comboBoxCyclic.getSelectedItem().toString(), mE);
      } else {
         System.out.println("Cancelled");
      }

   }

   private void editInIt(int response, MouseEvent mE) {
      if (response == 0) {
         System.out.println("GetText: " + this.jEditText.getText());
         this.editInIt(this.jEditText.getText(), mE);
      } else {
         System.out.println("Cancelled");
      }

   }

   private void editCustom(int response, MouseEvent mE) {
      if (response == 0) {
         System.out.println("Edit Text 1: " + this.jEditTextBoth1.getText());
         System.out.println("Edit Text 2: " + this.jEditTextBoth2.getText());
         this.editCustom(this.jEditTextBoth1.getText(), this.jEditTextBoth2.getText(), mE);
      } else {
         System.out.println("Cancelled");
      }

   }

   private void clientComponentNameOK(String direction, String interfaceValue, MouseEvent mE) {
      this.xmlFile.clientComponentNameOK(direction, interfaceValue, mE, this.tree);
   }

   private void senderComponentNameOK(String baseType, String direction, String name, String size, String canId, MouseEvent mE) {
      this.xmlFile.senderComponentNameOK(baseType, direction, name, size, canId, mE, this.tree);
   }

   private void runnableComponentNameOK(String cycle, String name, MouseEvent mE) {
      this.xmlFile.runnableComponentNameOK(cycle, name, mE, this.tree);
   }

   private void operationComponentNameOK(String name, String returnType, MouseEvent mE) {
      this.xmlFile.operationComponentNameOK(name, returnType, mE, this.tree);
   }

   private void changeCyclicTime(String cyclicTyme, MouseEvent mE) {
      this.xmlFile.changeCyclicTime(cyclicTyme, mE, this.tree);
   }

   private void editComponentNameOK(String componentName, MouseEvent mE) {
      this.xmlFile.editComponentNameOK(componentName, mE, this.tree);
   }

   private void editInIt(String componentName, MouseEvent mE) {
      this.xmlFile.editInIt(componentName, mE, this.tree);
   }

   private void editCustom(String elementName, String elementValue, MouseEvent mE) {
      this.xmlFile.editCustom(elementName, elementValue, mE, this.tree);
   }
}
