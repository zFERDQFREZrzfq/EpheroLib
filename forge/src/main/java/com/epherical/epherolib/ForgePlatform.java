package com.epherical.epherolib;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ForgePlatform extends CommonPlatform<ForgePlatform> {

    @Override
    public ForgePlatform getPlatform() {
        return this;
    }

    @Override
    public boolean isClientEnvironment() {
        return FMLEnvironment.dist == Dist.CLIENT;
    }

    @Override
    public boolean isServerEnvironment() {
        return FMLEnvironment.dist == Dist.DEDICATED_SERVER;
    }

    @Override
    public Path getRootConfigPath(String modID) {
        return FMLPaths.CONFIGDIR.get().resolve(modID);
    }

    @Override
    public Path getRootConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public String getPlayerLanguage(ServerPlayer player) {
        return player.getLanguage();
    }

}
