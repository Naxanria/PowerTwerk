package com.naxanria.powertwerk.gui;

import com.naxanria.powertwerk.PowerTwerk;
import com.naxanria.powertwerk.handler.ConfigHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ModConfigGui extends GuiConfig
{
  public ModConfigGui(GuiScreen parent)
  {
    super
    (
      parent,
      new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
      PowerTwerk.MODID,
      false, false,
      PowerTwerk.NAME,
      ""
    );
  }
}
