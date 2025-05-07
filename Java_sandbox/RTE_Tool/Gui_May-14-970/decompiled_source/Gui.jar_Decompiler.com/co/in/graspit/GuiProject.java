package co.in.graspit;

import co.in.graspit.RightClickMenu.PopClickLogClearRightClick;
import co.in.graspit.RightClickMenu.PopClickParameter;
import co.in.graspit.mainPanels.PanelNVM;
import co.in.graspit.mainPanels.PanelRTE;
import co.in.graspit.tables.ParameterRTETable;
import co.in.graspit.tools.ConstantDataPanelsIndex;
import co.in.graspit.tools.SettingData;
import co.in.graspit.tools.WriteAndReadXMLDocFile;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.util.Date;
import javax.swing.AbstractListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class GuiProject extends JFrame {
   private final SettingData sd = SettingData.getInstance();
   private final ConstantDataPanelsIndex pic = ConstantDataPanelsIndex.getInstance();
   private final WriteAndReadXMLDocFile xmlDoc = WriteAndReadXMLDocFile.getInstance();
   private static GuiProject frame;
   private final JPanel panelRTE;
   private final JPanel panelNVM;
   private final JTextArea textArea;
   private final JTable parameterTable;
   private final JLabel lblComponentName;
   private final JLabel lblPortType;
   private final JButton btnAddNewParameter;
   private final JInternalFrame internalFrame;
   private final JSplitPane splitPane;
   private final JScrollPane scrollPanelDetail;
   private final JPanel portDataShowOnTableSelection;
   private JTree rteTree;

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               GuiProject.frame = new GuiProject();
               GuiProject.frame.setVisible(true);
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      });
   }

   public GuiProject() {
      this.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\1_03_32.png"));
      this.getContentPane().setBackground(Color.WHITE);
      this.setForeground(Color.GRAY);
      this.setTitle("RTE Tool");
      this.setBackground(Color.WHITE);
      this.setDefaultCloseOperation(3);
      this.setBounds(100, 100, 800, 500);
      this.setExtendedState(6);
      this.sd.readSettingXML();
      this.xmlDoc.setFrame(this);
      this.panelRTE = new PanelRTE();
      this.panelNVM = new PanelNVM();
      ((PanelRTE)this.panelRTE).setGui(this);
      ((PanelNVM)this.panelNVM).setGui(this);
      JPanel panel = new JPanel();
      panel.setBackground(Color.WHITE);
      this.getContentPane().add(panel, "Center");
      panel.setMaximumSize(this.getMaximumSize());
      panel.setLayout(new CardLayout(0, 0));
      panel.add(this.panelRTE, "name_77766868455100");
      panel.add(this.panelNVM, "name_77766885638500");
      final JList<?> listPanel = new JList();
      listPanel.setBorder(new EmptyBorder(2, 10, 10, 10));
      listPanel.setModel(new AbstractListModel() {
         String[] values = new String[]{"  RTE  ", "  NVM  "};

         public int getSize() {
            return this.values.length;
         }

         public Object getElementAt(int index) {
            return this.values[index];
         }
      });
      this.pic.getClass();
      listPanel.setSelectedIndex(0);
      listPanel.setFont(new Font("Tahoma", 1, 16));
      listPanel.setForeground(Color.DARK_GRAY);
      listPanel.setBackground(Color.WHITE);
      this.getContentPane().add(listPanel, "West");
      listPanel.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
         }
      });
      JToolBar toolBar = new JToolBar();
      toolBar.setRollover(true);
      toolBar.setBackground(SystemColor.inactiveCaptionBorder);
      toolBar.setFloatable(false);
      toolBar.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.getContentPane().add(toolBar, "North");
      JLabel btnGenerateSourceFiles = new JLabel("");
      btnGenerateSourceFiles.setBackground(Color.WHITE);
      btnGenerateSourceFiles.setToolTipText("Generate Source Files");
      btnGenerateSourceFiles.setIcon(new ImageIcon("img\\generate_16.png"));
      btnGenerateSourceFiles.setBorder(new EmptyBorder(0, 5, 0, 5));
      toolBar.add(btnGenerateSourceFiles);
      btnGenerateSourceFiles.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            int var10000 = listPanel.getSelectedIndex();
            GuiProject.this.pic.getClass();
            if (var10000 == 0) {
               ((PanelRTE)GuiProject.this.panelRTE).setSourceFileGenerate();
            }

            var10000 = listPanel.getSelectedIndex();
            GuiProject.this.pic.getClass();
            if (var10000 == 1) {
               ((PanelNVM)GuiProject.this.panelNVM).generateSourceFile();
            }

         }
      });
      JLabel btnSave = new JLabel("");
      btnSave.setBackground(Color.WHITE);
      btnSave.setToolTipText("Save");
      btnSave.setIcon(new ImageIcon("img\\save_16.png"));
      btnSave.setBorder(new EmptyBorder(0, 5, 0, 5));
      toolBar.add(btnSave);
      btnSave.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            int var10000 = listPanel.getSelectedIndex();
            GuiProject.this.pic.getClass();
            if (var10000 == 0) {
               ((PanelRTE)GuiProject.this.panelRTE).setSave();
            }

            var10000 = listPanel.getSelectedIndex();
            GuiProject.this.pic.getClass();
            if (var10000 == 1) {
               ((PanelNVM)GuiProject.this.panelNVM).setSave();
            }

         }
      });
      JLabel btnSaveAs = new JLabel("");
      btnSaveAs.setBackground(Color.WHITE);
      btnSaveAs.setToolTipText("Save As");
      btnSaveAs.setIcon(new ImageIcon("img\\saveas_16.png"));
      btnSaveAs.setBorder(new EmptyBorder(0, 5, 0, 5));
      toolBar.add(btnSaveAs);
      this.internalFrame = new JInternalFrame("Console");
      this.internalFrame.setFrameIcon((Icon)null);
      this.internalFrame.setDefaultCloseOperation(1);
      this.internalFrame.setClosable(true);
      this.internalFrame.setBorder((Border)null);
      this.internalFrame.setBackground(Color.WHITE);
      this.internalFrame.getContentPane().setBackground(Color.WHITE);
      this.getContentPane().add(this.internalFrame, "South");
      this.internalFrame.setVisible(true);
      this.internalFrame.getContentPane().setLayout(new CardLayout(0, 0));
      this.splitPane = new JSplitPane();
      this.splitPane.setDividerSize(2);
      this.splitPane.setBackground(Color.WHITE);
      this.splitPane.setPreferredSize(new Dimension(32767, 200));
      this.splitPane.setMinimumSize(new Dimension(20, 20));
      this.internalFrame.getContentPane().add(this.splitPane, "name_73244424131100");
      this.textArea = new JTextArea();
      this.textArea.addMouseListener(new PopClickLogClearRightClick(this.textArea));
      this.textArea.setEditable(false);
      this.textArea.setBackground(Color.WHITE);
      JScrollPane scrollPanelLog = new JScrollPane(this.textArea);
      scrollPanelLog.setPreferredSize(new Dimension(500, 24));
      scrollPanelLog.getViewport().setBackground(Color.WHITE);
      scrollPanelLog.setBackground(Color.WHITE);
      scrollPanelLog.setVerticalScrollBarPolicy(22);
      scrollPanelLog.setHorizontalScrollBarPolicy(32);
      scrollPanelLog.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.splitPane.add(scrollPanelLog, "left");
      this.portDataShowOnTableSelection = new JPanel();
      this.portDataShowOnTableSelection.setBackground(Color.WHITE);
      this.scrollPanelDetail = new JScrollPane(this.portDataShowOnTableSelection);
      this.scrollPanelDetail.setBorder(new EmptyBorder(0, 0, 0, 0));
      this.scrollPanelDetail.getViewport().setBackground(Color.WHITE);
      this.scrollPanelDetail.setBackground(Color.WHITE);
      this.splitPane.add(this.scrollPanelDetail, "right");
      this.portDataShowOnTableSelection.setLayout((LayoutManager)null);
      this.parameterTable = new ParameterRTETable();
      this.parameterTable.addMouseListener(new PopClickParameter(this));
      JScrollPane scrollPane = new JScrollPane(this.parameterTable);
      scrollPane.setBounds(10, 40, 410, 156);
      scrollPane.getViewport().setBackground(Color.WHITE);
      this.portDataShowOnTableSelection.add(scrollPane);
      this.parameterTable.setBounds(0, 0, 1, 1);
      DefaultTableModel tModel = (DefaultTableModel)this.parameterTable.getModel();
      tModel.addColumn("Parameter");
      tModel.addColumn("Direction");
      tModel.addColumn("Type");
      this.lblComponentName = new JLabel("Component Name");
      this.lblComponentName.setForeground(Color.LIGHT_GRAY);
      this.lblComponentName.setHorizontalAlignment(0);
      this.lblComponentName.setAlignmentX(0.5F);
      this.lblComponentName.setFont(new Font("Tahoma", 0, 12));
      this.lblComponentName.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
      this.lblComponentName.setBackground(Color.WHITE);
      this.lblComponentName.setBounds(10, 10, 200, 25);
      this.portDataShowOnTableSelection.add(this.lblComponentName);
      this.lblPortType = new JLabel("Port Type");
      this.lblPortType.setForeground(Color.LIGHT_GRAY);
      this.lblPortType.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
      this.lblPortType.setFont(new Font("Tahoma", 0, 12));
      this.lblPortType.setHorizontalAlignment(0);
      this.lblPortType.setBackground(Color.WHITE);
      this.lblPortType.setBounds(220, 10, 200, 25);
      this.portDataShowOnTableSelection.add(this.lblPortType);
      this.btnAddNewParameter = new JButton("");
      this.btnAddNewParameter.setIcon(new ImageIcon("img\\plus_sign_16.png"));
      this.btnAddNewParameter.setBounds(420, 40, 25, 25);
      this.portDataShowOnTableSelection.add(this.btnAddNewParameter);
      this.textArea.append("Log Panel");
      btnSaveAs.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            int var10000 = listPanel.getSelectedIndex();
            GuiProject.this.pic.getClass();
            if (var10000 == 0) {
               ((PanelRTE)GuiProject.this.panelRTE).setSaveAs(GuiProject.frame);
            }

            var10000 = listPanel.getSelectedIndex();
            GuiProject.this.pic.getClass();
            if (var10000 == 1) {
               ((PanelNVM)GuiProject.this.panelNVM).setSaveAs(GuiProject.frame);
            }

         }
      });
      listPanel.addListSelectionListener(new ListSelectionListener() {
         public void valueChanged(ListSelectionEvent e) {
            System.out.println("List Selected Index ValueChanged: " + listPanel.getSelectedIndex());
            int var10000 = listPanel.getSelectedIndex();
            GuiProject.this.pic.getClass();
            if (var10000 == 0) {
               GuiProject.this.panelRTE.setVisible(true);
               GuiProject.this.panelNVM.setVisible(false);
            }

            var10000 = listPanel.getSelectedIndex();
            GuiProject.this.pic.getClass();
            if (var10000 == 1) {
               GuiProject.this.panelRTE.setVisible(false);
               GuiProject.this.panelNVM.setVisible(true);
            }

         }
      });
      JMenuBar menuBar = new JMenuBar();
      menuBar.setBackground(Color.WHITE);
      this.setJMenuBar(menuBar);
      JMenu fileMenu = new JMenu("File");
      fileMenu.setMnemonic(70);
      fileMenu.setBackground(Color.WHITE);
      menuBar.add(fileMenu);
      JMenuItem addComponentFileItem = new JMenuItem("Add Component");
      addComponentFileItem.setBackground(Color.WHITE);
      fileMenu.add(addComponentFileItem);
      addComponentFileItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int var10000 = listPanel.getSelectedIndex();
            GuiProject.this.pic.getClass();
            if (var10000 != 0) {
               GuiProject.this.setLogDataShow("Wrong Tab, Go to RTE Tab.");
               System.out.println("Wrong Tab, Go to RTE Tab.");
            } else {
               ((PanelRTE)GuiProject.this.panelRTE).setAddComponentClickListener();
            }
         }
      });
      JMenuItem addPortFileItem = new JMenuItem("Add Port");
      addPortFileItem.setBackground(Color.WHITE);
      addPortFileItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int var10000 = listPanel.getSelectedIndex();
            GuiProject.this.pic.getClass();
            if (var10000 != 0) {
               GuiProject.this.setLogDataShow("Wrong Tab, Go to RTE Tab.");
               System.out.println("Wrong Tab, Go to RTE Tab.");
            }
         }
      });
      JMenuItem importXMLFileItem = new JMenuItem("Import XML");
      importXMLFileItem.setBackground(Color.WHITE);
      fileMenu.add(importXMLFileItem);
      importXMLFileItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int var10000 = listPanel.getSelectedIndex();
            GuiProject.this.pic.getClass();
            if (var10000 != 0) {
               GuiProject.this.setLogDataShow("Wrong Tab, Go to RTE Tab.");
               System.out.println("Wrong Tab, Go to RTE Tab.");
            } else {
               ((PanelRTE)GuiProject.this.panelRTE).setImportXMLButton(GuiProject.frame);
            }
         }
      });
      JMenuItem exportXMLFileItem = new JMenuItem("Export XML");
      exportXMLFileItem.setBackground(Color.WHITE);
      fileMenu.add(exportXMLFileItem);
      exportXMLFileItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int var10000 = listPanel.getSelectedIndex();
            GuiProject.this.pic.getClass();
            if (var10000 != 0) {
               GuiProject.this.setLogDataShow("Wrong Tab, Go to RTE Tab.");
               System.out.println("Wrong Tab, Go to RTE Tab.");
            } else {
               ((PanelRTE)GuiProject.this.panelRTE).setExportXMLButton(GuiProject.frame);
            }
         }
      });
      JMenuItem exitFileItem = new JMenuItem("Exit");
      exitFileItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
         }
      });
      exitFileItem.setBackground(Color.WHITE);
      fileMenu.add(exitFileItem);
      JMenu viewMenu = new JMenu("View");
      viewMenu.setBackground(Color.WHITE);
      viewMenu.setMnemonic(86);
      menuBar.add(viewMenu);
      JMenuItem showLogPanelViewItem = new JMenuItem("Show Log Parameter Panel");
      showLogPanelViewItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (GuiProject.this.internalFrame.isVisible()) {
               GuiProject.this.internalFrame.setVisible(false);
            } else {
               GuiProject.this.internalFrame.setVisible(true);
            }

         }
      });
      showLogPanelViewItem.setBackground(Color.WHITE);
      viewMenu.add(showLogPanelViewItem);
      JMenu settingMenu = new JMenu("Setting");
      settingMenu.setMnemonic(72);
      settingMenu.setBackground(Color.WHITE);
      menuBar.add(settingMenu);
      JMenuItem settingHelpItem = new JMenuItem("Setting");
      settingHelpItem.setBackground(Color.WHITE);
      settingMenu.add(settingHelpItem);
      settingHelpItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            GuiProject.this.setSettingButton();
         }
      });
      this.eventsListeners();
   }

   private void eventsListeners() {
      this.btnAddNewParameter.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ((PanelRTE)GuiProject.this.panelRTE).addParameterAndTypeInTable();
         }
      });
   }

   private void setSettingButton() {
      this.settingSave(JOptionPane.showConfirmDialog((Component)null, this.sd.getSettingPanel(), "Setting", 2, -1));
   }

   private void settingSave(int ok) {
      if (ok == 0) {
         System.out.println("Save Pressed.");
         this.sd.saveChanges();
      } else {
         System.out.println("Cancel Pressed.");
      }

   }

   public JTable getTableParameter() {
      return this.parameterTable;
   }

   public void setLogDataShow(String logLine) {
      this.textArea.append("\n");
      this.textArea.append((new Time((new Date()).getTime())).toString() + ": " + logLine);
   }

   public void setLabelComponentNameText(String text) {
      if (text.equals("Component Name")) {
         this.lblComponentName.setForeground(Color.LIGHT_GRAY);
      } else {
         this.lblComponentName.setForeground(Color.BLACK);
      }

      this.lblComponentName.setText(text);
   }

   public void setLabelPortTypeText(String text) {
      if (text.equals("Port Type")) {
         this.lblPortType.setForeground(Color.LIGHT_GRAY);
      } else {
         this.lblPortType.setForeground(Color.BLACK);
      }

      this.lblPortType.setText(text);
   }

   public void setRTETree(JTree tree) {
      this.rteTree = tree;
   }

   public JTree getRTETree() {
      return this.rteTree;
   }

   public JPanel getRTEPanel() {
      return this.panelRTE;
   }
}
