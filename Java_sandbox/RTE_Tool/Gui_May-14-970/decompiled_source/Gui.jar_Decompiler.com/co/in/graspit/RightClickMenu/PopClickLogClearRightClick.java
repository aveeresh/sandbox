package co.in.graspit.RightClickMenu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class PopClickLogClearRightClick extends MouseAdapter {
   private final JTextArea textArea;

   public PopClickLogClearRightClick(JTextArea textArea) {
      this.textArea = textArea;
   }

   public void mouseReleased(MouseEvent e) {
      if (e.isPopupTrigger()) {
         this.doPop(e);
      }

   }

   private void doPop(MouseEvent e) {
      RightClickPopMenuLog menu = new RightClickPopMenuLog(this.textArea);
      menu.addClearButton(e);
      menu.show(e.getComponent(), e.getX(), e.getY());
   }
}
