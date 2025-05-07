package co.in.graspit.tools;

import co.in.graspit.filterFiles.FileFilterXML;
import co.in.graspit.filterFiles.FileViewXML;
import co.in.graspit.filterFiles.PreviewFileXML;
import co.in.graspit.readWriteComponentData.AddComponent;
import co.in.graspit.readWriteComponentData.AddPort;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;

public class MainMenuItems {
   private static MainMenuItems instance;
   private final JFrame frame;
   private final JTree tree;
   private final WriteAndReadXMLDocFile xmlDoc = WriteAndReadXMLDocFile.getInstance();
   private final SettingData sd = SettingData.getInstance();
   private final JMenuBar menubar = new JMenuBar();
   private final JMenu menu = new JMenu("Menu");
   private final JMenuItem menuItem_addComponent = new JMenuItem("Add Component");
   private final JMenuItem menuItem_addPort = new JMenuItem("Add Port");
   private final JMenuItem menuItem_importXML = new JMenuItem("Import XML");
   private final JMenuItem menuItem_exportXML = new JMenuItem("Export XML");
   private final JMenuItem menuItem_setting = new JMenuItem("Setting");

   private MainMenuItems(JFrame frame, JTree tree) {
      this.frame = frame;
      this.tree = tree;
      this.setItemsData();
   }

   public static MainMenuItems getInstance(JFrame frame, JTree tree) {
      if (instance == null) {
         instance = new MainMenuItems(frame, tree);
      }

      return instance;
   }

   public JMenuBar getJMenuBar() {
      return this.menubar;
   }

   public void setItemsData() {
      this.menu.setMnemonic(65);
      this.menu.getAccessibleContext().setAccessibleDescription("Add Component");
      this.menubar.add(this.menu);
      this.menuItem_addPort.setMnemonic(66);
      this.menu.add(this.menuItem_addPort);
      this.menu.add(this.menuItem_addComponent);
      this.menu.add(this.menuItem_importXML);
      this.menu.add(this.menuItem_exportXML);
      this.menu.add(this.menuItem_setting);
   }

   public void setAddComponentClickListener(final JTabbedPane mainTabPanel, final AddComponent addCompDialog) {
      this.menuItem_addComponent.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (mainTabPanel.getSelectedIndex() != 0) {
               System.out.println("Wrong Tab, Go to RTE Tab.");
            } else {
               int result = addCompDialog.showDialog();
               addCompDialog.processInputs(result);
            }
         }
      });
   }

   public void setAddPortClickListener(final JTabbedPane mainTabPanel, final AddPort addPortDialog) {
      this.menuItem_addPort.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (mainTabPanel.getSelectedIndex() != 0) {
               System.out.println("Wrong Tab, Go to RTE Tab.");
            } else {
               String compSelected = "Rte_SWC_SWX1";
               if (MainMenuItems.this.tree.isSelectionEmpty()) {
                  System.out.println("Component not Selected.");
               } else {
                  if (MainMenuItems.this.tree.getSelectionPath().getPathCount() > 1) {
                     compSelected = MainMenuItems.this.tree.getSelectionPath().getPathComponent(1).toString();
                  }

                  int result = addPortDialog.showDialog();
                  addPortDialog.processInputs(result, compSelected);
               }
            }
         }
      });
      this.setImportXMLButton(mainTabPanel);
      this.setExportXMLButton(mainTabPanel);
      this.setSettingButton();
   }

   private void setImportXMLButton(final JTabbedPane mainTabPanel) {
      this.menuItem_importXML.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (mainTabPanel.getSelectedIndex() != 0) {
               System.out.println("Wrong Tab, Go to RTE Tab.");
            } else {
               JFileChooser fileChooser = new JFileChooser();
               fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
               fileChooser.setMultiSelectionEnabled(true);
               fileChooser.setFileSelectionMode(0);
               fileChooser.setFileFilter(new FileFilterXML());
               fileChooser.setAcceptAllFileFilterUsed(false);
               fileChooser.setFileView(new FileViewXML());
               fileChooser.setAccessory(new PreviewFileXML(fileChooser));
               int result = fileChooser.showOpenDialog(MainMenuItems.this.frame);
               if (result == 0) {
                  File[] selectedFile = fileChooser.getSelectedFiles();
                  File[] var8 = selectedFile;
                  int var7 = selectedFile.length;

                  for(int var6 = 0; var6 < var7; ++var6) {
                     File file = var8[var6];
                     System.out.println(file.getAbsolutePath());
                     MainMenuItems.this.xmlDoc.importFilesToCurrentLocation(MainMenuItems.this.tree, file.getAbsolutePath(), file.getName());
                  }
               }

               MainMenuItems.this.xmlDoc.setTreeData(MainMenuItems.this.tree);
            }
         }
      });
   }

   private void setExportXMLButton(final JTabbedPane mainTabPanel) {
      this.menuItem_exportXML.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (mainTabPanel.getSelectedIndex() != 0) {
               System.out.println("Wrong Tab, Go to RTE Tab.");
            } else {
               JFileChooser fileChooser = new JFileChooser();
               fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
               fileChooser.setFileSelectionMode(1);
               int result = fileChooser.showOpenDialog(MainMenuItems.this.frame);
               if (result == 0) {
                  File selectedFile = fileChooser.getSelectedFile();
                  System.out.println(selectedFile.getAbsolutePath());
                  MainMenuItems.this.xmlDoc.exportRTEFilesXML(MainMenuItems.this.tree, selectedFile.getAbsolutePath());
               }

            }
         }
      });
   }

   private void setSettingButton() {
      this.menuItem_setting.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            MainMenuItems.this.settingSave(JOptionPane.showConfirmDialog((Component)null, MainMenuItems.this.sd.getSettingPanel(), "Setting", 2, -1));
         }
      });
   }

   private void settingSave(int ok) {
      if (ok == 0) {
         System.out.println("Save Pressed.");
         this.sd.saveChanges();
      } else {
         System.out.println("Cancel Pressed.");
      }

   }
}
