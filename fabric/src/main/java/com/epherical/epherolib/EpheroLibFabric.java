package com.epherical.epherolib;

import net.fabricmc.api.ModInitializer;

public class EpheroLibFabric implements ModInitializer {


    @Override
    public void onInitialize() {
        CommonPlatform.create(new FabricPlatform());

    }
}
