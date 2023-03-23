package com.the_beast_unleashed.chunkpurge;

import com.the_beast_unleashed.chunkpurge.operators.HandlerConfig;
import com.the_beast_unleashed.chunkpurge.proxy.ProxyCommon;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Tags.MODID, name = Tags.MODNAME, version = Tags.VERSION, acceptableRemoteVersions = "*")
public class ModChunkPurge {

    @Instance(Tags.MODID)
    public static ModChunkPurge instance;

    public static HandlerConfig config;
    public static org.apache.logging.log4j.Logger log;

    @SidedProxy(
            clientSide = "com.the_beast_unleashed.chunkpurge.proxy.ProxyClient",
            serverSide = "com.the_beast_unleashed.chunkpurge.proxy.ProxyCommon")
    public static ProxyCommon proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void load(FMLInitializationEvent event) {
        proxy.load(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        proxy.serverLoad(event);
    }

}
