package co.in.graspit.filterFiles;

import java.awt.Toolkit;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;

public class FileViewXML extends FileView {
   private ImageIcon xmlIcon = Utils.createImageIcon(Toolkit.getDefaultToolkit().getImage("source/images/xml_file_view_16px.png"));

   public String getName(File f) {
      return null;
   }

   public String getDescription(File f) {
      return null;
   }

   public Boolean isTraversable(File f) {
      return null;
   }

   public String getTypeDescription(File f) {
      String extension = Utils.getExtension(f);
      String type = null;
      if (extension != null && extension.equals("xml")) {
         type = "XML File.";
      }

      return type;
   }

   public Icon getIcon(File f) {
      String extension = Utils.getExtension(f);
      Icon icon = null;
      if (extension != null && extension.equals("xml")) {
         icon = this.xmlIcon;
      }

      return icon;
   }
}
