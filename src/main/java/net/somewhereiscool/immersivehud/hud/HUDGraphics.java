package net.somewhereiscool.immersivehud.hud;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public class HUDGraphics extends Screen {
    private final KeyMapping keybind;
    private static final ResourceLocation SLOT = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/sprites/container/inventory/effect_background_small.png");
    private static final Integer MAX_RADIUS = 50;

    private int xCenter;
    private int yCenter;

    private double currentDegree;
    private double degreeFactor;
    private int degreeSelected;
    private boolean shouldGoToOffhand = false;

    ItemStack heldItem = Minecraft.getInstance().player.getMainHandItem();
    ItemStack offhandItem = Minecraft.getInstance().player.getOffhandItem();

    protected HUDGraphics(Component title) {
        super(title);
        this.keybind = HUDKeybinds.OPENHUDRADIAL;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
            shouldGoToOffhand = true;
            onClose();
            removed();
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

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
        currentDegree = -90;

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
        int xDiff = xCenter - mouseX; // Reversed xDiff to maintain screen coordinate consistency
        int yDiff = yCenter - mouseY; // Reversed yDiff to maintain screen coordinate consistency

        double angle = Math.toDegrees(Math.atan2(yDiff, xDiff));
        angle -= degreeFactor * 2;

        // Normalize the angle to [0, 360]
        if (angle < 0) {
            angle += 360;
        }

        // Determine the selected slot
        degreeSelected = (int) (angle / degreeFactor);

    }
}