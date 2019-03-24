package com.naxanria.powertwerk.gui;

import com.naxanria.powertwerk.PowerTwerk;
import com.naxanria.powertwerk.handler.ConfigHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class ModConfigGui extends GuiConfig
{
  public ModConfigGui(GuiScreen parent)
  {
    super
    (
      parent,
      //new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.GENERAL)).getChildElements(),
      getElements(),
      PowerTwerk.MODID,
      false, false,
      PowerTwerk.NAME,
      ""
    );
  }
  
  private static List<IConfigElement> getElements()
  {
    List<IConfigElement> elements = new ArrayList<>();
    
    Configuration config = ConfigHandler.config;
  
    for (String cat :  config.getCategoryNames())
    {
//      if (cat.equals(ConfigHandler.GENERAL))
//      {
//        elements.addAll(new ConfigElement(config.getCategory(ConfigHandler.GENERAL)).getChildElements());
//      }
//      else
//      {
        elements.add(new ConfigElement(config.getCategory(cat)));
//      }
    }
    
    return elements;
  }
  
  
}
