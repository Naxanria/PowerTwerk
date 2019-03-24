package com.naxanria.powertwerk.handler;

import com.naxanria.powertwerk.PTSettings;
import com.naxanria.powertwerk.util.WorldUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.FoodStats;
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
        
        if (PTSettings.useHunger)
        {
          if (player.getFoodStats().getFoodLevel() < PTSettings.minHunger)
          {
            return;
          }
        }
        
        int affected = WorldUtil.pulseEnergy(player.world, player.getPosition(), player);
        
        if (affected > 0 && PTSettings.useHunger)
        {
          FoodStats foodStats = player.getFoodStats();
          
          float hunger = PTSettings.hungerPerTwerk * (PTSettings.useHungerPerMachine ? affected : 1);
          foodStats.addExhaustion(hunger);
//
//          if (hunger > foodStats.getSaturationLevel())
//          {
//            hunger = ((int) (hunger - foodStats.getSaturationLevel()));
//            foodStats.setFoodSaturationLevel(0);
//          }
//
//          if (hunger > foodStats.getFoodLevel())
//          {
//            foodStats.setFoodLevel(0);
//          }
//          else
//          {
//            foodStats.setFoodLevel(foodStats.getFoodLevel() - hunger);
//          }
        }
      }
    }
    
    crouched = player.isSneaking();
  }
}
