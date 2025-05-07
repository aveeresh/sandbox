package co.in.graspit.readWriteComponentData;

import co.in.graspit.GuiProject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class GenerateNVMFileDotH {
   private static GenerateNVMFileDotH instance;
   private final String filesPathDotH = "src\\NVM\\components\\";
   private final String fileInformation = "FILE INFORMATION";
   private final String headerGuard = "HEADER GUARD";
   private final String localAndGlobalDefinition = "LOCAL & GLOBAL DEFINITION";
   private final String configurationParameters = "CONFIGURATION PARAMETERS";
   private final String commentDash = "/";
   private final String commentStar = "*";
   private final String newLine = "\n";
   private final String tabLine = "\t";
   private final String spaceLine = " ";
   private final String atFile = "@file\t: ";
   private final String atFileValue = "Nvm_cfg.h";
   private final String atBrief = "@brief\t: ";
   private final String atBriefValue = "Header file of NVM Manager queue management sub-module";
   private final String atCopyright = "@copyright";
   private final String atCopyrightValue = "(c)2020 All right reserved";
   private final String hash_if_n_def = "#ifndef";
   private final String hash_if_def = "#ifdef";
   private final String hash_un_def = "#undef";
   private final String hash_end_if = "#endif";
   private final String hash_define = "#define";
   private final String _NVM_CFG_H_ = "__NVM_CFG_H__";
   private final String GLOBAL = "GLOBAL";
   private final String LOCAL = "LOCAL";
   private final String extern = "extern";
   private final String staticV = "static";
   private final int starCount = 80;
   private ArrayList<String> name = new ArrayList();
   private ArrayList<String> unit = new ArrayList();
   private ArrayList<String> comment = new ArrayList();
   private String newTabResult = "%-32s%-10s%s%n";

   private GenerateNVMFileDotH() {
   }

   public static GenerateNVMFileDotH getInstance() {
      if (instance == null) {
         instance = new GenerateNVMFileDotH();
      }

      return instance;
   }

   private String getComentedStarLine() {
      StringBuilder sb = new StringBuilder();
      sb.append("/");

      for(int i = 0; i < 80; ++i) {
         sb.append("*");
      }

      sb.append("/");
      return sb.toString();
   }

   public void generateFileDotH(GuiProject frame) {
      File mkDirDotH = new File("src\\NVM\\components\\\\");
      mkDirDotH.mkdirs();
      File hFile = new File("src\\NVM\\components\\Nvm_cfg.h");

      try {
         hFile.createNewFile();
         this.writeFile(hFile, frame);
      } catch (IOException var5) {
         var5.printStackTrace();
         frame.setLogDataShow("Error in Creating Nvm_cfg.h file.");
         frame.setLogDataShow(var5.getMessage());
      }

   }

   private void writeFile(File file, GuiProject frame) {
      try {
         FileWriter myWriter = new FileWriter(file);
         this.addNewLineAfterEveryLine(this.getComentedStarLine(), myWriter);
         this.addNewLineAfterEveryLine("// FILE INFORMATION", myWriter);
         this.addNewLineAfterEveryLine(this.getComentedStarLine(), myWriter);
         this.addNewLineAfterEveryLine("/**", myWriter);
         this.addNewLineAfterEveryLine(" * @file\t: Nvm_cfg.h", myWriter);
         this.addNewLineAfterEveryLine(" * @brief\t: Header file of NVM Manager queue management sub-module", myWriter);
         this.addNewLineAfterEveryLine(" *", myWriter);
         this.addNewLineAfterEveryLine(" * @copyright\t(c)2020 All right reserved", myWriter);
         this.addNewLineAfterEveryLine(" */", myWriter);
         myWriter.append("\n");
         this.addNewLineAfterEveryLine(this.getComentedStarLine(), myWriter);
         this.addNewLineAfterEveryLine("// HEADER GUARD", myWriter);
         this.addNewLineAfterEveryLine(this.getComentedStarLine(), myWriter);
         this.addNewLineAfterEveryLine("#ifndef __NVM_CFG_H__", myWriter);
         this.addNewLineAfterEveryLine("#define __NVM_CFG_H__", myWriter);
         myWriter.append("\n");
         this.addNewLineAfterEveryLine(this.getComentedStarLine(), myWriter);
         this.addNewLineAfterEveryLine("// LOCAL & GLOBAL DEFINITION", myWriter);
         this.addNewLineAfterEveryLine(this.getComentedStarLine(), myWriter);
         myWriter.append("\n");
         this.addNewLineAfterEveryLine("#ifdef GLOBAL", myWriter);
         this.addNewLineAfterEveryLine("#undef GLOBAL", myWriter);
         this.addNewLineAfterEveryLine("#endif", myWriter);
         myWriter.append("\n");
         this.addNewLineAfterEveryLine("#ifdef LOCAL", myWriter);
         this.addNewLineAfterEveryLine("#undef LOCAL", myWriter);
         this.addNewLineAfterEveryLine("#endif", myWriter);
         myWriter.append("\n");
         this.addNewLineAfterEveryLine("#define GLOBAL\textern", myWriter);
         this.addNewLineAfterEveryLine("#define LOCAL\tstatic", myWriter);
         myWriter.append("\n");
         this.addNewLineAfterEveryLine(this.getComentedStarLine(), myWriter);
         this.addNewLineAfterEveryLine("// CONFIGURATION PARAMETERS", myWriter);
         this.addNewLineAfterEveryLine(this.getComentedStarLine(), myWriter);
         myWriter.append("\n");
         this.getNVMDataToRead(myWriter);
         myWriter.append("\n");
         myWriter.append("\n");
         myWriter.append("#endif");
         myWriter.append(" ");
         myWriter.append("/");
         myWriter.append("*");
         myWriter.append(" ");
         myWriter.append("__NVM_CFG_H__");
         myWriter.append(" ");
         myWriter.append("*");
         myWriter.append("/");
         myWriter.close();
         frame.setLogDataShow((new File("")).getAbsolutePath() + "\\" + "src\\NVM\\components\\" + "Nvm_cfg.h" + " file created successfully.");
      } catch (IOException var4) {
         var4.printStackTrace();
      }

   }

   private void addNewLineAfterEveryLine(String line, FileWriter myWriter) {
      try {
         myWriter.append("\n");
         myWriter.append(line);
      } catch (IOException var4) {
         var4.printStackTrace();
      }

   }

   public void setData(ArrayList<ArrayList<String>> allData, ArrayList<String> comment) {
      this.name.clear();
      this.unit.clear();
      this.comment.clear();

      for(int i = 0; i < allData.size(); ++i) {
         for(int j = 0; j < ((ArrayList)allData.get(i)).size(); ++j) {
            if (j == 0) {
               this.name.add((String)((ArrayList)allData.get(i)).get(j));
            } else if (j == 1) {
               this.unit.add((String)((ArrayList)allData.get(i)).get(j));
            }
         }
      }

      Iterator var6 = comment.iterator();

      while(var6.hasNext()) {
         String s = (String)var6.next();
         this.comment.add(s);
      }

   }

   public String padRight(String s, String n, String j) {
      return String.format(this.newTabResult, s, n, j);
   }

   private void getNVMDataToRead(FileWriter myWriter) throws IOException {
      for(int i = 0; i < this.name.size(); ++i) {
         myWriter.append("#define " + this.padRight((String)this.name.get(i), (String)this.unit.get(i) + "U", "//" + (String)this.comment.get(i)));
         myWriter.append("\n");
      }

   }
}
