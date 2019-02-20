package com.naxanria.powertwerk.handler;

import com.naxanria.powertwerk.PTSettings;
import com.naxanria.powertwerk.PowerTwerk;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler
{
  public static Configuration config;
  
  public static final String GENERAL = Configuration.CATEGORY_GENERAL;
  public static final String HUNGER = "hunger";
  
  public static void init()
  {
    if (config == null)
    {
      PowerTwerk.logger.error("Config being attempted to be loaded before its initialized.");
      return;
    }
    
    config.load();
  
    PTSettings.powerGenerated = config.getInt("Power Generation", GENERAL, 42, 1, 10000,
      "The amount of power generated per twerk.");
    PTSettings.sharedBetweenAll = config.getBoolean("Shared Power", GENERAL, false,
      "Is the power generated per twerk shared between all receivers.");
    PTSettings.timeBetweenTwerks = config.getInt("Time Between Generation", GENERAL, 10, 1, 20 * 10,
      "The time between twerks before it can generate power again");
    PTSettings.radius = config.getInt("Radius", GENERAL, 1, 1, 6,
      "The radius around teh player to be able to charge.");
    PTSettings.onlyHorizontal = config.getBoolean("Only Horizontal", GENERAL, true,
      "Only check horizontal for blocks to power.");
    
    PTSettings.useHunger = config.getBoolean("Use Hunger", HUNGER, false,
      "Use hunger as a resource");
    PTSettings.hungerPerTwerk = config.getInt("Hunger Per Twerk", HUNGER, 1, 1, 20,
      "The hunger used per twerk.");
    PTSettings.minHunger = config.getInt("Minimum Hunger", HUNGER, 3, 0, 20,
      "The minimum amount of hunger to be able to power machines with twerking.");
    PTSettings.useHungerPerMachine = config.getBoolean("Hunger Per Machine", HUNGER, false,
      "Each machine affected drains the hunger instead of a set value");
    
    config.save();
  }
}
