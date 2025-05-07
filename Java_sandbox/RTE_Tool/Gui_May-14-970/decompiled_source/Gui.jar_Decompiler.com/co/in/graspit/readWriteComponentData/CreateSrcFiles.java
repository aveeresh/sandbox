package co.in.graspit.readWriteComponentData;

import co.in.graspit.GuiProject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CreateSrcFiles {
   private final String filesPathDotH = "src\\RTE\\components\\";
   private final String filesPathDotC = "src\\SWCS\\";
   private final String commentFirstLine = "/*****************************************";
   private final String lastLine = "*****************************************/";
   private final String creationLine = "*     Created on:";
   private final String authorLine = "*     Author:    ";
   private final String fileName = "*     File:    ";
   private final String schedularLine = "*     Schedular Mapping";
   private final String FunctionCalls = "*     Function Calls";
   private final String addCodeHere = "*     Add Your Code Here";
   private final String nextLineCmd = "\n";
   private final String tabCmd = "\t";
   private final String doubleQuat = "\"";
   private final String spaceCmd = " ";
   private final String commaCmd = ",";
   private final String semiCmd = ";";
   private final String openBracketCmd = "(";
   private final String closeBracketCmd = ")";
   private final String openCurlyBracket = "{";
   private final String closeCurlyBracket = "}";
   private final String voidCmd = "void";
   private final String underScore = "_";
   private final String star = "*";
   private final String dashCmd = "/";
   private final String SWC = "SWC";
   private final String SWCL = "SWCL";
   private final String SWCS = "SWCS";
   private final String RTE = "RTE";
   private final String Rte = "Rte";
   private final String unH = "_H_";
   private final String createdOn = "Created on: ";
   private final String author = "Author: ";
   private final String pathRteDotC = "../../RTE/components/";
   private final String doNotChangeThis = "DO NOT CHANGE THIS COMMENT!";
   private final String startIncludeAndDeclaration = "<< Start of include and declaration area >>";
   private final String endIncludeAndDeclaration = "<< End of include and declaration area >>";
   private final String startRunnableImpl = "<< Start of runnable implementation >>";
   private final String endRunnableImpl = "<< End of runnable implementation >>";
   private final String pathRteDotH = "../Rte.h";
   private final String pathRteTypeDotH = "../../RTE/Rte_Type.h";
   private final String schedularMapping = "Schedular Mapping";
   private final String APIPrototypes = "API prototypes";
   private final String rte_read = "Rte_Read_<p>_<d> (explicit S/R communication with isQueued = false)";
   private final String rte_isUpdate = "Rte_IsUpdated_<p>_<d> (explicit S/R communication with isQueued = false)";
   private final String rte_write = "Rte_Write_<p>_<d> (explicit S/R communication with isQueued = false)";
   private final String rte_call = "Rte_Call_<p>_<o> (unmapped) for synchronous C/S communication";
   private final String includeText = "#include";
   private final String ifndefText = "#ifndef";
   private final String defineText = "#define";
   private final String endifText = "#endif";
   private final String dotCText = ".c";
   private final String dotHText = ".h";
   private final String runnableText = "_RUNNABLE_";
   private final String callText = "_Call_";
   private final String suffHfileText = "_APPL_CODE";
   private final String functionText = "FUNC";
   private final String initText = "Init";
   private final String cyclicText = "Cyclic";
   private final int starCount = 117;
   FileWriter myWriter = null;
   private DocumentBuilder builder;
   private XPath xp;
   private HashMap<String, Object> compFileReadData = new HashMap();
   private final String[] sendRead = new String[]{"Read", "Write"};
   String[] keyValueNames = new String[]{"ComponentName", "COMPONENT_TYPE", "Cyclic-Time"};
   String[] portKeyValues = new String[]{"Port-Type", "Function-Type", "Required-Port", "SubFunction", "Return-Type", "Return-Value", "BlockType", "BlockName", "ServerFunctionCall", "Arg-Typeone", "Argone", "Arg-Typetwo", "Argtwo", "Arg-typethree", "Argthree"};

   private String getStartStarLine() {
      StringBuilder sb = new StringBuilder();
      sb.append("/");

      for(int i = 0; i <= 117; ++i) {
         sb.append("*");
      }

      return sb.toString();
   }

   private String getEndStarLine() {
      StringBuilder sb = new StringBuilder();
      sb.append(" ");

      for(int i = 0; i < 117; ++i) {
         sb.append("*");
      }

      sb.append("/");
      return sb.toString();
   }

   private void writeLine(String line) {
      try {
         if (this.myWriter == null) {
            System.out.println("Writer is NULL.");
            return;
         }

         this.myWriter.append("\n");
         this.myWriter.append(line);
      } catch (IOException var3) {
         var3.printStackTrace();
      }

   }

   public CreateSrcFiles(ArrayList<String> allComponentList) {
      try {
         this.builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
         this.xp = XPathFactory.newInstance().newXPath();
      } catch (ParserConfigurationException var3) {
         var3.printStackTrace();
         this.builder = null;
         this.xp = null;
      }

   }

   public void createFiles(GuiProject frame) {
      if (this.builder == null) {
         System.out.println("Doc Builder is NULL.");
      } else if (this.xp == null) {
         System.out.println("X Path is NULL.");
      } else {
         File docFile = new File("MAE_RteGen.xml");
         if (!docFile.exists()) {
            System.out.println("File Not Found: " + docFile.getAbsolutePath());
         } else {
            frame.setLogDataShow("File Path .h is " + (new File("")).getAbsolutePath() + "\\" + "src\\RTE\\components\\");
            frame.setLogDataShow("File Path .c is " + (new File("")).getAbsolutePath() + "\\" + "src\\SWCS\\");
            File mkDirDotH = new File("src\\RTE\\components\\\\");
            mkDirDotH.mkdirs();

            try {
               Document doc = this.builder.parse(docFile);
               String path = "//SW_Cs/SW_C/@abbreviation";
               NodeList nodeSupList = (NodeList)this.xp.compile(path).evaluate(doc, XPathConstants.NODESET);

               for(int i = 0; i < nodeSupList.getLength(); ++i) {
                  String abName = nodeSupList.item(i).getNodeValue();
                  String fileName = "SWC_" + abName;
                  File mkDirDotC = new File("src\\SWCS\\" + fileName + "\\");
                  mkDirDotC.mkdirs();
                  File cFile = new File("src\\SWCS\\" + fileName + "\\" + fileName + ".c");
                  File hFile = new File("src\\RTE\\components\\Rte_" + fileName + ".h");
                  String subPath = "//SW_Cs/SW_C[@abbreviation='" + nodeSupList.item(i).getNodeValue() + "']";
                  NodeList nodeList = (NodeList)this.xp.compile(subPath).evaluate(doc, XPathConstants.NODESET);
                  HashMap<String, NodeList> map = new HashMap();
                  String clientServerPath = "//SW_Cs/SW_C[@abbreviation='" + nodeSupList.item(i).getNodeValue() + "']/ClientServer";
                  map.put("ClientServer", (NodeList)this.xp.compile(clientServerPath).evaluate(doc, XPathConstants.NODESET));
                  String senderReceiverPath = "//SW_Cs/SW_C[@abbreviation='" + nodeSupList.item(i).getNodeValue() + "']/SenderReceiver";
                  map.put("SenderReceiver", (NodeList)this.xp.compile(senderReceiverPath).evaluate(doc, XPathConstants.NODESET));
                  String runnablePath = "//SW_Cs/SW_C[@abbreviation='" + nodeSupList.item(i).getNodeValue() + "']/Runnable";
                  map.put("Runnable", (NodeList)this.xp.compile(runnablePath).evaluate(doc, XPathConstants.NODESET));

                  try {
                     cFile.createNewFile();
                     hFile.createNewFile();
                  } catch (IOException var20) {
                     var20.printStackTrace();
                  }

                  this.writeMyCFile(cFile, nodeList, abName);
                  this.writeMyHFile(hFile, nodeList, abName, map);
               }
            } catch (IOException | XPathExpressionException | SAXException var21) {
               var21.printStackTrace();
            }

         }
      }
   }

   private void writeC(String name) throws IOException {
      if (this.myWriter != null) {
         this.myWriter.append("/*");
         this.writeLine(" *\tSWC_" + name + ".c");
         this.writeLine(" *");
         this.writeLine(" *\tCreated on: " + (new Date()).toString());
         this.writeLine(" *\tAuthor: PrashantGupta");
         this.writeLine(" */");
         this.writeLine("#include \"../../RTE/components/Rte_SWC_" + name + ".h" + "\"");
         this.writeLine(this.getStartStarLine());
         this.writeLine(" * DO NOT CHANGE THIS COMMENT!\t<< Start of include and declaration area >>\tDO NOT CHANGE THIS COMMENT!");
         this.writeLine(this.getEndStarLine());
         this.myWriter.append("\n\n\n");
         this.writeLine(this.getStartStarLine());
         this.writeLine(" * DO NOT CHANGE THIS COMMENT!\t<< End of include and declaration area >>\tDO NOT CHANGE THIS COMMENT!");
         this.writeLine(this.getEndStarLine());
         this.myWriter.append("\n\n");
         this.writeLine("FUNC(void, RTE_SWC_" + name.toUpperCase() + "_APPL_CODE" + ")" + "SWC" + "_" + name + "_" + "Cyclic05ms" + "(" + "void" + ")");
         this.writeLine("{");
         this.writeLine(this.getStartStarLine());
         this.writeLine(" * DO NOT CHANGE THIS COMMENT!\t<< Start of runnable implementation >>\tDO NOT CHANGE THIS COMMENT!");
         this.writeLine(" * Symbol: SWC_" + name + "_" + "Cyclic05ms");
         this.writeLine(this.getEndStarLine());
         this.myWriter.append("\n");
         this.writeLine(this.getStartStarLine());
         this.writeLine(" * DO NOT CHANGE THIS COMMENT!\t<< End of runnable implementation >>\tDO NOT CHANGE THIS COMMENT!");
         this.writeLine(this.getEndStarLine());
         this.myWriter.append("\n");
         this.writeLine("}");
         this.myWriter.append("\n");
         this.writeLine("FUNC(void, RTE_SWC_" + name.toUpperCase() + "_APPL_CODE" + ")" + "SWC" + "_" + name + "_" + "Init" + "(" + "void" + ")");
         this.writeLine("{");
         this.writeLine(this.getStartStarLine());
         this.writeLine(" * DO NOT CHANGE THIS COMMENT!\t<< Start of runnable implementation >>\tDO NOT CHANGE THIS COMMENT!");
         this.writeLine(" * Symbol: SWC_" + name + "_" + "Init");
         this.writeLine(this.getEndStarLine());
         this.myWriter.append("\n");
         this.writeLine(this.getStartStarLine());
         this.writeLine(" * DO NOT CHANGE THIS COMMENT!\t<< End of runnable implementation >>\tDO NOT CHANGE THIS COMMENT!");
         this.writeLine(this.getEndStarLine());
         this.myWriter.append("\n");
         this.writeLine("}");
      }
   }

   private void writeH(String name, NodeList nodeList, HashMap<String, NodeList> map) throws IOException {
      if (this.myWriter != null) {
         this.myWriter.append("/*");
         this.writeLine(" *\tRte_SWC_" + name + ".h");
         this.writeLine(" *");
         this.writeLine(" *\tCreated on: " + (new Date()).toString());
         this.writeLine(" *\tAuthor: PrashantGupta");
         this.writeLine(" */");
         this.writeLine("/* double include prevention */");
         this.writeLine("#ifndef RTE_SWCL_" + name.toUpperCase() + "_H_");
         this.writeLine("#define RTE_SWCL_" + name.toUpperCase() + "_H_");
         this.writeLine("#include \"../Rte.h\"");
         this.writeLine("#include \"../../RTE/Rte_Type.h\"");
         this.writeLine(this.getStartStarLine());
         this.writeLine(" * Schedular Mapping");
         this.writeLine(this.getEndStarLine());
         ArrayList<String> runnableStringList = new ArrayList();
         NodeList runnableList = (NodeList)map.get("Runnable");

         for(int i = 0; i < runnableList.getLength(); ++i) {
            Element element = (Element)runnableList.item(i);
            if (element.hasAttribute("cycle") && !element.getAttributes().getNamedItem("cycle").getNodeValue().equalsIgnoreCase("init")) {
               runnableStringList.add(element.getAttributes().getNamedItem("name").getNodeValue());
            }
         }

         if (runnableStringList.size() == 1) {
            this.writeLine("#define RTE_RUNNABLE_SWC_" + name + "_" + (String)runnableStringList.get(0) + "_" + "Cyclic05ms" + " " + "SWC" + "_" + name + "_" + (String)runnableStringList.get(0) + "_" + "Cyclic05ms");
         } else {
            this.writeLine("#define RTE_RUNNABLE_SWC_" + name + "_" + "Cyclic05ms" + " " + "SWC" + "_" + name + "_" + "Cyclic05ms");
         }

         this.myWriter.append("\n");
         this.writeLine(this.getStartStarLine());
         this.writeLine(" * API prototypes");
         this.writeLine(this.getEndStarLine());
         if (!name.equals("Mgr")) {
            NodeList clientServerList = (NodeList)map.get("ClientServer");
            if (clientServerList.getLength() > 0) {
               for(int i = 0; i < clientServerList.getLength(); ++i) {
                  NodeList nList = ((Element)clientServerList.item(i)).getElementsByTagName("Operation");

                  for(int j = 0; j < nList.getLength(); ++j) {
                     Node node = nList.item(j);
                     NodeList parameterList = ((Element)node).getElementsByTagName("Parameter");
                     StringBuilder sb = new StringBuilder();

                     for(int k = 0; k < parameterList.getLength(); ++k) {
                        NamedNodeMap param = parameterList.item(k).getAttributes();
                        if (k == 0) {
                           sb.append("(" + param.getNamedItem("type").getNodeValue() + "_" + param.getNamedItem("name").getNodeValue());
                        } else if (k + 1 == parameterList.getLength()) {
                           sb.append(", Ptr_" + param.getNamedItem("type").getNodeValue() + "_" + param.getNamedItem("name").getNodeValue());
                        } else {
                           sb.append(", " + param.getNamedItem("type").getNodeValue() + "_" + param.getNamedItem("name").getNodeValue());
                        }
                     }

                     sb.append(")");
                     String parameter = sb.toString();
                     this.writeLine("#define Rte_Call_SWCS_" + name + "_" + "UBSW" + "_" + "Mgr" + "_" + clientServerList.item(i).getAttributes().getNamedItem("interface").getNodeValue() + "_" + node.getAttributes().getNamedItem("name").getNodeValue() + parameter + "      " + "(" + node.getAttributes().getNamedItem("name").getNodeValue() + parameter + ", ((Std_ReturnType)RTE_E_OK))");
                  }
               }
            }

            NodeList senderReceiverList = (NodeList)map.get("SenderReceiver");
            if (senderReceiverList.getLength() > 0) {
               for(int i = 0; i < senderReceiverList.getLength(); ++i) {
                  Node node = senderReceiverList.item(i);

                  for(int j = 0; j < this.sendRead.length; ++j) {
                     String param = node.getAttributes().getNamedItem("base_type").getNodeValue().replace("Byte", node.getAttributes().getNamedItem("size").getNodeValue() + "Byte") + "_" + node.getAttributes().getNamedItem("name").getNodeValue();
                     String param2 = "Rte_SWCS_CCM_" + node.getAttributes().getNamedItem("name").getNodeValue();
                     if (j == 0) {
                        this.writeLine("#define Rte_" + this.sendRead[j] + "_" + "SWCS" + "_" + name + "_" + "SWCS" + "_" + "CCM" + "_" + node.getAttributes().getNamedItem("name").getNodeValue() + "(" + param + ")" + "      " + "(" + "(" + param + ")" + "= " + param2 + ", ((Std_ReturnType)RTE_E_OK))");
                     } else if (j == 1) {
                        this.writeLine("#define Rte_" + this.sendRead[j] + "_" + "SWCS" + "_" + name + "_" + "SWCS" + "_" + "CCM" + "_" + node.getAttributes().getNamedItem("name").getNodeValue() + "(" + param + ")" + "      " + "(" + "(" + param2 + ")" + "= " + param + ", ((Std_ReturnType)RTE_E_OK))");
                     }
                  }
               }
            }
         }

         this.myWriter.append("\n\n\n\n");
         this.writeLine(this.getStartStarLine());
         this.writeLine(" * Rte_Read_<p>_<d> (explicit S/R communication with isQueued = false)");
         this.writeLine(this.getEndStarLine());
         this.myWriter.append("\n");
         this.writeLine(this.getStartStarLine());
         this.writeLine(" * Rte_IsUpdated_<p>_<d> (explicit S/R communication with isQueued = false)");
         this.writeLine(this.getEndStarLine());
         this.myWriter.append("\n");
         this.writeLine(this.getStartStarLine());
         this.writeLine(" * Rte_Write_<p>_<d> (explicit S/R communication with isQueued = false)");
         this.writeLine(this.getEndStarLine());
         this.myWriter.append("\n");
         this.writeLine(this.getStartStarLine());
         this.writeLine(" * Rte_Call_<p>_<o> (unmapped) for synchronous C/S communication");
         this.writeLine(this.getEndStarLine());
         this.myWriter.append("\n");
         this.writeLine("FUNC(void, RTE_SWC_" + name + "_APPL_CODE" + ")" + "SWC" + "_" + name + "_" + "Cyclic05ms" + "(" + "void" + ")" + ";");
         this.writeLine("FUNC(void, RTE_SWC_" + name + "_APPL_CODE" + ")" + "SWC" + "_" + name + "_" + "Init" + "(" + "void" + ")" + ";");
         this.myWriter.append("\n");
         this.writeLine("#endif /* RTE_SWCL_" + name + "_H_" + " " + "*" + "/");
      }
   }

   private void writeMyCFile(File file, NodeList nodeList, String abName) {
      try {
         this.myWriter = new FileWriter(file);
         this.writeC(abName);
      } catch (IOException var13) {
         var13.printStackTrace();
      } finally {
         if (this.myWriter != null) {
            try {
               this.myWriter.close();
            } catch (IOException var12) {
               var12.printStackTrace();
            }
         }

      }

      this.myWriter = null;
   }

   private void writeMyHFile(File file, NodeList nodeList, String abName, HashMap<String, NodeList> map) {
      try {
         this.myWriter = new FileWriter(file);
         this.writeH(abName, nodeList, map);
      } catch (IOException var14) {
         var14.printStackTrace();
      } finally {
         if (this.myWriter != null) {
            try {
               this.myWriter.close();
            } catch (IOException var13) {
               var13.printStackTrace();
            }
         }

      }

      this.myWriter = null;
   }

   void parseComponentData(String componentName) {
      try {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document compBuilder = builder.parse(new File(componentName + ".xml"));
         this.compFileReadData.clear();
         compBuilder.getDocumentElement().normalize();
         Element root = compBuilder.getDocumentElement();
         System.out.println(root.getNodeName());
         NodeList nList = compBuilder.getElementsByTagName(componentName);
         System.out.println("============================");
         Element eElement = (Element)nList.item(0);
         this.compFileReadData.put(this.keyValueNames[0], componentName);
         String[] var11;
         int portsno = (var11 = this.keyValueNames).length;

         int totalports;
         for(totalports = 0; totalports < portsno; ++totalports) {
            String componentInfo = var11[totalports];
            if (eElement.getElementsByTagName(componentInfo).item(0) != null) {
               System.out.println("Component Info " + componentInfo);
               String compInfoText = eElement.getElementsByTagName(componentInfo).item(0).getTextContent();
               this.compFileReadData.put(componentInfo, compInfoText);
               System.out.println(compInfoText);
            }
         }

         ArrayList<Object> portList = new ArrayList();
         totalports = eElement.getElementsByTagName("Port").getLength();

         for(portsno = 0; portsno < totalports; ++portsno) {
            HashMap<String, String> portsInfo = new HashMap();
            Element tempElement = (Element)eElement.getElementsByTagName("Port").item(portsno);
            String[] var16;
            int var15 = (var16 = this.portKeyValues).length;

            for(int var14 = 0; var14 < var15; ++var14) {
               String portName = var16[var14];
               if (tempElement.getElementsByTagName(portName).item(0) != null) {
                  portsInfo.put(portName, tempElement.getElementsByTagName(portName).item(0).getTextContent());
               }
            }

            portList.add(portsInfo);
         }

         this.compFileReadData.put("Ports", portList);
      } catch (ParserConfigurationException var17) {
         var17.printStackTrace();
      } catch (IOException | SAXException var18) {
         var18.printStackTrace();
      }

   }

   void writeHFile(File hFile, String componentName, GuiProject frame) {
      try {
         FileWriter myWriter = new FileWriter(hFile);
         this.writeHeader("Prashant Gupta", myWriter);
         this.writeRequiredSchMappingMacros(myWriter);
         this.writeReqAPIPrototypes(myWriter);
         this.writeFunctions(myWriter);
         frame.setLogDataShow(componentName + ".h file created successfully.");
         myWriter.close();
      } catch (IOException var6) {
         var6.printStackTrace();
         frame.setLogDataShow(var6.getMessage());
      }

   }

   void writeHeader(String authorName, FileWriter currentCompFile) {
      try {
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
         LocalDateTime now = LocalDateTime.now();
         currentCompFile.append("/*****************************************\n");
         currentCompFile.append("*     Created on:\t");
         currentCompFile.append(dtf.format(now) + "\n");
         currentCompFile.append("*     Author:    \t" + authorName + "\n");
         currentCompFile.append("*****************************************/\n");
         System.out.println("Successfully wrote to the file.");
      } catch (IOException var5) {
         System.out.println("An error occurred.");
         var5.printStackTrace();
      }

   }

   void writeRequiredSchMappingMacros(FileWriter currentCompFile) {
      try {
         currentCompFile.append("\n\n");
         currentCompFile.append("#ifndef\t");
         String compName = (String)this.compFileReadData.get("ComponentName");
         String compType = (String)this.compFileReadData.get("COMPONENT_TYPE");
         String includeComp = compName + "_" + compType + "_H_";
         System.out.println("CompName" + compName);
         System.out.println("CompTaype" + compType);
         currentCompFile.append(includeComp);
         currentCompFile.append("\n");
         currentCompFile.append("#define\t");
         currentCompFile.append(includeComp);
         currentCompFile.append("\n");
         currentCompFile.append("/*****************************************\n");
         currentCompFile.append("*     Schedular Mapping\n");
         currentCompFile.append("*****************************************/\n\n");
         currentCompFile.append("#define\t");
         String cyclicTime = (String)this.compFileReadData.get("Cyclic-Time");
         String defineContText = compName + "_" + compType + "_" + "_RUNNABLE_" + cyclicTime;
         currentCompFile.append(defineContText + "\t");
         currentCompFile.append(compName + "_" + compType + "_");
         currentCompFile.append("\n");
         System.out.println("Successfully wrote to the file.");
      } catch (IOException var7) {
         System.out.println("An error occurred.");
         var7.printStackTrace();
      }

   }

   void writeReqAPIPrototypes(FileWriter currentCompFile) {
      try {
         currentCompFile.append("\n\n");
         currentCompFile.append("/*****************************************\n");
         currentCompFile.append("API prototypes\n");
         currentCompFile.append("*****************************************/\n\n");
         ArrayList<?> portList = (ArrayList)this.compFileReadData.get("Ports");
         String componentName = (String)this.compFileReadData.get("ComponentName");

         for(int portscnt = 0; portscnt < portList.size(); ++portscnt) {
            currentCompFile.append("#define\t");
            HashMap<?, ?> port = (HashMap)portList.get(portscnt);
            String funcType = (String)port.get("Function-Type");
            String requirePort = (String)port.get("Required-Port");
            String blockType = (String)port.get("BlockType");
            String blockName = (String)port.get("BlockName");
            String retType = (String)port.get("Return-Type");
            String subFunction = (String)port.get("SubFunction");
            String retVal = (String)port.get("Return-Value");
            String serverFCall = (String)port.get("ServerFunctionCall");
            String argType1 = (String)port.get("Arg-Typeone");
            String arg1 = (String)port.get("Argone");
            currentCompFile.append(funcType + "_" + requirePort + "_" + componentName + "_" + blockType + "_" + blockName + "_" + subFunction + "(" + arg1 + ")" + " ");
            currentCompFile.append("(" + serverFCall + "(" + " " + arg1 + "," + argType1 + ")" + "," + "(" + "(" + retType + ")" + retVal + ")" + ")" + "\n" + "\n");
         }
      } catch (IOException var16) {
         var16.printStackTrace();
      }

   }

   void writeFunctions(FileWriter currentCompFile) {
      String componentName = (String)this.compFileReadData.get("ComponentName");
      String componentType = (String)this.compFileReadData.get("COMPONENT_TYPE");
      String cyclicTime = (String)this.compFileReadData.get("Cyclic-Time");

      try {
         currentCompFile.append("/*****************************************\n");
         currentCompFile.append("*     Function Calls\n");
         currentCompFile.append("*****************************************/\n\n");
         currentCompFile.append("FUNC(void," + componentName + "_" + componentType + "_APPL_CODE" + ")" + "\n");
         currentCompFile.append(componentName + componentType + "Cyclic" + cyclicTime + "(" + "void" + ")" + "\n");
         currentCompFile.append("FUNC(void," + componentName + "_" + componentType + "_APPL_CODE" + ")" + "\n");
         currentCompFile.append(componentName + componentType + "Init" + "(" + "void" + ")" + "\n");
         currentCompFile.append("\n#endif");
      } catch (IOException var6) {
         var6.printStackTrace();
      }

   }

   void writeCFile(File cFile, String componentName, GuiProject frame) {
      try {
         FileWriter myWriter = new FileWriter(cFile);
         this.writeCFileHeader("Prashant Gupta", myWriter);
         this.writeCFileFunctions(myWriter);
         frame.setLogDataShow(componentName + ".c file created successfully.");
         myWriter.close();
      } catch (IOException var6) {
         var6.printStackTrace();
         frame.setLogDataShow(var6.getMessage());
      }

   }

   void writeCFileHeader(String authorName, FileWriter currentCompFile) {
      String componentName = (String)this.compFileReadData.get("ComponentName");

      try {
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
         LocalDateTime now = LocalDateTime.now();
         currentCompFile.append("/*****************************************\n");
         currentCompFile.append("*     File:    \t");
         currentCompFile.append(componentName + ".c" + "\n");
         currentCompFile.append("*     Created on:\t");
         currentCompFile.append(dtf.format(now) + "\n");
         currentCompFile.append("*     Author:    \t" + authorName + "\n");
         currentCompFile.append("*****************************************/\n");
         currentCompFile.append("#include " + componentName + ".h");
         System.out.println("Successfully wrote to the file.");
      } catch (IOException var6) {
         System.out.println("An error occurred.");
         var6.printStackTrace();
      }

   }

   void writeCFileFunctions(FileWriter currentCompFile) {
      String componentName = (String)this.compFileReadData.get("ComponentName");
      String componentType = (String)this.compFileReadData.get("COMPONENT_TYPE");
      String cyclicTime = (String)this.compFileReadData.get("Cyclic-Time");

      try {
         currentCompFile.append("\n/*****************************************\n");
         currentCompFile.append("*     Function Calls\n");
         currentCompFile.append("*****************************************/\n\n");
         currentCompFile.append("FUNC(void," + componentName + "_" + componentType + "_APPL_CODE" + ")" + "\n");
         currentCompFile.append(componentName + componentType + "Cyclic" + cyclicTime + "(" + "void" + ")" + "{" + "\n");
         currentCompFile.append("/*****************************************\n");
         currentCompFile.append("*     Add Your Code Here\n");
         currentCompFile.append("*****************************************/\n");
         currentCompFile.append("}\n\n");
         currentCompFile.append("FUNC(void," + componentName + "_" + componentType + "_APPL_CODE" + ")" + "\n");
         currentCompFile.append(componentName + componentType + "Init" + "(" + "void" + ")" + "{" + "\n");
         currentCompFile.append("/*****************************************\n");
         currentCompFile.append("*     Add Your Code Here\n");
         currentCompFile.append("*****************************************/\n");
         currentCompFile.append("}");
      } catch (IOException var6) {
         var6.printStackTrace();
      }

   }
}
