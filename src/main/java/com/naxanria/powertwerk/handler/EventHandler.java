package com.naxanria.powertwerk.handler;

import com.naxanria.powertwerk.PowerTwerk;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.UUID;

public class EventHandler
{
  HashMap<UUID, PlayerTwerkManager> states = new HashMap<>();
  
  @SubscribeEvent
  public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
  {
    EntityLivingBase entity = event.getEntityLiving();
    
    if (entity.world.isRemote || !(entity instanceof EntityPlayer))
    {
      return;
    }
    
    EntityPlayer player = (EntityPlayer) entity;
    
    UUID id = player.getUniqueID();
    
    PlayerTwerkManager state = states.get(id);
    state = state != null ? state : new PlayerTwerkManager();
    state.update(player);
    states.put(id, state);
  }
  
  @SubscribeEvent
  public void onConfigChanged(ConfigChangedEvent event)
  {
    if (event.getModID().equals(PowerTwerk.MODID))
    {
      ConfigHandler.config.save();
      ConfigHandler.init();
    }
  }
  
}
