package com.naxanria.powertwerk.handler;

import com.naxanria.powertwerk.PTSettings;
import com.naxanria.powertwerk.util.WorldUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlayerTwerkManager
{
  private boolean crouched = false;
  private int crouchTime = 0;
  
  public void update(EntityPlayer player)
  {
    if (player.ticksExisted < crouchTime)
    {
      crouchTime = 0;
    }
    
    if (player.isSneaking() && !crouched)
    {
      if (player.ticksExisted > crouchTime + PTSettings.timeBetweenTwerks)
      {
        crouchTime = player.ticksExisted;
        WorldUtil.pulseEnergy(player.world, player.getPosition(), player);
      }
    }
    
    crouched = player.isSneaking();
  }
}
