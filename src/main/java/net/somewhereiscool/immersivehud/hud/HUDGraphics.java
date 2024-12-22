package net.somewhereiscool.immersivehud.hud;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;


public class HUDGraphics extends Screen {
    private final KeyMapping keybind;
    private static final ResourceLocation SLOT = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/sprites/container/inventory/effect_background_small.png");
    private static final Integer MAX_RADIUS = 50;

    private int xCenter;
    private int yCenter;

    private double degreeFactor;
    private int degreeSelected;

    protected HUDGraphics(Component title) {
        super(title);
        this.keybind = HUDKeybinds.OPENHUDRADIAL;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        onClose();
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    protected void renderBlurredBackground() {
        return;
    }

    @Override
    protected void init() {
        super.init();

        // Add widgets and precomputed values
        xCenter = (this.width / 2) - 8;
        yCenter = (this.height / 2) - 8;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void tick() {
        super.tick();

    }

    @Override
    public void onClose() {
        // Stop any handlers here
        this.minecraft.player.getInventory().setSelectedHotbarSlot(degreeSelected);

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
        degreeFactor = (double) 360 / maxItems;
        double currentDegree = -90;

        for(int i = 0; i < maxItems; i++) {
            int xOffset = (int) (MAX_RADIUS * Math.cos(Math.toRadians(currentDegree)));
            int yOffset = (int) (MAX_RADIUS * Math.sin(Math.toRadians(currentDegree)));

            ItemStack item = this.minecraft.player.getInventory().getItem(i);
            graphics.renderItem(item, xCenter + xOffset, yCenter + yOffset);

            currentDegree += degreeFactor;
        }

        calculateDesiredSlot(graphics, mouseX, mouseY);
    }

    public void calculateDesiredSlot(GuiGraphics graphics, int mouseX, int mouseY) {
        // Calculates the difference from the cursor to the center of the screen
        int xDiff = xCenter - mouseX;
        int yDiff = yCenter - mouseY;

        double angle = Math.toDegrees(Math.atan2(yDiff, xDiff));
        angle -= degreeFactor * 2;

        if (angle < 0) {
            angle += 360;
        }

        // Determine the selected slot
        degreeSelected = (int) (angle / degreeFactor);
    }
}