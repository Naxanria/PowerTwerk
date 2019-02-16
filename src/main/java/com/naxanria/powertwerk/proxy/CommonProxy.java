package com.naxanria.powertwerk.proxy;

import com.naxanria.powertwerk.PowerTwerk;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy
{
  public abstract boolean isClient();
  
  public void registerHandler(Object obj)
  {
    PowerTwerk.logger.info("Registering event handler " + obj.getClass().getCanonicalName());
    
    MinecraftForge.EVENT_BUS.register(obj);
  }
}
