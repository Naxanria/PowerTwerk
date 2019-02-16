package com.naxanria.powertwerk;

import com.naxanria.powertwerk.handler.ConfigHandler;
import com.naxanria.powertwerk.handler.EventHandler;
import com.naxanria.powertwerk.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod
(
  modid = PowerTwerk.MODID,
  name = PowerTwerk.NAME,
  version = PowerTwerk.VERSION,
  guiFactory = "com.naxanria." + PowerTwerk.MODID + ".gui.ModGuiFactory"
)
public class PowerTwerk
{
  public static final String MODID = "powertwerk";
  public static final String NAME = "Power Twerk";
  public static final String VERSION = "@VERSION@";
  
  public static final String PROXY = "com.naxanria." + MODID + ".proxy";
//
//  @Mod.Instance
//  public static PowerTwerk instance;

  @SidedProxy(clientSide = PROXY + ".ClientProxy", serverSide = PROXY + ".ServerProxy")
  public static CommonProxy proxy;
  
  public static Logger logger;
  
  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event)
  {
    logger = event.getModLog();
  
    File configFile = new File(event.getSuggestedConfigurationFile().getParent(), MODID + "/" + MODID + ".cfg");
    
    ConfigHandler.config = new Configuration(configFile);
    ConfigHandler.init();
    
    proxy.registerHandler(new EventHandler());
  }
}
