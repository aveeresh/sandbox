package co.in.graspit.filterFiles;

import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

public class PreviewFileXML extends JComponent implements PropertyChangeListener {
   private ImageIcon thumbnail = null;
   private File file = null;

   public PreviewFileXML(JFileChooser fc) {
      this.setPreferredSize(new Dimension(100, 50));
      fc.addPropertyChangeListener(this);
   }

   public void loadImage() {
      if (this.file == null) {
         this.thumbnail = null;
      } else {
         ImageIcon tmpIcon = new ImageIcon(this.file.getPath());
         if (tmpIcon != null) {
            if (tmpIcon.getIconWidth() > 90) {
               this.thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(90, -1, 1));
            } else {
               this.thumbnail = tmpIcon;
            }
         }

      }
   }

   public void propertyChange(PropertyChangeEvent e) {
      boolean update = false;
      String prop = e.getPropertyName();
      if ("directoryChanged".equals(prop)) {
         this.file = null;
         update = true;
      } else if ("SelectedFileChangedProperty".equals(prop)) {
         this.file = (File)e.getNewValue();
         update = true;
      }

      if (update) {
         this.thumbnail = null;
         if (this.isShowing()) {
            this.loadImage();
            this.repaint();
         }
      }

   }

   protected void paintComponent(Graphics g) {
      if (this.thumbnail == null) {
         this.loadImage();
      }

      if (this.thumbnail != null) {
         int x = this.getWidth() / 2 - this.thumbnail.getIconWidth() / 2;
         int y = this.getHeight() / 2 - this.thumbnail.getIconHeight() / 2;
         if (y < 0) {
            y = 0;
         }

         if (x < 5) {
            x = 5;
         }

         this.thumbnail.paintIcon(this, g, x, y);
      }

   }
}
