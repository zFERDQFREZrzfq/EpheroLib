package com.epherical.epherolib;

import com.epherical.epherolib.config.LanguageConfig;
import net.fabricmc.api.ModInitializer;

public class EpheroLibFabric implements ModInitializer {


    @Override
    public void onInitialize() {
        CommonPlatform.create(new FabricPlatform());
        LanguageConfig config = new LanguageConfig("translations");
        config.loadTranslations(ModConstants.MOD_ID, "en_us");

    }
}
