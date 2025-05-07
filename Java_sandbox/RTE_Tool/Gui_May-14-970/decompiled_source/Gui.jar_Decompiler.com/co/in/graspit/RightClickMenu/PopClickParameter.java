package co.in.graspit.RightClickMenu;

import co.in.graspit.GuiProject;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopClickParameter extends MouseAdapter {
   private final GuiProject frame;

   public PopClickParameter(GuiProject frame) {
      this.frame = frame;
   }

   public void mouseReleased(MouseEvent e) {
      if (e.isPopupTrigger()) {
         this.doPop(e);
      }

   }

   private void doPop(MouseEvent e) {
      RightClickPopMenuParameter menu = new RightClickPopMenuParameter(this.frame);
      menu.selectTableRow(e);
      menu.addClearButton(e);
      menu.show(e.getComponent(), e.getX(), e.getY());
   }
}
