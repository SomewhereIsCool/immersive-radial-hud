package net.somewhereiscool.immersivehud.hud;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.event.ScreenEvent;

import java.awt.event.KeyEvent;

import static net.minecraft.world.item.Items.BIRCH_LOG;

public class HUDGraphics extends Screen {
    private final KeyMapping keybind;
    private static final ResourceLocation CROSSHAIR = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/sprites/hud/crosshair.png");
    private static final ResourceLocation SLOT = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/sprites/container/inventory/effect_background_small.png");
    private static final Integer RADIUS = 50;

    int xCenter;
    int yCenter;

    protected HUDGraphics(Component title) {
        super(title);
        this.keybind = HUDKeybinds.OPENHUDRADIAL;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void renderBlurredBackground() {
        return;
    }

    @Override
    protected void init() {
        super.init();

        xCenter = (this.width / 2) - 8;
        yCenter = (this.height / 2) - 8;

        // Add widgets and precomputed values
    }

    @Override
    public void tick() {
        super.tick();

    }

    @Override
    public void onClose() {
        // Stop any handlers here

        // Call last in case it interferes with the override
        super.onClose();
    }

    @Override
    public void removed() {
        // Reset initial states here

        // Call last in case it interferes with the override
        super.removed();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        drawItems(graphics, mouseX, mouseY);

        // super.render(graphics, mouseX, mouseY, partialTick);
    }



    public void drawItems(GuiGraphics graphics, int mouseX, int mouseY) {
        // Get items and make a circle out of it
        // I would need to access the current players inventory

        int maxItems = Inventory.SELECTION_SIZE;
        double degreeFactor = (double) 360 / maxItems;
        double currentDegree = -90;

        for(int i = 0; i < maxItems; i++) {

            int xOffset = (int) (RADIUS * Math.cos(Math.toRadians(currentDegree)));
            int yOffset = (int) (RADIUS * Math.sin(Math.toRadians(currentDegree)));

            ItemStack item = this.minecraft.player.getInventory().getItem(i);
            graphics.renderItem(item, xCenter + xOffset, yCenter + yOffset);

            currentDegree += degreeFactor;
        }
    }


    public void calculateDesiredSlot(GuiGraphics graphics, int mouseX, int mouseY) {
        // Calculates the angle by getting difference from cursor and center of the screen
        int xDiff = xCenter-mouseX;
        int yDiff = yCenter-mouseY;

        double angle = Math.tan((double) yDiff/xDiff);


    }
}