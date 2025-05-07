package co.in.graspit.tools;

public class ConstantDataPanelsIndex {
   private static ConstantDataPanelsIndex instance;
   public final int PANEL_RTE = 0;
   public final int PANEL_NVM = 1;
   public final int PANEL_MEMORY_CONFIGURATION = 0;
   public final int PANEL_BLOCK_ATTRIBUTES = 1;

   private ConstantDataPanelsIndex() {
   }

   public static ConstantDataPanelsIndex getInstance() {
      if (instance == null) {
         instance = new ConstantDataPanelsIndex();
      }

      return instance;
   }
}
