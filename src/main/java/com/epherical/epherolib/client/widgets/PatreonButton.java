package com.epherical.epherolib.client.widgets;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.resources.ResourceLocation;

public class PatreonButton extends ImageButton {
    private static final ResourceLocation TEXTURES = new ResourceLocation("auction_the_world", "textures/gui/elements.png");

    public PatreonButton(int x, int y, OnPress onPress) {
        super(x, y, 41, 18, 25, 0, TEXTURES, onPress);
    }


    @Override
    public void renderWidget(GuiGraphics graphics, int x, int y, float delta) {
        this.renderTexture(graphics, this.resourceLocation, this.getX(), this.getY(), this.xTexStart, this.yTexStart, this.yDiffTex, this.width, this.height, this.textureWidth, this.textureHeight);
    }
}
