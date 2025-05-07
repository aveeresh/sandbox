package co.in.graspit.filterFiles;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FileFilterXML extends FileFilter {
   public boolean accept(File f) {
      if (f.isDirectory()) {
         return true;
      } else {
         String extension = Utils.getExtension(f);
         if (extension != null) {
            return extension.equals("xml");
         } else {
            return false;
         }
      }
   }

   public String getDescription() {
      return ".XML";
   }
}
