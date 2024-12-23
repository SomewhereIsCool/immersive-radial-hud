package net.somewhereiscool.immersivehud.hud;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;


public class HUDGraphics extends Screen {
    private static final ResourceLocation SLOT = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/sprites/hud/hotbar_selection.png");
    private static final Integer MAX_RADIUS = 50;

    private int xCenter;
    private int yCenter;

    private double degreeFactor;
    private int degreeSelected;

    protected HUDGraphics(Component title) {
        super(title);
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
        xCenter = (this.width / 2);
        yCenter = (this.height / 2);
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
        degreeFactor = (double)360 / maxItems;
        double currentDegree = -90;

        for(int i = 0; i < maxItems; i++) {
            int xOffset = (int) (MAX_RADIUS * Math.cos(Math.toRadians(currentDegree)));
            int yOffset = (int) (MAX_RADIUS * Math.sin(Math.toRadians(currentDegree)));

            ItemStack item = this.minecraft.player.getInventory().getItem(i);
            // graphics.blit(RenderType.GUI_TEXTURED, SLOT, (xCenter + xOffset) - 12, (yCenter + yOffset) - 12, 0, 0, 24, 23, 24, 23, ARGB.color(255, 255, 255, 255));

            // Half is key
            graphics.renderItem(item, xCenter + xOffset - 8, yCenter + yOffset - 8, 0, -10);
            graphics.pose().pushPose();
            graphics.pose().translate(0,0, 200);

            if(!item.isEmpty() && item.getCount() > 1) {
                graphics.drawString(minecraft.font, Component.literal(String.valueOf(item.getCount())), xCenter + xOffset + 3, yCenter + yOffset + 1, ARGB.color(255, 255, 255, 255));
            }

            graphics.pose().popPose();
            currentDegree += degreeFactor;
        }

        calculateDesiredSlot(graphics, mouseX, mouseY);
    }

    public void calculateDesiredSlot(GuiGraphics graphics, int mouseX, int mouseY) {
        // Calculates the difference from the cursor to the center of the screen
        double angle = Math.toDegrees(Math.atan2(yCenter - mouseY, xCenter - mouseX));
        angle -= (90 - (degreeFactor/2));

        if (angle < 0) {
            angle += 360;
        }

        // Determine the selected slot
        degreeSelected = (int) (angle / degreeFactor); // should be good in terms of value
        ItemStack item = this.minecraft.player.getInventory().getItem(degreeSelected);

        if(!item.isEmpty()) {
            graphics.drawCenteredString(minecraft.font, Component.literal(item.getItem().getName().getString()), xCenter, yCenter + (this.height/4), ARGB.color(255, 255, 255, 255));
        }

        double angleToItem = (degreeFactor * degreeSelected) - (90);
        int xLength = (int) (MAX_RADIUS * Math.cos(Math.toRadians(angleToItem)));
        int yLength = (int) (MAX_RADIUS * Math.sin(Math.toRadians(angleToItem)));

        // Round
        graphics.blit(RenderType.GUI_TEXTURED, SLOT, (xCenter + xLength) - 12, (yCenter + yLength) - 12, 0, 0, 24, 23, 24, 23, ARGB.color(255, 255, 255, 255));
    }
}