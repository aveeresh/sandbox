package co.in.graspit.tables;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.JTable;

public class MemoryConfigurationTable extends JTable {
   private ArrayList<String> discription_table_jp1 = null;

   public void addDiscriptionArray(ArrayList<String> discription_table_jp1) {
      this.discription_table_jp1 = discription_table_jp1;
   }

   public boolean editCellAt(int row, int column, EventObject e) {
      return column == 0 ? false : super.editCellAt(row, column, e);
   }

   public String getToolTipText(MouseEvent e) {
      String tip = super.getToolTipText(e);
      Point p = e.getPoint();
      int rowIndex = this.rowAtPoint(p);
      int colIndex = this.columnAtPoint(p);
      int realColumnIndex = this.convertColumnIndexToModel(colIndex);
      if (realColumnIndex == 0 && this.discription_table_jp1 != null && rowIndex < this.discription_table_jp1.size()) {
         tip = (String)this.discription_table_jp1.get(rowIndex);
      }

      return tip;
   }
}
