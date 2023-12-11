package com.epherical.epherolib.client.widgets;

import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.resources.ResourceLocation;

public class DiscordButton extends ImageButton {
    private static final WidgetSprites TEXTURES = new WidgetSprites(
            new ResourceLocation("epherolib", "textures/gui/links/disc_logo"),
            new ResourceLocation("epherolib", "textures/gui/links/disc_logo_highlight"));

    public DiscordButton(int x, int y, OnPress onPress) {
        super(x, y, 23, 18, TEXTURES, onPress);
    }
}
