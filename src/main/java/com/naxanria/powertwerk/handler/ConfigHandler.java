package com.naxanria.powertwerk.handler;

import com.naxanria.powertwerk.PTSettings;
import com.naxanria.powertwerk.PowerTwerk;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler
{
  public static Configuration config;
  
  public static void init()
  {
    if (config == null)
    {
      PowerTwerk.logger.error("Config being attempted to be loaded before its initialized.");
      return;
    }
    
    config.load();
  
    PTSettings.powerGenerated = config.getInt("Power Generation", Configuration.CATEGORY_GENERAL, 42, 1, 10000,
      "The amount of power generated per twerk.");
    PTSettings.sharedBetweenAll = config.getBoolean("Shared Power", Configuration.CATEGORY_GENERAL, false,
      "Is the power generated per twerk shared between all receivers.");
    PTSettings.timeBetweenTwerks = config.getInt("Time Between Generation", Configuration.CATEGORY_GENERAL, 10, 1, 20 * 10,
      "The time between twerks before it can generate power again");
    PTSettings.radius = config.getInt("Radius", Configuration.CATEGORY_GENERAL, 1, 1, 6,
      "The radius around teh player to be able to charge.");
    PTSettings.onlyHorizontal = config.getBoolean("Only Horizontal", Configuration.CATEGORY_GENERAL, true,
      "Only check horizontal for blocks to power.");
    
    config.save();
  }
}
