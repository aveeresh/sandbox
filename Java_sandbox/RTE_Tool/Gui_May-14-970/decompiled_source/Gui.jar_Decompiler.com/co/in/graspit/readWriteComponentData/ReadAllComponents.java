package co.in.graspit.readWriteComponentData;

import java.io.File;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadAllComponents {
   private final JTable jtable;
   private DocumentBuilder builder;
   private Object[] columnNames = new Object[]{"Port-Type", "Function-Type", "Requirer-Component-Type", "Requirer-Component-Name", "Provider-Component-Type", "Provider-Component-Name", "SubFunction", "Return-Type", "ServerFunctionCall", "Argument"};
   private final String[] rte_sender = new String[]{"Rte_Read", "Rte_Write"};

   public ReadAllComponents(JTable datatable) throws ParserConfigurationException {
      this.jtable = datatable;
      this.createHeadingsRow();
      this.builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
   }

   public void populateJTable(TreePath treePath) {
      try {
         DefaultTableModel c = (DefaultTableModel)this.jtable.getModel();
         c.setColumnCount(0);
         c.addColumn(this.columnNames[0]);
         TableColumnModel columnModel = this.jtable.getColumnModel();

         for(int count = 0; count < columnModel.getColumnCount(); ++count) {
            columnModel.getColumn(count).setPreferredWidth(160);
         }

         File file = new File("MAE_RteGen.xml");
         if (!file.exists()) {
            System.out.println("File Can Not Found.");
            return;
         }

         Document doc = this.builder.parse(file);
         XPath xPath = XPathFactory.newInstance().newXPath();
         String expression = "/rtegen/SW_Cs[1]/SW_C[@abbreviation='" + treePath.getPathComponent(1).toString() + "']";
         NodeList nodeList = (NodeList)xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
         this.populateComponentData(nodeList, treePath);
      } catch (IOException | XPathExpressionException | SAXException var9) {
         var9.printStackTrace();
      }

   }

   public void showTableData(TreePath treePath, JTree tree) {
      try {
         this.createHeadingsRow();
         File file = new File("MAE_RteGen.xml");
         if (!file.exists()) {
            System.out.println("File Can Not Found.");
            return;
         }

         Document doc = this.builder.parse(file);
         XPath xPath = XPathFactory.newInstance().newXPath();
         String expression = "/rtegen/SW_Cs[1]/SW_C[@abbreviation='" + treePath.getPathComponent(1).toString() + "']/" + treePath.getPathComponent(2).toString();
         NodeList nodeList = (NodeList)xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
         String ex = "/rtegen/SW_Cs[1]/SW_C[@abbreviation='" + treePath.getPathComponent(1).toString() + "']";
         NodeList nList = (NodeList)xPath.compile(ex).evaluate(doc, XPathConstants.NODESET);
         this.tableDataComponent(nodeList, treePath, nList, tree);
      } catch (IOException | XPathExpressionException | SAXException var10) {
         var10.printStackTrace();
      }

   }

   void createHeadingsRow() {
      DefaultTableModel c = (DefaultTableModel)this.jtable.getModel();
      c.setColumnCount(0);
      Object[] var5;
      int var4 = (var5 = this.columnNames).length;

      int count;
      for(count = 0; count < var4; ++count) {
         Object colName = var5[count];
         c.addColumn(colName);
      }

      TableColumnModel columnModel = this.jtable.getColumnModel();

      for(count = 0; count < columnModel.getColumnCount(); ++count) {
         columnModel.getColumn(count).setPreferredWidth(160);
      }

   }

   public void tableDataComponent(NodeList nList, TreePath treePath, NodeList superList, JTree tree) {
      DefaultTableModel c = (DefaultTableModel)this.jtable.getModel();
      int totalRows = c.getRowCount();
      if (totalRows > 0) {
         for(int rows = totalRows - 1; rows >= 0; --rows) {
            c.removeRow(rows);
         }
      }

      DefaultMutableTreeNode multiNode = (DefaultMutableTreeNode)treePath.getPathComponent(1);
      int index = multiNode.getIndex((DefaultMutableTreeNode)treePath.getPathComponent(2));

      for(int i = 0; i < superList.getLength(); ++i) {
         NodeList node = superList.item(i).getChildNodes();
         int count = -1;

         for(int j = 0; j < node.getLength(); ++j) {
            Node n = node.item(j);
            if (n.getNodeType() == 1) {
               ++count;
               if (index == count) {
                  if (treePath.getLastPathComponent().toString().equals("ClientServer")) {
                     this.clientServerSub(n, superList);
                  } else if (treePath.getLastPathComponent().toString().equals("SenderReceiver")) {
                     this.senderReceiverSub(n, superList);
                  } else if (treePath.getLastPathComponent().toString().equals("Runnable")) {
                     System.out.println("Runneble Code Here: ");
                  } else {
                     System.out.println("NodeName: Not Match: ");
                  }
               }
            }
         }
      }

   }

   private void clientServerSub(Node node, NodeList superList) {
      NodeList opList = node.getChildNodes();

      for(int x = 0; x < opList.getLength(); ++x) {
         Node op = opList.item(x);
         if (op.getNodeType() == 1) {
            String[] rowDataList = new String[this.columnNames.length];

            for(int i = 0; i < this.columnNames.length; ++i) {
               if (i == 0) {
                  rowDataList[i] = node.getNodeName();
               } else if (i == 1) {
                  rowDataList[i] = "Rte_Call";
               } else if (i == 2) {
                  rowDataList[i] = "SW_Cs";
               } else if (i == 3) {
                  rowDataList[i] = superList.item(0).getAttributes().getNamedItem("abbreviation").getTextContent();
               } else if (i == 4) {
                  rowDataList[i] = "UBSW";
               } else if (i == 5) {
                  rowDataList[i] = "Mgr";
               } else if (i == 6) {
                  rowDataList[i] = node.getAttributes().getNamedItem("interface").getTextContent() + "_" + op.getAttributes().getNamedItem("name").getTextContent();
               } else if (i == 7) {
                  try {
                     rowDataList[i] = op.getAttributes().getNamedItem("return").getTextContent();
                  } catch (Exception var9) {
                     rowDataList[i] = "";
                  }
               } else if (i == 8) {
                  rowDataList[i] = op.getAttributes().getNamedItem("name").getTextContent();
               } else if (i == 9) {
                  rowDataList[i] = "Mgr";
               }
            }

            this.addRowToJtable(rowDataList);
         }
      }

   }

   private void senderReceiverSub(Node node, NodeList superList) {
      String[] rowDataList = new String[this.columnNames.length];

      for(int x = 0; x < this.rte_sender.length; ++x) {
         for(int i = 0; i < this.columnNames.length; ++i) {
            if (i == 0) {
               rowDataList[i] = node.getNodeName();
            } else if (i == 1) {
               rowDataList[i] = this.rte_sender[x];
            } else if (i == 2) {
               rowDataList[i] = "SW_Cs";
            } else if (i == 3) {
               rowDataList[i] = superList.item(0).getAttributes().getNamedItem("abbreviation").getTextContent();
            } else if (i == 4) {
               rowDataList[i] = "SW_Cs";
            } else if (i == 5) {
               rowDataList[i] = "CCM";
            } else if (i == 6) {
               rowDataList[i] = node.getAttributes().getNamedItem("name").getTextContent();
            } else if (i == 7) {
               rowDataList[i] = "";
            } else if (i == 8) {
               rowDataList[i] = "Rte_SWCS_CCM_" + node.getAttributes().getNamedItem("name").getTextContent();
            } else if (i == 9) {
               rowDataList[i] = node.getAttributes().getNamedItem("base_type").getTextContent() + node.getAttributes().getNamedItem("size").getTextContent() + "Byte_" + node.getAttributes().getNamedItem("name").getTextContent();
            }
         }

         this.addRowToJtable(rowDataList);
      }

   }

   public void populateComponentData(NodeList nList, TreePath treePath) {
      DefaultTableModel c = (DefaultTableModel)this.jtable.getModel();
      int totalRows = c.getRowCount();
      int totalports;
      if (totalRows > 0) {
         for(totalports = totalRows - 1; totalports >= 0; --totalports) {
            c.removeRow(totalports);
         }
      }

      totalports = nList.getLength();
      System.out.println("TotalPort: " + totalports);

      for(int i = 0; i < totalports; ++i) {
         Node tempElement = nList.item(i);
         if (tempElement.getNodeType() == 1) {
            NodeList clList = tempElement.getChildNodes();

            for(int j = 0; j < clList.getLength(); ++j) {
               Node cl = clList.item(j);
               if (cl.getNodeType() == 1) {
                  if (cl.getNodeName().equals("ClientServer")) {
                     System.out.println("NodeName: " + cl.getNodeName());
                     this.clientServer(cl);
                  } else if (cl.getNodeName().equals("SenderReceiver")) {
                     System.out.println("NodeName: " + cl.getNodeName());
                     this.senderReceiver(cl);
                  } else if (cl.getNodeName().equals("Runnable")) {
                     System.out.println("NodeName: " + cl.getNodeName());
                  } else {
                     System.out.println("NodeName: Not Match: " + cl.getNodeName());
                  }
               }
            }
         }
      }

   }

   private void clientServer(Node node) {
      NodeList opList = node.getChildNodes();

      for(int x = 0; x < opList.getLength(); ++x) {
         Node op = opList.item(x);
         if (op.getNodeType() == 1) {
            String[] rowDataList = new String[this.columnNames.length];

            for(int i = 0; i < this.columnNames.length; ++i) {
               if (i == 0) {
                  rowDataList[i] = node.getNodeName();
               }
            }

            this.addRowToJtable(rowDataList);
         }
      }

   }

   private void senderReceiver(Node node) {
      String[] rowDataList = new String[this.columnNames.length];

      for(int i = 0; i < this.columnNames.length; ++i) {
         if (i == 0) {
            rowDataList[i] = node.getNodeName();
         }
      }

      this.addRowToJtable(rowDataList);
   }

   void addRowToJtable(Object[] rowobject) {
      DefaultTableModel deftable = (DefaultTableModel)this.jtable.getModel();
      deftable.addRow(rowobject);
   }
}
