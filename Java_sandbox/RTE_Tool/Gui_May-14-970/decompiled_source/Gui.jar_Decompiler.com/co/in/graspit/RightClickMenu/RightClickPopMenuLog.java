package co.in.graspit.RightClickMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

public class RightClickPopMenuLog extends JPopupMenu {
   private ArrayList<JMenuItem> anItem = new ArrayList(1);
   private final JTextArea textArea;

   public RightClickPopMenuLog(JTextArea textArea) {
      this.textArea = textArea;
   }

   public void addClearButton(MouseEvent mE) {
      this.anItem.add(new JMenuItem("Clear Log"));
      int size = this.anItem.size() - 1;
      this.add((JMenuItem)this.anItem.get(size));
      ((JMenuItem)this.anItem.get(size)).addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            RightClickPopMenuLog.this.textArea.setText("Log Panel");
         }
      });
   }
}
