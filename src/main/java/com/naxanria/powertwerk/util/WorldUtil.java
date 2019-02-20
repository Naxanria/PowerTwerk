package com.naxanria.powertwerk.util;

import com.naxanria.powertwerk.PTSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.ArrayList;
import java.util.List;

public class WorldUtil
{
  public static int pulseEnergy(World world, BlockPos pos, EntityPlayer player)
  {
    int affected = 0;
    
    int radius = PTSettings.radius;
    boolean shared = PTSettings.sharedBetweenAll;
  
    List<TileEntity> toShare = new ArrayList<>();
    
    if (PTSettings.onlyHorizontal)
    {
      for (int x = -radius; x <= radius; x++)
      {
        for (int z = -radius; z <= radius; z++)
        {
          BlockPos checkPos = pos.add(x, 0, z);
          TileEntity tile = getPowerTile(world, checkPos);
          
          if (tile == null)
          {
            continue;
          }
          
          if (shared)
          {
            toShare.add(tile);
          }
          else
          {
            givePower(tile, PTSettings.powerGenerated);
            affected++;
          }
        }
      }
    }
    else
    {
      for (int x = -radius; x <= radius; x++)
      {
        for (int y = -radius; y <= radius; y++)
        {
          for (int z = -radius; z <= radius; z++)
          {
            BlockPos checkPos = pos.add(x, y, z);
            TileEntity tile = getPowerTile(world, checkPos);
    
            if (tile == null)
            {
              continue;
            }
    
            if (shared)
            {
              toShare.add(tile);
            }
            else
            {
              givePower(tile, PTSettings.powerGenerated);
              affected++;
            }
          }
        }
      }
    }
    
    if (shared && toShare.size() > 0)
    {
      int per = PTSettings.powerGenerated / toShare.size();
      affected = toShare.size();
  
      for (TileEntity tile :
        toShare)
      {
        givePower(tile, per);
      }
    }
    
    return affected;
  }
  
  private static void givePower(TileEntity tile, int amount)
  {
    for (EnumFacing facing :
      EnumFacing.values())
    {
      IEnergyStorage storage = tile.getCapability(CapabilityEnergy.ENERGY, facing);
      
      if (storage == null)
      {
        continue;
      }
      
      storage.receiveEnergy(amount, false);
      
      return;
    }
  }
  
  public static TileEntity getPowerTile(World world, BlockPos pos)
  {
    TileEntity t = world.getTileEntity(pos);
    
    if (t != null)
    {
      for (EnumFacing facing :
        EnumFacing.values())
      {
        if (t.hasCapability(CapabilityEnergy.ENERGY, facing))
        {
          return t;
        }
      }
    }
    
    return null;
  }
}
