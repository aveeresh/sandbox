package co.in.graspit.RightClickMenu;

import co.in.graspit.readWriteComponentData.ReadAllComponents;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

public class PopClickListenerRight extends MouseAdapter {
   private JTree tree;
   private ReadAllComponents readAllComp;

   public PopClickListenerRight(JTree tree, ReadAllComponents readAllComp) {
      this.tree = tree;
      this.readAllComp = readAllComp;
   }

   public void mousePressed(MouseEvent e) {
   }

   public void mouseReleased(MouseEvent e) {
      if (e.isPopupTrigger()) {
         this.doPop(e);
      }

   }

   public void mouseMoved(MouseEvent e) {
      System.out.println(e.getComponent());
   }

   private void doPop(MouseEvent e) {
      this.tree.clearSelection();
      this.tree.addSelectionPath(this.tree.getPathForLocation(e.getX(), e.getY()));
      RightClickPopUpMenu menu = new RightClickPopUpMenu(this.tree, this.readAllComp, e);
      if (this.tree.getPathForLocation(e.getX(), e.getY()) == null) {
         System.out.println("Nothing Selected.");
      } else {
         int pathCount = this.tree.getPathForLocation(e.getX(), e.getY()).getPathCount();
         if (pathCount == 2) {
            menu.addClientServerButton(e);
            menu.addSenderReceiverButton(e);
            menu.addRunnableButton(e);
            menu.addCustomButton(e);
         }

         if (pathCount == 3) {
            TreePath path = this.tree.getPathForLocation(e.getX(), e.getY());
            if (path.getPathComponent(2).toString().equals("ClientServer")) {
               menu.addOperationButton(e);
            }
         }

         if (pathCount != 1) {
            menu.addDeleteButton(e);
            menu.show(e.getComponent(), e.getX(), e.getY());
         }

      }
   }
}
