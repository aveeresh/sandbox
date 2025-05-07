package co.in.graspit.tables;

import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class RTEMainTable extends JTable {
   private ArrayList<String[]> editorData = new ArrayList(4);

   public RTEMainTable() {
      this.setSize(this.preferredViewportSize);
      this.intitializeData();
   }

   private void intitializeData() {
      this.editorData.add(new String[]{"Rte_Call", "Rte_Read", "Rte_Write", "Rte_IsUpdated"});
      this.editorData.add(new String[]{"SWX1_SENSOR", "SWX2_LOGICALMANAGER", "SWX3_ACTUATOR", "SWX4"});
      this.editorData.add(new String[]{"SWCS", "UBSW"});
      this.editorData.add(new String[]{"SWX1_SENSOR", "SWX2_LOGICALMANAGER", "SWX3_ACTUATOR", "SWX4"});
   }

   public boolean editCellAt(int row, int column, EventObject e) {
      return super.editCellAt(row, column, e);
   }

   public TableCellEditor getCellEditor(int row, int column) {
      return super.getCellEditor(row, column);
   }
}
