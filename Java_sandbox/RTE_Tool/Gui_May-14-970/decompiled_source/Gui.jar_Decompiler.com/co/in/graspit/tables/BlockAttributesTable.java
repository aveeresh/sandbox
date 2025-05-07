package co.in.graspit.tables;

import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class BlockAttributesTable extends JTable {
   private ArrayList<String[]> editorData = new ArrayList(7);

   public BlockAttributesTable() {
      this.intitializeData();
   }

   private void intitializeData() {
      this.editorData.add(new String[]{"User", "Admin"});
      this.editorData.add(new String[]{"Disable", "Enable"});
      this.editorData.add(new String[]{"Disable", "CRC8", "CRC16", "CRC32"});
      this.editorData.add(new String[]{"", "Disable", "Enable"});
      this.editorData.add(new String[]{"", "Preserved", "Corrupted"});
      this.editorData.add(new String[]{"", "Modified", "Not Modified"});
      this.editorData.add(new String[]{"", "Empty", "Filled"});
   }

   public boolean editCellAt(int row, int column, EventObject e) {
      return column == 0 ? false : super.editCellAt(row, column, e);
   }

   public TableCellEditor getCellEditor(int row, int column) {
      int modelColumn = this.convertColumnIndexToModel(column);
      if (modelColumn >= 3 && modelColumn < 10) {
         JComboBox<String> comboBox1 = new JComboBox((String[])this.editorData.get(modelColumn - 3));
         return new DefaultCellEditor(comboBox1);
      } else {
         return super.getCellEditor(row, column);
      }
   }
}
