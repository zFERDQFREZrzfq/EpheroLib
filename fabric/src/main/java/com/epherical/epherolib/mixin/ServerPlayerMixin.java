package com.epherical.epherolib.mixin;

import com.epherical.epherolib.objects.PlayerLanguage;
import net.minecraft.network.protocol.game.ServerboundClientInformationPacket;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin implements PlayerLanguage {

    @Unique
    private String language = "en_us";

    @Inject(method = "updateOptions", at = @At("TAIL"))
    public void epheroLibSetLangField(ServerboundClientInformationPacket serverboundClientInformationPacket, CallbackInfo ci) {
        this.language = serverboundClientInformationPacket.language();
    }

    @Override
    @Unique
    public String getLanguage() {
        return language;
    }

    @Override
    @Unique
    public void setLanguage(String language) {
        this.language = language;
    }
}
