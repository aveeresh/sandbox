package co.in.graspit.tools;

import co.in.graspit.GuiProject;
import co.in.graspit.mainPanels.PanelRTE;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WriteAndReadXMLDocFile {
   private static WriteAndReadXMLDocFile instance;
   private DocumentBuilder builder = null;
   private final XPath xp;
   private GuiProject frame = null;
   private final String[] finalAllElementName = new String[]{"Cyclic-Time", "COMPONENT_TYPE", "memory_configuration", "blocks", "InIt"};
   private final String[] memConfAttr = new String[]{"unit", "name", "head"};
   private final String[] memConfKey = new String[]{"name", "value", "remark", "description"};
   private final String[] blockAttrAttr = new String[]{"block", "name", "head"};
   private final String[] blockAttrKey = new String[]{"id", "name", "length", "block", "write_protection", "crc_type", "secure_block", "block_status", "block_modification", "block_filled_status"};
   private final String xmlMemPath = "source\\xml_files\\";
   private final String xmlBlockPath = "source\\xml_files\\";
   private ArrayList<DefaultMutableTreeNode> d_list = new ArrayList();
   private ArrayList<DefaultTreeModel> m_list = new ArrayList();

   private WriteAndReadXMLDocFile() {
      try {
         this.builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      } catch (ParserConfigurationException var2) {
         var2.printStackTrace();
      }

      this.xp = XPathFactory.newInstance().newXPath();
   }

   public static WriteAndReadXMLDocFile getInstance() {
      if (instance == null) {
         instance = new WriteAndReadXMLDocFile();
      }

      return instance;
   }

   public void setFrame(GuiProject frame) {
      this.frame = frame;
   }

   private String getAbsPath() {
      return (new File("")).getAbsolutePath() + "\\";
   }

   public void saveRTE(JTree TPanel, JTable jTable) {
      this.saveAsRTE((String)null, TPanel, jTable);
   }

   public void saveAsRTE(String absolutePath, JTree TPanel, JTable jTable) {
      if (absolutePath != null && absolutePath.toLowerCase().contains(".xml")) {
         absolutePath = absolutePath.replace(".xml", "");
         absolutePath = absolutePath.replace(".Xml", "");
         absolutePath = absolutePath.replace(".xMl", "");
         absolutePath = absolutePath.replace(".xmL", "");
         absolutePath = absolutePath.replace(".XML", "");
         absolutePath = absolutePath.replace(".XMl", "");
         absolutePath = absolutePath.replace(".XmL", "");
         absolutePath = absolutePath.replace(".xML", "");
      }

      TreeSelectionModel value = TPanel.getSelectionModel();
      boolean isNull = false;
      if (value != null) {
         TreePath treepath = value.getSelectionPath();
         if (treepath != null && treepath.getPathCount() > 1) {
            String docName = treepath.getPathComponent(1).toString();
            if (absolutePath == null) {
               absolutePath = docName;
               isNull = true;
            }

            try {
               DefaultTableModel tModel = (DefaultTableModel)jTable.getModel();
               Document doc = this.builder.parse(new File(docName + ".xml"));
               NodeList nodeList = doc.getElementsByTagName(doc.getDocumentElement().getNodeName());

               NodeList superList;
               int portCount;
               for(int i = 0; i < nodeList.getLength(); ++i) {
                  if (nodeList.item(i).getNodeType() == 1) {
                     superList = nodeList.item(i).getChildNodes();
                     portCount = 0;

                     for(int j = 0; j < superList.getLength(); ++j) {
                        if (superList.item(j).getNodeType() == 1 && superList.item(j).getNodeName().equals("Port")) {
                           NodeList portList = superList.item(j).getChildNodes();

                           for(int k = 0; k < portList.getLength(); ++k) {
                              if (portList.item(k).getNodeType() == 1) {
                                 for(int colCount = 0; colCount < tModel.getColumnCount(); ++colCount) {
                                    if (portList.item(k).getNodeName().equals(tModel.getColumnName(colCount))) {
                                       portList.item(k).setTextContent(tModel.getValueAt(portCount, colCount).toString());
                                    }
                                 }
                              }
                           }

                           ++portCount;
                        }
                     }
                  }
               }

               XPath xp = XPathFactory.newInstance().newXPath();
               superList = (NodeList)xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

               for(portCount = 0; portCount < superList.getLength(); ++portCount) {
                  Node node = superList.item(portCount);
                  node.getParentNode().removeChild(node);
               }

               Transformer tr = TransformerFactory.newInstance().newTransformer();
               tr.setOutputProperty("indent", "yes");
               tr.transform(new DOMSource(doc), new StreamResult(new File(absolutePath + ".xml")));
               if (this.frame != null) {
                  if (isNull) {
                     this.frame.setLogDataShow(this.getAbsPath() + absolutePath + ".xml file Save Successfully.");
                  } else {
                     this.frame.setLogDataShow(absolutePath + ".xml file Save As Successfully.");
                  }
               } else {
                  System.out.println("xmlDoc: Frame is Null.");
               }
            } catch (Exception var17) {
               var17.printStackTrace();
            }

         }
      }
   }

   public void saveNVMMem(JTable jTable, ArrayList<String> discription_table_jp1) {
      this.saveAsNVMMem((String)null, jTable, discription_table_jp1);
   }

   public void saveAsNVMMem(String absolutePath, JTable jTable, ArrayList<String> discription_table_jp1) {
      System.out.println("Save as file: " + absolutePath);
      if (absolutePath != null && absolutePath.toLowerCase().contains(".xml")) {
         absolutePath = absolutePath.replace(".xml", "");
         absolutePath = absolutePath.replace(".Xml", "");
         absolutePath = absolutePath.replace(".xMl", "");
         absolutePath = absolutePath.replace(".xmL", "");
         absolutePath = absolutePath.replace(".XML", "");
         absolutePath = absolutePath.replace(".XMl", "");
         absolutePath = absolutePath.replace(".XmL", "");
         absolutePath = absolutePath.replace(".xML", "");
      }

      boolean isNull = false;
      String docName = this.finalAllElementName[2];
      if (absolutePath == null) {
         absolutePath = "source\\xml_files\\" + docName;
         isNull = true;
      }

      try {
         Document dom = this.builder.newDocument();
         Element root = dom.createElement(docName);
         dom.appendChild(root);
         this.addSubDataNVMMem(dom, root, jTable, discription_table_jp1);
         Transformer tr = TransformerFactory.newInstance().newTransformer();
         tr.setOutputProperty("indent", "yes");
         tr.transform(new DOMSource(dom), new StreamResult(new File(absolutePath + ".xml")));
         if (this.frame != null) {
            if (isNull) {
               this.frame.setLogDataShow(this.getAbsPath() + absolutePath + ".xml file Save Successfully.");
            } else {
               this.frame.setLogDataShow(absolutePath + ".xml file Save As Successfully.");
            }
         } else {
            System.out.println("xmlDoc: Frame is Null.");
         }
      } catch (TransformerException | TransformerFactoryConfigurationError var9) {
         var9.printStackTrace();
      }

   }

   private void addSubDataNVMMem(Document dom, Element root, JTable jTable, ArrayList<String> discription_table_jp1) {
      DefaultTableModel defTable = (DefaultTableModel)jTable.getModel();
      Element unit = dom.createElement(this.memConfAttr[0]);
      unit.setAttribute(this.memConfAttr[1], this.memConfAttr[2]);
      root.appendChild(unit);

      Element subPort;
      for(int i = 0; i < defTable.getColumnCount(); ++i) {
         subPort = dom.createElement(this.memConfKey[i]);
         subPort.setTextContent(defTable.getColumnName(i));
         unit.appendChild(subPort);
      }

      Element unit = dom.createElement(this.memConfKey[3]);
      unit.setTextContent("Description");
      unit.appendChild(unit);

      for(int i = 0; i < defTable.getRowCount(); ++i) {
         unit = dom.createElement(this.memConfAttr[0]);
         unit.setAttribute(this.memConfAttr[1], String.valueOf(i));
         root.appendChild(unit);

         for(int j = 0; j < defTable.getColumnCount(); ++j) {
            Element subPort = dom.createElement(this.memConfKey[j]);
            subPort.setTextContent((String)defTable.getValueAt(i, j));
            unit.appendChild(subPort);
         }

         subPort = dom.createElement(this.memConfKey[3]);
         subPort.setTextContent((String)discription_table_jp1.get(i));
         unit.appendChild(subPort);
      }

   }

   public void saveNVMBlock(JTable jTable) {
      this.saveAsNVMBlock((String)null, jTable);
   }

   public void saveAsNVMBlock(String absolutePath, JTable jTable) {
      System.out.println("Save as file: " + absolutePath);
      if (absolutePath != null && absolutePath.toLowerCase().contains(".xml")) {
         absolutePath = absolutePath.replace(".xml", "");
         absolutePath = absolutePath.replace(".Xml", "");
         absolutePath = absolutePath.replace(".xMl", "");
         absolutePath = absolutePath.replace(".xmL", "");
         absolutePath = absolutePath.replace(".XML", "");
         absolutePath = absolutePath.replace(".XMl", "");
         absolutePath = absolutePath.replace(".XmL", "");
         absolutePath = absolutePath.replace(".xML", "");
      }

      boolean isNull = false;
      String docName = this.finalAllElementName[3];
      if (absolutePath == null) {
         absolutePath = "source\\xml_files\\block_attributes";
         isNull = true;
      }

      try {
         Document dom = this.builder.newDocument();
         Element root = dom.createElement(docName);
         dom.appendChild(root);
         this.addSubDataNVMBlock(dom, root, jTable);
         Transformer tr = TransformerFactory.newInstance().newTransformer();
         tr.setOutputProperty("indent", "yes");
         tr.transform(new DOMSource(dom), new StreamResult(new File(absolutePath + ".xml")));
         if (this.frame != null) {
            if (isNull) {
               this.frame.setLogDataShow(this.getAbsPath() + absolutePath + ".xml file Save Successfully.");
            } else {
               this.frame.setLogDataShow(absolutePath + ".xml file Save As Successfully.");
            }
         } else {
            System.out.println("xmlDoc: Frame is Null.");
         }
      } catch (TransformerException | TransformerFactoryConfigurationError var8) {
         var8.printStackTrace();
      }

   }

   private void addSubDataNVMBlock(Document dom, Element root, JTable jTable) {
      DefaultTableModel defTable = (DefaultTableModel)jTable.getModel();
      Element unit = dom.createElement(this.blockAttrAttr[0]);
      unit.setAttribute(this.blockAttrAttr[1], this.blockAttrAttr[2]);
      root.appendChild(unit);

      for(int i = 0; i < defTable.getColumnCount(); ++i) {
         Element subPort = dom.createElement(this.blockAttrKey[i]);
         subPort.setTextContent(defTable.getColumnName(i));
         unit.appendChild(subPort);
      }

      for(int i = 0; i < defTable.getRowCount(); ++i) {
         Element unit = dom.createElement(this.blockAttrAttr[0]);
         unit.setAttribute(this.blockAttrAttr[1], String.valueOf(i));
         root.appendChild(unit);

         for(int j = 0; j < defTable.getColumnCount(); ++j) {
            Element subPort = dom.createElement(this.blockAttrKey[j]);
            subPort.setTextContent((String)defTable.getValueAt(i, j));
            unit.appendChild(subPort);
         }
      }

   }

   public void clientComponentNameOK(String direction, String interfaceValue, MouseEvent mE, JTree tree) {
      try {
         if (direction.trim().equals("")) {
            this.writeLog("Direction is Empty.");
            return;
         }

         if (interfaceValue.trim().equals("")) {
            this.writeLog("Interface is Empty.");
            return;
         }

         Document doc = this.builder.parse(new File("MAE_RteGen.xml"));
         TreePath treePath = tree.getPathForLocation(mE.getX(), mE.getY());
         String path = "//SW_Cs[1]/SW_C[@abbreviation='" + treePath.getPathComponent(1) + "']";
         NodeList nodeList = (NodeList)this.xp.compile(path).evaluate(doc, XPathConstants.NODESET);
         Element element = (Element)nodeList.item(0);
         Element newElement = doc.createElement("ClientServer");
         element.appendChild(newElement);
         newElement.setAttribute("dir", direction);
         newElement.setAttribute("interface", interfaceValue);
         NodeList nl = (NodeList)this.xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

         for(int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            node.getParentNode().removeChild(node);
         }

         Transformer tr = TransformerFactory.newInstance().newTransformer();
         tr.setOutputProperty("indent", "yes");
         tr.transform(new DOMSource(doc), new StreamResult(new File("MAE_RteGen.xml")));
         this.setTreeData(tree);
      } catch (Exception var14) {
         var14.printStackTrace();
      }

   }

   public void senderComponentNameOK(String baseType, String direction, String name, String size, String canId, MouseEvent mE, JTree tree) {
      try {
         if (baseType.trim().equals("")) {
            this.writeLog("Base Type is Empty.");
            return;
         }

         if (direction.trim().equals("")) {
            this.writeLog("Direction is Empty.");
            return;
         }

         if (name.trim().equals("")) {
            this.writeLog("Name is Empty.");
            return;
         }

         if (size.trim().equals("")) {
            this.writeLog("Size is Empty.");
            return;
         }

         Document doc = this.builder.parse(new File("MAE_RteGen.xml"));
         TreePath treePath = tree.getPathForLocation(mE.getX(), mE.getY());
         String path = "//SW_Cs[1]/SW_C[@abbreviation='" + treePath.getPathComponent(1) + "']";
         NodeList nodeList = (NodeList)this.xp.compile(path).evaluate(doc, XPathConstants.NODESET);
         Element element = (Element)nodeList.item(0);
         Element newElement = doc.createElement("SenderReceiver");
         element.appendChild(newElement);
         if (!canId.trim().equals("")) {
            newElement.setAttribute("CAN_ID", canId);
         }

         newElement.setAttribute("base_type", baseType);
         newElement.setAttribute("dir", direction);
         newElement.setAttribute("name", name);
         newElement.setAttribute("size", size);
         NodeList nl = (NodeList)this.xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

         for(int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            node.getParentNode().removeChild(node);
         }

         Transformer tr = TransformerFactory.newInstance().newTransformer();
         tr.setOutputProperty("indent", "yes");
         tr.transform(new DOMSource(doc), new StreamResult(new File("MAE_RteGen.xml")));
         this.setTreeData(tree);
      } catch (Exception var17) {
         var17.printStackTrace();
      }

   }

   public void runnableComponentNameOK(String cycle, String name, MouseEvent mE, JTree tree) {
      try {
         if (cycle.trim().equals("")) {
            this.writeLog("Cycle is Empty.");
            return;
         }

         if (name.trim().equals("")) {
            this.writeLog("Name is Empty.");
            return;
         }

         Document doc = this.builder.parse(new File("MAE_RteGen.xml"));
         TreePath treePath = tree.getPathForLocation(mE.getX(), mE.getY());
         String path = "//SW_Cs[1]/SW_C[@abbreviation='" + treePath.getPathComponent(1) + "']";
         NodeList nodeList = (NodeList)this.xp.compile(path).evaluate(doc, XPathConstants.NODESET);
         Element element = (Element)nodeList.item(0);
         Element newElement = doc.createElement("Runnable");
         element.appendChild(newElement);
         newElement.setAttribute("cycle", cycle);
         newElement.setAttribute("name", name);
         NodeList nl = (NodeList)this.xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

         for(int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            node.getParentNode().removeChild(node);
         }

         Transformer tr = TransformerFactory.newInstance().newTransformer();
         tr.setOutputProperty("indent", "yes");
         tr.transform(new DOMSource(doc), new StreamResult(new File("MAE_RteGen.xml")));
         this.setTreeData(tree);
      } catch (Exception var14) {
         var14.printStackTrace();
      }

   }

   public void operationComponentNameOK(String name, String returnType, MouseEvent mE, JTree tree) {
      try {
         if (name.trim().equals("")) {
            this.writeLog("name is Empty.");
            return;
         }

         Document doc = this.builder.parse(new File("MAE_RteGen.xml"));
         TreePath treePath = tree.getPathForLocation(mE.getX(), mE.getY());
         String path = "//SW_Cs[1]/SW_C[@abbreviation='" + treePath.getPathComponent(1) + "']";
         NodeList nodeList = (NodeList)this.xp.compile(path).evaluate(doc, XPathConstants.NODESET);
         DefaultMutableTreeNode multiNode = (DefaultMutableTreeNode)treePath.getPathComponent(1);
         int index = multiNode.getIndex((DefaultMutableTreeNode)treePath.getPathComponent(2));

         for(int i = 0; i < nodeList.getLength(); ++i) {
            NodeList subList = nodeList.item(i).getChildNodes();
            int count = 0;

            for(int j = 0; j < subList.getLength(); ++j) {
               Node node = subList.item(j);
               if (node.getNodeType() == 1) {
                  System.out.println("NodeName: " + node.getNodeName());
                  if (index == count) {
                     Element ele = (Element)node;
                     Element newElement = doc.createElement("Operation");
                     ele.appendChild(newElement);
                     newElement.setAttribute("name", name);
                     if (!returnType.trim().equals("")) {
                        newElement.setAttribute("return", returnType);
                     }

                     System.out.println("Match: ");
                  }

                  ++count;
               }
            }
         }

         NodeList nl = (NodeList)this.xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

         for(int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            node.getParentNode().removeChild(node);
         }

         Transformer tr = TransformerFactory.newInstance().newTransformer();
         tr.setOutputProperty("indent", "yes");
         tr.transform(new DOMSource(doc), new StreamResult(new File("MAE_RteGen.xml")));
         this.setTreeData(tree);
      } catch (Exception var18) {
         var18.printStackTrace();
      }

   }

   public void editComponentNameOK(String componentName, MouseEvent mE, JTree tree) {
      try {
         if (componentName.toLowerCase().contains(".xml")) {
            componentName = componentName.replace(".xml", "");
            componentName = componentName.replace(".Xml", "");
            componentName = componentName.replace(".xMl", "");
            componentName = componentName.replace(".xmL", "");
            componentName = componentName.replace(".XML", "");
            componentName = componentName.replace(".XMl", "");
            componentName = componentName.replace(".XmL", "");
            componentName = componentName.replace(".xML", "");
         }

         boolean isFileExist = (new File(componentName + ".xml")).exists();
         if (!isFileExist) {
            TreePath path = tree.getPathForLocation(mE.getX(), mE.getY());
            String componenFileXml = path.getPathComponent(1).toString();
            String filePath = componenFileXml + ".xml";
            File xmlFile = new File(filePath);
            Document compBuilder = this.builder.parse(new File("componentList.xml"));
            compBuilder.getDocumentElement().normalize();
            Document doc = this.builder.parse(xmlFile);
            Element element = doc.getDocumentElement();
            Element element2 = doc.createElement(componentName);
            NamedNodeMap attrs = element.getAttributes();

            for(int i = 0; i < attrs.getLength(); ++i) {
               Attr attr2 = (Attr)doc.importNode(attrs.item(i), true);
               element2.getAttributes().setNamedItem(attr2);
            }

            while(element.hasChildNodes()) {
               element2.appendChild(element.getFirstChild());
            }

            element.getParentNode().replaceChild(element2, element);
            XPath xp = XPathFactory.newInstance().newXPath();
            NodeList nl = (NodeList)xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

            for(int i = 0; i < nl.getLength(); ++i) {
               Node node = nl.item(i);
               node.getParentNode().removeChild(node);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(componentName + ".xml"));
            xmlFile.delete();
            NodeList nodeListCore = compBuilder.getElementsByTagName(compBuilder.getDocumentElement().getNodeName());

            for(int i = 0; i < nodeListCore.getLength(); ++i) {
               if (nodeListCore.item(i).getAttributes().getNamedItem("UUID") != null) {
                  NodeList firstNodeList = nodeListCore.item(i).getChildNodes();

                  for(int j = 0; j < firstNodeList.getLength(); ++j) {
                     if (1 == firstNodeList.item(j).getNodeType()) {
                        NodeList SubNodeList = firstNodeList.item(j).getChildNodes();

                        for(int k = 0; k < SubNodeList.getLength(); ++k) {
                           if (1 == SubNodeList.item(k).getNodeType()) {
                              System.out.println("Type :" + k + " " + SubNodeList.item(k).getTextContent());
                              if (SubNodeList.item(k).getTextContent().equals(componenFileXml)) {
                                 SubNodeList.item(k).setTextContent(componentName);
                              }
                           }
                        }
                     }
                  }
               }
            }

            compBuilder.getDocumentElement().normalize();
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty("indent", "no");
            tr.transform(new DOMSource(compBuilder), new StreamResult(new File("componentList.xml")));
            MutableTreeNode mNode = (MutableTreeNode)path.getLastPathComponent();
            mNode.setUserObject(componentName);
         } else {
            System.out.println("File Already Exist.");
         }
      } catch (Exception var23) {
         var23.printStackTrace();
      }

   }

   public void changeCyclicTime(String cyclicTime, MouseEvent mE, JTree tree) {
      try {
         TreePath path = tree.getPathForLocation(mE.getX(), mE.getY());
         String componenFileXml = path.getPathComponent(1).toString();
         String filePath = componenFileXml + ".xml";
         Document doc = this.builder.parse(new File(filePath));
         if (doc.getElementsByTagName(this.finalAllElementName[0]).item(0) != null) {
            doc.getElementsByTagName(this.finalAllElementName[0]).item(0).getChildNodes().item(0).setTextContent(cyclicTime);
         } else {
            Element root = doc.getDocumentElement();
            Element inItEle = doc.createElement(this.finalAllElementName[0]);
            inItEle.setTextContent(cyclicTime);
            root.appendChild(inItEle);
         }

         XPath xp = XPathFactory.newInstance().newXPath();
         NodeList nl = (NodeList)xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

         for(int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            node.getParentNode().removeChild(node);
         }

         Transformer transformer = TransformerFactory.newInstance().newTransformer();
         transformer.setOutputProperty("indent", "yes");
         transformer.transform(new DOMSource(doc), new StreamResult(componenFileXml + ".xml"));
      } catch (Exception var12) {
         var12.printStackTrace();
      }

   }

   public void editInIt(String inItVal, MouseEvent mE, JTree tree) {
      try {
         TreePath path = tree.getPathForLocation(mE.getX(), mE.getY());
         String componenFileXml = path.getPathComponent(1).toString();
         String filePath = componenFileXml + ".xml";
         Document doc = this.builder.parse(new File(filePath));
         if (doc.getElementsByTagName(this.finalAllElementName[4]).item(0) != null) {
            doc.getElementsByTagName(this.finalAllElementName[4]).item(0).getChildNodes().item(0).setTextContent(inItVal);
         } else {
            Element root = doc.getDocumentElement();
            Element inItEle = doc.createElement(this.finalAllElementName[4]);
            inItEle.setTextContent(inItVal);
            root.appendChild(inItEle);
         }

         XPath xp = XPathFactory.newInstance().newXPath();
         NodeList nl = (NodeList)xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

         for(int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            node.getParentNode().removeChild(node);
         }

         Transformer transformer = TransformerFactory.newInstance().newTransformer();
         transformer.setOutputProperty("indent", "yes");
         transformer.transform(new DOMSource(doc), new StreamResult(componenFileXml + ".xml"));
      } catch (Exception var12) {
         var12.printStackTrace();
      }

   }

   public void editCustom(String elementName, String elementValue, MouseEvent mE, JTree tree) {
      try {
         elementName = elementName.replace(" ", "_");
         TreePath path = tree.getPathForLocation(mE.getX(), mE.getY());
         String componenFileXml = path.getPathComponent(1).toString();
         String filePath = componenFileXml + ".xml";
         Document doc = this.builder.parse(new File(filePath));
         if (doc.getElementsByTagName(elementName).item(0) != null) {
            doc.getElementsByTagName(elementName).item(0).getChildNodes().item(0).setTextContent(elementValue);
         } else {
            Element root = doc.getDocumentElement();
            Element inItEle = doc.createElement(elementName);
            inItEle.setTextContent(elementValue);
            root.appendChild(inItEle);
         }

         XPath xp = XPathFactory.newInstance().newXPath();
         NodeList nl = (NodeList)xp.evaluate("//text()[normalize-space(.)='']", doc, XPathConstants.NODESET);

         for(int i = 0; i < nl.getLength(); ++i) {
            Node node = nl.item(i);
            node.getParentNode().removeChild(node);
         }

         Transformer transformer = TransformerFactory.newInstance().newTransformer();
         transformer.setOutputProperty("indent", "yes");
         transformer.transform(new DOMSource(doc), new StreamResult(componenFileXml + ".xml"));
      } catch (Exception var13) {
         var13.printStackTrace();
      }

   }

   public void exportRTEFilesXML(JTree tree, String cpPath) {
      try {
         String fileName = "MAE_RteGen";
         File file = new File(fileName + ".xml");
         if (file.exists()) {
            File destFile = new File(cpPath + "\\" + fileName + ".xml");
            InputStream is = null;
            FileOutputStream os = null;

            try {
               is = new FileInputStream(file);
               os = new FileOutputStream(destFile);
               byte[] buffer = new byte[1024];

               int length;
               while((length = is.read(buffer)) > 0) {
                  os.write(buffer, 0, length);
               }
            } finally {
               is.close();
               os.close();
            }

            this.frame.setLogDataShow("Export File Path: " + cpPath + "\\" + fileName + ".xml");
         }
      } catch (Exception var14) {
         var14.printStackTrace();
      }

   }

   public void importFilesToCurrentLocation(JTree tree, String filePath, String fileName) {
      try {
         Document docToImport = this.builder.parse(new File(filePath));
         XPath xPath = XPathFactory.newInstance().newXPath();
         String xPathSWCsPath = "//SW_Cs[1]";
         NodeList nodeList = (NodeList)xPath.compile(xPathSWCsPath).evaluate(docToImport, XPathConstants.NODESET);
         if (nodeList.getLength() <= 0) {
            this.frame.setLogDataShow("Cant Read This File.");
            return;
         }

         File toDelete = new File("MAE_RteGen.xml");
         if (toDelete.exists()) {
            toDelete.delete();
         }

         File file = new File(filePath);
         if (file.exists()) {
            File destFile = new File("MAE_RteGen.xml");
            InputStream is = null;
            FileOutputStream os = null;

            try {
               is = new FileInputStream(file);
               os = new FileOutputStream(destFile);
               byte[] buffer = new byte[1024];

               int length;
               while((length = is.read(buffer)) > 0) {
                  os.write(buffer, 0, length);
               }
            } finally {
               is.close();
               os.close();
            }

            this.frame.setLogDataShow("Import File Path: " + filePath);
         }
      } catch (Exception var19) {
         var19.printStackTrace();
      }

   }

   public void setTreeData(JTree tree) {
      try {
         Document docCompList = this.builder.parse(new File("MAE_RteGen.xml"));
         NodeList nList = docCompList.getElementsByTagName(docCompList.getDocumentElement().getNodeName());
         int count = 0;

         for(int temp = 0; temp < nList.getLength(); ++temp) {
            Node node = nList.item(temp);
            if (node.getNodeType() == 1) {
               Element eElement = (Element)node;
               this.d_list.add(new DefaultMutableTreeNode("COMPONENTS", eElement) {
                  {
                     NodeList nListRoot = var3.getChildNodes();

                     for(int i = 0; i < nListRoot.getLength(); ++i) {
                        Node rootNode = nListRoot.item(i);
                        if (rootNode.getNodeType() == 1 && rootNode.getNodeName().equals("SW_Cs")) {
                           NodeList swcsList = rootNode.getChildNodes();

                           for(int j = 0; j < swcsList.getLength(); ++j) {
                              Node swcs = swcsList.item(j);
                              if (swcs.getNodeType() == 1) {
                                 String name = swcs.getAttributes().getNamedItem("abbreviation").getTextContent();
                                 DefaultMutableTreeNode node_1 = new DefaultMutableTreeNode(name);
                                 NodeList dataList = swcs.getChildNodes();

                                 for(int k = 0; k < dataList.getLength(); ++k) {
                                    Node data = dataList.item(k);
                                    if (data.getNodeType() == 1) {
                                       node_1.add(new DefaultMutableTreeNode(data.getNodeName()));
                                    }
                                 }

                                 this.add(node_1);
                              }
                           }
                        }
                     }

                  }
               });
               this.m_list.add(new DefaultTreeModel((TreeNode)this.d_list.get(count)));
               tree.setModel((TreeModel)this.m_list.get(count));
               ++count;
            }
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }

   }

   public void changeTableDataParameter(JTable table, TreePath treePath) {
      try {
         DefaultTableModel model = (DefaultTableModel)this.frame.getTableParameter().getModel();
         model.setRowCount(0);
         if (treePath == null) {
            System.out.println("treePath NULL.");
            return;
         }

         if (treePath.getPathCount() <= 2) {
            System.out.println("treepath Count: " + treePath.getPathCount() + " Need 3");
            return;
         }

         if (!treePath.getPathComponent(2).toString().equals("ClientServer")) {
            System.out.println("ClientServer NULL.");
            return;
         }

         ArrayList<String[]> arrayList = new ArrayList();
         Document doc = this.builder.parse(new File("MAE_RteGen.xml"));
         DefaultMutableTreeNode multiNode = (DefaultMutableTreeNode)treePath.getPathComponent(1);
         int index = multiNode.getIndex((DefaultMutableTreeNode)treePath.getPathComponent(2));
         String xPathSWCsPath = "//SW_Cs[1]/SW_C[@abbreviation='" + treePath.getPathComponent(1) + "']/" + treePath.getPathComponent(2);
         NodeList nodeList = (NodeList)this.xp.compile(xPathSWCsPath).evaluate(doc, XPathConstants.NODESET);
         int count = 0;

         for(int j = 0; j < nodeList.getLength(); ++j) {
            Node n = nodeList.item(j);
            if (n.getNodeType() == 1) {
               if (index == count) {
                  if (treePath.getLastPathComponent().toString().equals("ClientServer")) {
                     NodeList oprationList = ((Element)n).getElementsByTagName("Operation");

                     for(int k = 0; k < oprationList.getLength(); ++k) {
                        if (k == table.getSelectedRow()) {
                           NodeList paraList = ((Element)oprationList.item(k)).getElementsByTagName("Parameter");

                           for(int x = 0; x < paraList.getLength(); ++x) {
                              String[] paraListString = new String[3];
                              NamedNodeMap att = paraList.item(x).getAttributes();
                              paraListString[0] = att.getNamedItem("type").getTextContent() + "_" + att.getNamedItem("name").getTextContent();
                              paraListString[1] = att.getNamedItem("direction").getTextContent();
                              paraListString[2] = att.getNamedItem("type").getTextContent();
                              arrayList.add(paraListString);
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

         if (arrayList.size() > 0) {
            Iterator var21 = arrayList.iterator();

            while(var21.hasNext()) {
               String[] s = (String[])var21.next();
               model.addRow(s);
            }
         }
      } catch (Exception var19) {
         var19.printStackTrace();
      }

   }

   public void changeInXmlFileForParameter(TreePath treepath, String parameter, String direction, String type) {
      JTable rteTable = ((PanelRTE)this.frame.getRTEPanel()).getMainTable();
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
                           Element ele = (Element)oprationList.item(k);
                           System.out.println(ele.getNodeName());
                           Element paraEle = doc.createElement("Parameter");
                           ele.appendChild(paraEle);
                           paraEle.setAttribute("name", parameter);
                           paraEle.setAttribute("direction", direction);
                           paraEle.setAttribute("type", type);
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
      } catch (Exception var19) {
         var19.printStackTrace();
      }

   }

   public void writeLog(String log) {
      if (this.frame != null) {
         this.frame.setLogDataShow(log);
      }
   }
}
