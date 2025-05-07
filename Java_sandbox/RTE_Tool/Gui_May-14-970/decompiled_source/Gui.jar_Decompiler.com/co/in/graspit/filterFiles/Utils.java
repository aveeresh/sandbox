package co.in.graspit.filterFiles;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;

public class Utils {
   public static final String jpeg = "jpeg";
   public static final String jpg = "jpg";
   public static final String gif = "gif";
   public static final String tiff = "tiff";
   public static final String tif = "tif";
   public static final String png = "png";
   public static final String xml = "xml";

   public static String getExtension(File f) {
      String ext = null;
      String s = f.getName();
      int i = s.lastIndexOf(46);
      if (i > 0 && i < s.length() - 1) {
         ext = s.substring(i + 1).toLowerCase();
      }

      return ext;
   }

   protected static ImageIcon createImageIcon(String path) {
      URL imgURL = Utils.class.getResource(path);
      System.out.println(imgURL);
      if (imgURL != null) {
         return new ImageIcon(imgURL);
      } else {
         System.err.println("Couldn't find file: " + path);
         return null;
      }
   }

   protected static ImageIcon createImageIcon(Image image) {
      if (image != null) {
         return new ImageIcon(image);
      } else {
         System.err.println("Couldn't find file: " + image);
         return null;
      }
   }
}
