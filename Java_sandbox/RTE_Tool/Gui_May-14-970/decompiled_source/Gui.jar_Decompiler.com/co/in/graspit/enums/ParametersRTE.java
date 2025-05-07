package co.in.graspit.enums;

public enum ParametersRTE {
   PORTTYPE("Port-Type"),
   FUNCTYPE("Function-Type"),
   REQ_Comp_Type("Requirer-Component-Type"),
   REQ_Comp_Name("Requirer-Component-Name"),
   PRO_Comp_Type("Provider-Component-Type"),
   PRO_Comp_Name("Provider-Component-Name"),
   SUBFUNC("SubFunction"),
   RETTYPE("Return-Type"),
   RETVALUE("Return-Value"),
   BLOCKTYPE("BlockType"),
   BLOCKNAME("BlockName"),
   SERVERFUNCCALL("ServerFunctionCall"),
   ARGUMENT("Argument");

   public final String value;

   private ParametersRTE(String value) {
      this.value = value;
   }
}
