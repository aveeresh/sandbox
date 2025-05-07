package co.in.graspit.mainPanels;

import co.in.graspit.GuiProject;
import co.in.graspit.readWriteComponentData.GenerateNVMFileDotH;
import co.in.graspit.tables.BlockAttributesTable;
import co.in.graspit.tables.MemoryConfigurationTable;
import co.in.graspit.tools.ConstantDataPanelsIndex;
import co.in.graspit.tools.WriteAndReadXMLDocFile;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PanelNVM extends JPanel {
   private final ConstantDataPanelsIndex pic = ConstantDataPanelsIndex.getInstance();
   private final WriteAndReadXMLDocFile xmlDoc = WriteAndReadXMLDocFile.getInstance();
   private final GenerateNVMFileDotH dotHFile = GenerateNVMFileDotH.getInstance();
   private final JTabbedPane tabbedPanel;
   private final JPanel panelMemoryConfig;
   private final JPanel panelBlockAttributes;
   private final JTable nvmTable;
   private final JTable blockTable;
   private final JScrollPane scrollPanelNVMTable;
   private final JScrollPane scrollPanelBlockTable;
   private final ArrayList<String> header_table_jp1 = new ArrayList();
   private final ArrayList<ArrayList<String>> body_table_jp1 = new ArrayList();
   private final ArrayList<String> discription_table_jp1 = new ArrayList();
   private final ArrayList<String> header_table_jp2 = new ArrayList();
   private final ArrayList<ArrayList<String>> body_table_jp2 = new ArrayList();
   private final String memoryConfXMLFilePath = "source\\xml_files\\memory_configuration.xml";
   private final String rteFileXML = "MAE_RteGen.xml";
   private GuiProject frame;

   public PanelNVM() {
      this.setBorder(new MatteBorder(2, 2, 2, 0, new Color(244, 247, 252)));
      this.setBackground(Color.WHITE);
      this.setLayout(new CardLayout(0, 0));
      this.tabbedPanel = new JTabbedPane(1);
      this.tabbedPanel.setBorder((Border)null);
      this.tabbedPanel.setBackground(Color.WHITE);
      this.add(this.tabbedPanel, "name_81132252002500");
      this.panelMemoryConfig = new JPanel();
      this.panelMemoryConfig.setBorder((Border)null);
      this.panelMemoryConfig.setBackground(Color.WHITE);
      this.tabbedPanel.addTab("Memory Configuration", (Icon)null, this.panelMemoryConfig, (String)null);
      this.panelMemoryConfig.setLayout(new BorderLayout(0, 0));
      JTabbedPane var10000 = this.tabbedPanel;
      this.pic.getClass();
      var10000.setSelectedIndex(0);
      this.nvmTable = new MemoryConfigurationTable();
      this.setTableData();
      this.scrollPanelNVMTable = new JScrollPane(this.nvmTable);
      this.scrollPanelNVMTable.getViewport().setBackground(Color.WHITE);
      this.panelMemoryConfig.add(this.scrollPanelNVMTable, "West");
      this.panelBlockAttributes = new JPanel();
      this.panelBlockAttributes.setBorder((Border)null);
      this.panelBlockAttributes.setBackground(Color.WHITE);
      this.tabbedPanel.addTab("Block Attributes", (Icon)null, this.panelBlockAttributes, (String)null);
      this.panelBlockAttributes.setLayout(new CardLayout(0, 0));
      this.blockTable = new BlockAttributesTable();
      this.blockTable.setAutoResizeMode(0);
      this.setTableHeaderAndBody();
      this.scrollPanelBlockTable = new JScrollPane(this.blockTable);
      this.scrollPanelBlockTable.getViewport().setBackground(Color.WHITE);
      this.panelBlockAttributes.add(this.scrollPanelBlockTable, "name_159894747216400");
   }

   public void setGui(GuiProject frame) {
      this.frame = frame;
   }

   private void setTableData() {
      this.setTableHeaderAndBodyMemoryConf();
   }

   private void setTableHeaderAndBodyMemoryConf() {
      this.readMemoryConfXML();
      this.nvmTable.setRowHeight(20);
      ((MemoryConfigurationTable)this.nvmTable).addDiscriptionArray(this.discription_table_jp1);
      DefaultTableModel tModel = (DefaultTableModel)this.nvmTable.getModel();
      Iterator var3 = this.header_table_jp1.iterator();

      while(var3.hasNext()) {
         Object colName = var3.next();
         tModel.addColumn(colName);
      }

      if (this.body_table_jp1.size() >= 1 && ((ArrayList)this.body_table_jp1.get(0)).size() >= 1) {
         Object[][] obj = new Object[this.body_table_jp1.size()][((ArrayList)this.body_table_jp1.get(0)).size()];

         for(int i = 0; i < this.body_table_jp1.size(); ++i) {
            for(int j = 0; j < ((ArrayList)this.body_table_jp1.get(i)).size(); ++j) {
               obj[i][j] = ((ArrayList)this.body_table_jp1.get(i)).get(j);
            }

            tModel.addRow(obj[i]);
         }

      } else {
         System.out.println("Empty Table Data");
      }
   }

   private void readMemoryConfXML() {
      try {
         DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
         Document compBuilder = builder.parse(new File("source\\xml_files\\memory_configuration.xml"));
         compBuilder.getDocumentElement().normalize();
         NodeList lists = compBuilder.getElementsByTagName("memory_configuration");

         for(int i = 0; i < lists.getLength(); ++i) {
            NodeList blocks = lists.item(i).getChildNodes();
            int count = 0;

            for(int j = 0; j < blocks.getLength(); ++j) {
               if (1 == blocks.item(j).getNodeType()) {
                  Node block = blocks.item(j);
                  String attrName = block.getAttributes().getNamedItem("name").getNodeValue();
                  int k;
                  Node subBlock;
                  if (attrName.equals("head")) {
                     for(k = 0; k < block.getChildNodes().getLength(); ++k) {
                        subBlock = block.getChildNodes().item(k);
                        if (1 == subBlock.getNodeType() && !subBlock.getNodeName().equals("description")) {
                           this.header_table_jp1.add(subBlock.getTextContent());
                        }
                     }
                  } else {
                     for(k = 0; k < block.getChildNodes().getLength(); ++k) {
                        subBlock = block.getChildNodes().item(k);
                        if (1 == subBlock.getNodeType()) {
                           if (this.body_table_jp1.size() <= count) {
                              this.body_table_jp1.add(new ArrayList());
                           }

                           if (!subBlock.getNodeName().equals("description")) {
                              ((ArrayList)this.body_table_jp1.get(count)).add(subBlock.getTextContent());
                           } else {
                              this.discription_table_jp1.add(subBlock.getTextContent());
                           }
                        }
                     }

                     ++count;
                  }
               }
            }
         }
      } catch (Exception var12) {
         var12.printStackTrace();
      }

   }

   private void setTableHeaderAndBody() {
      this.readBlockAttributesXML();
      this.blockTable.setRowHeight(20);
      DefaultTableModel tModel = (DefaultTableModel)this.blockTable.getModel();
      Iterator var3 = this.header_table_jp2.iterator();

      while(var3.hasNext()) {
         Object colName = var3.next();
         tModel.addColumn(colName);
      }

      TableColumnModel columnModel = this.blockTable.getColumnModel();

      for(int count = 0; count < columnModel.getColumnCount(); ++count) {
         if (count == 0) {
            columnModel.getColumn(count).setPreferredWidth(100);
         } else if (count == 1) {
            columnModel.getColumn(count).setPreferredWidth(220);
         } else {
            columnModel.getColumn(count).setPreferredWidth(160);
         }
      }

      if (this.body_table_jp2.size() >= 1 && ((ArrayList)this.body_table_jp2.get(0)).size() >= 1) {
         Object[][] obj = new Object[this.body_table_jp2.size()][((ArrayList)this.body_table_jp2.get(0)).size()];

         for(int i = 0; i < this.body_table_jp2.size(); ++i) {
            for(int j = 0; j < ((ArrayList)this.body_table_jp2.get(i)).size(); ++j) {
               obj[i][j] = ((ArrayList)this.body_table_jp2.get(i)).get(j);
            }

            tModel.addRow(obj[i]);
         }

      } else {
         System.out.println("Empty Table Data");
      }
   }

   private void readBlockAttributesXML() {
      try {
         this.header_table_jp2.add("Id");
         this.header_table_jp2.add("Name");
         this.header_table_jp2.add("Length");
         this.header_table_jp2.add("Base Type");
         DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
         Document doc = builder.parse(new File("MAE_RteGen.xml"));
         doc.getDocumentElement().normalize();
         XPath xPath = XPathFactory.newInstance().newXPath();
         String expression = "/rtegen/NvM_Blocks[1]/NvM_Block";
         NodeList lists = (NodeList)xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

         for(int i = 0; i < lists.getLength(); ++i) {
            Node block = lists.item(i);
            if (1 == block.getNodeType()) {
               System.out.println("Name: " + block.getNodeName());
               NamedNodeMap map = block.getAttributes();
               ArrayList<String> sL = new ArrayList();
               sL.add(map.getNamedItem("id").getTextContent());
               sL.add(map.getNamedItem("name").getTextContent());
               sL.add(map.getNamedItem("length").getTextContent());
               sL.add(map.getNamedItem("base-type").getTextContent());
               this.body_table_jp2.add(sL);
            }
         }
      } catch (Exception var10) {
         var10.printStackTrace();
      }

   }

   public void generateSourceFile() {
      int var10000 = this.tabbedPanel.getSelectedIndex();
      this.pic.getClass();
      if (var10000 == 0) {
         System.out.println("Generate .h Files Memory");
         this.generateFileDotH();
      } else {
         var10000 = this.tabbedPanel.getSelectedIndex();
         this.pic.getClass();
         if (var10000 == 1) {
            System.out.println("Generate .h Files Block");
         }
      }

   }

   public void setSave() {
      int var10000 = this.tabbedPanel.getSelectedIndex();
      this.pic.getClass();
      if (var10000 == 0) {
         this.xmlDoc.saveNVMMem(this.nvmTable, this.discription_table_jp1);
      } else {
         var10000 = this.tabbedPanel.getSelectedIndex();
         this.pic.getClass();
         if (var10000 == 1) {
            this.xmlDoc.saveNVMBlock(this.blockTable);
         }
      }

   }

   public void setSaveAs(GuiProject frame) {
      int var10000 = this.tabbedPanel.getSelectedIndex();
      this.pic.getClass();
      JFileChooser fileChooser;
      int userSelection;
      File fileToSave;
      if (var10000 == 0) {
         fileChooser = new JFileChooser();
         fileChooser.setDialogTitle("Specify a file to save");
         userSelection = fileChooser.showSaveDialog(frame);
         if (userSelection == 0) {
            fileToSave = fileChooser.getSelectedFile();
            this.xmlDoc.saveAsNVMMem(fileToSave.getAbsolutePath(), this.nvmTable, this.discription_table_jp1);
         }
      } else {
         var10000 = this.tabbedPanel.getSelectedIndex();
         this.pic.getClass();
         if (var10000 == 1) {
            fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            userSelection = fileChooser.showSaveDialog(frame);
            if (userSelection == 0) {
               fileToSave = fileChooser.getSelectedFile();
               this.xmlDoc.saveAsNVMBlock(fileToSave.getAbsolutePath(), this.blockTable);
            }
         }
      }

   }

   public void generateFileDotH() {
      this.dotHFile.setData(this.body_table_jp1, this.discription_table_jp1);
      this.dotHFile.generateFileDotH(this.frame);
   }
}
