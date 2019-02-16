package com.naxanria.powertwerk.proxy;

public class ServerProxy extends CommonProxy
{
  @Override
  public boolean isClient()
  {
    return false;
  }
}
