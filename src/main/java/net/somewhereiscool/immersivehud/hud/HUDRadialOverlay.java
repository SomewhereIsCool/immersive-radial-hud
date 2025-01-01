package net.somewhereiscool.immersivehud.hud;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.event.InputEvent;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

public class HUDRadialOverlay extends Overlay {
    private static final ResourceLocation SLOT = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/sprites/hud/hotbar_selection.png");
    private static final Integer MAX_RADIUS = 50;
    private static Minecraft mcInstance;

    private static int xCenter;
    private static int yCenter;

    private static double degreeFactor;
    private static int degreeSelected;

    private static InputEvent.Key key;
    private static long window;

    protected HUDRadialOverlay(InputEvent.Key keyBind, Minecraft mc) {
        key = keyBind;
        mcInstance = mc;
        window = mc.getWindow().getWindow();
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
        mcInstance.mouseHandler.releaseMouse();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        checkKeyReleased();
        xCenter = graphics.guiWidth()/2;
        yCenter = graphics.guiHeight()/2;
        drawItems(graphics, mouseX, mouseY);
    }

    public static void checkKeyReleased() {
        if(!InputConstants.isKeyDown(window, key.getKey()) && mcInstance.getOverlay() instanceof HUDRadialOverlay) {
            mcInstance.setOverlay(null);

            assert mcInstance.player != null;
            mcInstance.player.getInventory().setSelectedHotbarSlot(degreeSelected);
            GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
            mcInstance.mouseHandler.grabMouse();
        }
    }

    public void drawItems(GuiGraphics graphics, int mouseX, int mouseY) {
        // Get items and make a circle out of it
        int maxItems = Inventory.SELECTION_SIZE;
        degreeFactor = (double)360 / maxItems;
        double currentDegree = -90;

        for(int i = 0; i < maxItems; i++) {
            int xOffset = (int) (MAX_RADIUS * Math.cos(Math.toRadians(currentDegree)));
            int yOffset = (int) (MAX_RADIUS * Math.sin(Math.toRadians(currentDegree)));

            assert Minecraft.getInstance().player != null;
            ItemStack item = Minecraft.getInstance().player.getInventory().getItem(i);

            graphics.renderItem(item, xCenter + xOffset - 8, yCenter + yOffset - 8, 0, -10);
            graphics.pose().pushPose();
            graphics.pose().translate(0,0, 200);

            if(!item.isEmpty() && item.getCount() > 1) {
                graphics.drawString(Minecraft.getInstance().font, Component.literal(String.valueOf(item.getCount())), xCenter + xOffset + 3, yCenter + yOffset + 1, ARGB.color(255, 255, 255, 255));
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
        assert Minecraft.getInstance().player != null;
        ItemStack item = Minecraft.getInstance().player.getInventory().getItem(degreeSelected);

        if(!item.isEmpty()) {
            graphics.drawCenteredString(Minecraft.getInstance().font, Component.literal(item.getItem().getName().getString()), xCenter, yCenter + (graphics.guiHeight()/4), ARGB.color(255, 255, 255, 255));
        }

        double angleToItem = (degreeFactor * degreeSelected) - (90);
        int xLength = (int) (MAX_RADIUS * Math.cos(Math.toRadians(angleToItem)));
        int yLength = (int) (MAX_RADIUS * Math.sin(Math.toRadians(angleToItem)));

        graphics.blit(RenderType.GUI_TEXTURED, SLOT, (xCenter + xLength) - 12, (yCenter + yLength) - 12, 0, 0, 24, 23, 24, 23, ARGB.color(255, 255, 255, 255));
    }
}