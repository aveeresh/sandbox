package co.in.graspit.RightClickMenu;

import co.in.graspit.GuiProject;
import co.in.graspit.mainPanels.PanelRTE;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RightClickPopMenuParameter extends JPopupMenu {
   private XPath xp = null;
   private DocumentBuilder builder = null;
   private final GuiProject frame;
   private ArrayList<JMenuItem> anItem = new ArrayList(1);

   public RightClickPopMenuParameter(GuiProject frame) {
      this.frame = frame;

      try {
         this.builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      } catch (ParserConfigurationException var3) {
         var3.printStackTrace();
      }

      this.xp = XPathFactory.newInstance().newXPath();
   }

   public void selectTableRow(MouseEvent mE) {
      Point point = mE.getPoint();
      JTable table = this.frame.getTableParameter();
      int currentRow = table.rowAtPoint(point);
      table.setRowSelectionInterval(currentRow, currentRow);
   }

   public void addClearButton(MouseEvent mE) {
      this.anItem.add(new JMenuItem("Delete"));
      int size = this.anItem.size() - 1;
      this.add((JMenuItem)this.anItem.get(size));
      ((JMenuItem)this.anItem.get(size)).addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            RightClickPopMenuParameter.this.deleteParameter();
         }
      });
   }

   private void deleteParameter() {
      System.out.println("Delete Clicked");
      JTable rteTable = ((PanelRTE)this.frame.getRTEPanel()).getMainTable();
      JTable table = this.frame.getTableParameter();
      DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
      int selectedRow = table.getSelectedRow();
      tableModel.removeRow(selectedRow);
      TreePath path = this.frame.getRTETree().getSelectionPath();
      DefaultMutableTreeNode multiNode = (DefaultMutableTreeNode)path.getPathComponent(1);
      int index = multiNode.getIndex((DefaultMutableTreeNode)path.getPathComponent(2));

      try {
         Document doc = this.builder.parse(new File("MAE_RteGen.xml"));
         String xPathSWCsPath = "//SW_Cs[1]/SW_C[@abbreviation='" + path.getPathComponent(1) + "']/" + path.getPathComponent(2);
         NodeList nodeList = (NodeList)this.xp.compile(xPathSWCsPath).evaluate(doc, XPathConstants.NODESET);
         int count = 0;

         for(int j = 0; j < nodeList.getLength(); ++j) {
            Node n = nodeList.item(j);
            if (n.getNodeType() == 1) {
               if (index == count) {
                  if (path.getLastPathComponent().toString().equals("ClientServer")) {
                     NodeList oprationList = ((Element)n).getElementsByTagName("Operation");

                     for(int k = 0; k < oprationList.getLength(); ++k) {
                        if (k == rteTable.getSelectedRow()) {
                           NodeList paraList = ((Element)oprationList.item(k)).getElementsByTagName("Parameter");

                           for(int x = 0; x < paraList.getLength(); ++x) {
                              if (x == selectedRow) {
                                 paraList.item(x).getParentNode().removeChild(paraList.item(x));
                              }
                           }
                        }
                     }
                  } else {
                     System.out.println("NodeName: Not Match: ");
                  }
               }

               ++count;
            }
         }

         NodeList nl = (NodeList)this.xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

         for(int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            node.getParentNode().removeChild(node);
         }

         Transformer transformer = TransformerFactory.newInstance().newTransformer();
         transformer.setOutputProperty("indent", "yes");
         transformer.transform(new DOMSource(doc), new StreamResult("MAE_RteGen.xml"));
      } catch (Exception var18) {
         var18.printStackTrace();
      }

   }
}
