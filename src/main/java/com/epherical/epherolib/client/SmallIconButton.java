package com.epherical.epherolib.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class SmallIconButton extends Button {

    private static final WidgetSprites BUTTONS = new WidgetSprites(
            new ResourceLocation("epherolib", "widgets/small_button"),
            new ResourceLocation("epherolib", "widgets/small_button_highlighted"));

    private static final WidgetSprites ARROWS = new WidgetSprites(
            new ResourceLocation("epherolib", "widgets/arrow_down"),
            new ResourceLocation("epherolib", "widgets/arrow_up"));


    public static final ResourceLocation ELEMENTS = new ResourceLocation("epherolib", "textures/gui/elements.png");


    public boolean opened = false;
    private Icon icon;


    protected SmallIconButton(int $$0, int $$1, int $$2, int $$3, Component $$4, OnPress $$5, CreateNarration $$6, Icon icon) {
        super($$0, $$1, $$2, $$3, $$4, $$5, $$6);
        this.icon = icon;
    }

    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        int offset = this.getTextureY();
        graphics.blitSprite(BUTTONS.get(this.active, this.isHoveredOrFocused()), this.getX(), this.getY(), this.getWidth(), this.getHeight());
        if (icon == Icon.INCREMENT) {
            graphics.blitSprite(ARROWS.disabled(), this.getX(), this.getY(), 5, 5);
        } else {
            graphics.blitSprite(ARROWS.enabled(), this.getX(), this.getY(), 5, 5);
        }
        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private int getTextureY() {
        int offset = 1;
        if (!this.active) {
            offset = 0;
        } else if (this.isHovered()) {
            offset = 2;
        }

        return offset;
    }

    public static Builder buttonBuilder(@NotNull Component component, @NotNull OnPress press) {
        return new Builder(component, press);
    }

    public static class Builder {
        private final Component message;
        private final OnPress onPress;
        @Nullable
        private Tooltip tooltip;
        private int x;
        private int y;
        private int width = 9;
        private int height = 9;
        private CreateNarration createNarration = Button.DEFAULT_NARRATION;

        private Icon icon = Icon.INCREMENT;

        public Builder(Component $$0, OnPress $$1) {
            this.message = $$0;
            this.onPress = $$1;
        }

        public Builder pos(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder setIcon(Icon icon) {
            this.icon = icon;
            return this;
        }

        public Builder tooltip(@Nullable Tooltip $$0) {
            this.tooltip = $$0;
            return this;
        }

        public Builder createNarration(CreateNarration $$0) {
            this.createNarration = $$0;
            return this;
        }

        public SmallIconButton build() {
            SmallIconButton button = new SmallIconButton(this.x, this.y, this.width, this.height, this.message, this.onPress, this.createNarration, icon);
            button.setTooltip(this.tooltip);
            return button;
        }
    }

    public boolean isOpened() {
        return opened;
    }
}
