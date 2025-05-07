package co.in.graspit.mainPanels;

import co.in.graspit.GuiProject;
import co.in.graspit.RightClickMenu.PopClickListenerRight;
import co.in.graspit.filterFiles.FileFilterXML;
import co.in.graspit.filterFiles.FileViewXML;
import co.in.graspit.filterFiles.PreviewFileXML;
import co.in.graspit.readWriteComponentData.AddComponent;
import co.in.graspit.readWriteComponentData.AddPort;
import co.in.graspit.readWriteComponentData.CreateSrcFiles;
import co.in.graspit.readWriteComponentData.ReadAllComponents;
import co.in.graspit.tables.RTEMainTable;
import co.in.graspit.tools.WriteAndReadXMLDocFile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PanelRTE extends JPanel {
   private final WriteAndReadXMLDocFile xmlDoc = WriteAndReadXMLDocFile.getInstance();
   private final JTree tree = new JTree();
   private final JTable jTable;
   private final JScrollPane scrollPane;
   private final JScrollPane scrollPanelTree;
   private final ArrayList<DefaultMutableTreeNode> d_list = new ArrayList();
   private final ArrayList<DefaultTreeModel> m_list = new ArrayList();
   private final ArrayList<String> allComponentsList = new ArrayList();
   private final AddComponent addCompDialog;
   private final AddPort addPortDialog;
   private static ReadAllComponents readAllComp;
   private CreateSrcFiles createSourceFiles;
   private GuiProject frame;
   private final JTextField editText1;
   private final JTextField editText2;
   private final JTextField editText3;
   private TreePath jTreePath;

   public PanelRTE() {
      this.addCompDialog = new AddComponent(this.tree);
      this.addPortDialog = new AddPort(this.tree, this.allComponentsList);
      this.editText1 = new JTextField();
      this.editText2 = new JTextField();
      this.editText3 = new JTextField();
      this.jTreePath = null;
      this.setBorder(new MatteBorder(2, 2, 2, 0, new Color(244, 247, 252)));
      this.setBackground(Color.WHITE);
      this.setLayout(new BorderLayout(0, 0));
      this.tree.setBorder(new MatteBorder(1, 1, 1, 1, SystemColor.inactiveCaptionBorder));
      this.tree.setSelectionRow(0);
      this.tree.setBackground(Color.WHITE);
      this.scrollPanelTree = new JScrollPane(this.tree);
      this.scrollPanelTree.setPreferredSize(new Dimension(200, 322));
      this.scrollPanelTree.setMinimumSize(new Dimension(200, 23));
      this.add(this.scrollPanelTree, "West");
      this.scrollPanelTree.setBackground(Color.WHITE);
      this.scrollPanelTree.getViewport().setBackground(Color.WHITE);
      this.jTable = new RTEMainTable();
      this.jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
         public void valueChanged(ListSelectionEvent event) {
            if (!event.getValueIsAdjusting() && PanelRTE.this.jTable.getSelectedRow() != -1) {
               PanelRTE.this.xmlDoc.changeTableDataParameter(PanelRTE.this.jTable, PanelRTE.this.jTreePath);
            }

         }
      });
      this.jTable.setRowHeight(22);
      this.jTable.setBackground(Color.WHITE);
      this.jTable.setAutoResizeMode(0);
      this.jTable.setVisible(true);
      this.scrollPane = new JScrollPane(this.jTable);
      this.add(this.scrollPane, "Center");
      this.scrollPane.setBackground(Color.WHITE);
      this.scrollPane.getViewport().setBackground(Color.WHITE);
      this.initializeData();
   }

   public void setGui(GuiProject frame) {
      this.frame = frame;
      frame.setRTETree(this.tree);
   }

   public JTable getMainTable() {
      return this.jTable;
   }

   private void initializeData() {
      try {
         DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
         Document docCompList = builder.parse(new File("MAE_RteGen.xml"));
         NodeList nList = docCompList.getElementsByTagName(docCompList.getDocumentElement().getNodeName());
         XPath xPath = XPathFactory.newInstance().newXPath();
         String expression = "/rtegen";
         NodeList nodeList = (NodeList)xPath.compile(expression).evaluate(docCompList, XPathConstants.NODESET);
         nList = nodeList;
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
               this.tree.setModel((TreeModel)this.m_list.get(count));
               this.tree.getSelectionModel().setSelectionMode(1);
               ++count;
            }
         }

         this.tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent evt) {
               PanelRTE.this.jTree1ValueChanged(evt);
            }
         });

         try {
            readAllComp = new ReadAllComponents(this.jTable);
         } catch (ParserConfigurationException var11) {
            var11.printStackTrace();
         }

         this.tree.addMouseListener(new PopClickListenerRight(this.tree, readAllComp));
         this.createSourceFiles = new CreateSrcFiles(this.allComponentsList);
      } catch (Throwable var12) {
         var12.printStackTrace();
      }

   }

   public void setSourceFileGenerate() {
      this.createSourceFiles.createFiles(this.frame);
   }

   public void setSave() {
      this.xmlDoc.saveRTE(this.tree, this.jTable);
   }

   public void setSaveAs(GuiProject frame) {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setDialogTitle("Specify a file to save");
      int userSelection = fileChooser.showSaveDialog(frame);
      if (userSelection == 0) {
         File fileToSave = fileChooser.getSelectedFile();
         this.xmlDoc.saveAsRTE(fileToSave.getAbsolutePath(), this.tree, this.jTable);
      }

   }

   public void setAddComponentClickListener() {
      this.addCompDialog.showDialogToAddComponent(this.tree);
   }

   public void setAddPortClickListener() {
      String compSelected = "Rte_SWC_SWX1";
      if (this.tree.isSelectionEmpty()) {
         System.out.println("Component not Selected.");
      } else {
         if (this.tree.getSelectionPath().getPathCount() > 1) {
            compSelected = this.tree.getSelectionPath().getPathComponent(1).toString();
         }

         int result = this.addPortDialog.showDialog();
         this.addPortDialog.processInputs(result, compSelected);
      }
   }

   public void setImportXMLButton(GuiProject frame) {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      fileChooser.setMultiSelectionEnabled(true);
      fileChooser.setFileSelectionMode(0);
      fileChooser.setFileFilter(new FileFilterXML());
      fileChooser.setAcceptAllFileFilterUsed(false);
      fileChooser.setFileView(new FileViewXML());
      fileChooser.setAccessory(new PreviewFileXML(fileChooser));
      int result = fileChooser.showOpenDialog(frame);
      if (result == 0) {
         File[] selectedFile = fileChooser.getSelectedFiles();
         File[] var8 = selectedFile;
         int var7 = selectedFile.length;

         for(int var6 = 0; var6 < var7; ++var6) {
            File file = var8[var6];
            System.out.println(file.getAbsolutePath());
            this.xmlDoc.importFilesToCurrentLocation(this.tree, file.getAbsolutePath(), file.getName());
         }
      }

      this.xmlDoc.setTreeData(this.tree);
   }

   public void setExportXMLButton(GuiProject frame) {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      fileChooser.setFileSelectionMode(1);
      int result = fileChooser.showOpenDialog(frame);
      if (result == 0) {
         File selectedFile = fileChooser.getSelectedFile();
         System.out.println(selectedFile.getAbsolutePath());
         this.xmlDoc.exportRTEFilesXML(this.tree, selectedFile.getAbsolutePath());
      }

   }

   public void addParameterAndTypeInTable() {
      if (this.frame.getTableParameter().getRowCount() >= 4) {
         this.frame.setLogDataShow("Max 4 Row Supported.");
      } else {
         TreePath path = this.tree.getSelectionPath();
         if (path != null && path.getPathCount() >= 3) {
            if (!path.getPathComponent(2).toString().equals("ClientServer")) {
               this.frame.setLogDataShow("Select ClientServer Only.");
            } else if (this.jTable.getSelectedRow() == -1) {
               this.frame.setLogDataShow("Select Opration from Table.");
            } else {
               JPanel dialogPanel = new JPanel(new GridLayout(2, 3));
               dialogPanel.add(new JLabel("Parameter"));
               dialogPanel.add(new JLabel("Direction"));
               dialogPanel.add(new JLabel("Type"));
               dialogPanel.add(this.editText1);
               dialogPanel.add(this.editText2);
               dialogPanel.add(this.editText3);
               this.selectedParameterPanel(JOptionPane.showConfirmDialog((Component)null, dialogPanel, "Add Parameter And Type", 2, -1));
            }
         } else {
            this.frame.setLogDataShow("Select Port from Side Bar.");
         }
      }
   }

   private void selectedParameterPanel(int ok) {
      if (ok == 0) {
         String parameter = this.editText1.getText().toString();
         String direction = this.editText2.getText().toString();
         String type = this.editText3.getText().toString();
         this.editText1.setText("");
         this.editText2.setText("");
         this.editText3.setText("");
         DefaultTableModel model = (DefaultTableModel)this.frame.getTableParameter().getModel();
         model.addRow(new String[]{type + "_" + parameter, direction, type});
         this.xmlDoc.changeInXmlFileForParameter(this.tree.getSelectionPath(), parameter, direction, type);
      } else {
         System.out.println("Cancel");
      }

   }

   private void jTree1ValueChanged(TreeSelectionEvent evt) {
      TreePath treepath = evt.getNewLeadSelectionPath();
      this.jTreePath = treepath;
      this.frame.setLabelComponentNameText("Component Name");
      this.frame.setLabelPortTypeText("Port Type");
      DefaultTableModel model = (DefaultTableModel)this.frame.getTableParameter().getModel();
      model.setRowCount(0);
      if (treepath != null) {
         System.out.println("treePath: " + treepath);
         if (treepath.getPathCount() > 1) {
            if (treepath.getPathCount() > 2) {
               this.frame.setLabelPortTypeText(treepath.getPathComponent(2).toString());
               readAllComp.showTableData(treepath, this.tree);
            }

            String componenFileXml = treepath.getPathComponent(1).toString();
            this.frame.setLabelComponentNameText(componenFileXml);
            if (treepath.getPathCount() <= 2) {
               readAllComp.populateJTable(treepath);
            }
         }

      }
   }
}
