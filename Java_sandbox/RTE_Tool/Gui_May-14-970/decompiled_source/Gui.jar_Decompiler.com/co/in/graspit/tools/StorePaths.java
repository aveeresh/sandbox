package co.in.graspit.tools;

public class StorePaths {
   private static StorePaths instance;
   private String sourceFilePathLocation;

   private StorePaths() {
   }

   public static StorePaths getInstance() {
      if (instance == null) {
         instance = new StorePaths();
      }

      return instance;
   }

   public String getSourceFilePathLocation() {
      return this.sourceFilePathLocation;
   }

   public void setSourceFilePathLocation(String sourceFilePathLocation) {
      this.sourceFilePathLocation = sourceFilePathLocation;
   }
}
