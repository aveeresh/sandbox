package co.in.graspit.Enum;

import java.awt.Color;

public enum MyColor {
   PrimaryColorDark(new Color(165, 94, 234)),
   PrimaryColorLight(new Color(232, 232, 255)),
   SeconderyColorLight(new Color(250, 250, 255)),
   WhiteColor(new Color(255, 255, 255));

   public final Color color;

   private MyColor(Color color) {
      this.color = color;
   }
}
