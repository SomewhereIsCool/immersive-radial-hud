package net.somewhereiscool.minimalradialhud.hud.radial;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public class HUDRadialOverlay extends Overlay {
    //private static final ResourceLocation SLOT = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/sprites/hud/hotbar_selection.png");
    private static Integer MAX_RADIUS;

    private static int xCenter;
    private static int yCenter;

    private static double degreeFactor;
    private static int degreeSelected;

    //private static InputEvent.Key key;
    private static long window;

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // MAX_RADIUS = Config.radialDistance.get();
        checkKeyReleased();
        xCenter = context.getScaledWindowWidth()/2;
        yCenter = context.getScaledWindowHeight()/2;
        drawItems(context, mouseX, mouseY);
    }

    public void drawItems(DrawContext context, int mouseX, int mouseY) {
        // Get items and make a circle out of it
        assert MinecraftClient.getInstance().player != null;
        int maxItems = MinecraftClient.getInstance().player.getInventory().size();
        degreeFactor = (double)360 / maxItems;
        double currentDegree = -90;

        for(int i = 0; i < maxItems; i++) {
            int xOffset = (int) (MAX_RADIUS * Math.cos(Math.toRadians(currentDegree)));
            int yOffset = (int) (MAX_RADIUS * Math.sin(Math.toRadians(currentDegree)));

            // Get items
            ItemStack item = MinecraftClient.getInstance().player.getInventory().getStack(i);

            // Render items
            context.drawItem(item, xCenter, yCenter);
            // context.renderItem(item, xCenter + xOffset - 8, yCenter + yOffset - 8, 0, -10);


            // Put numbers
            /*
            if(!item.isEmpty() && item.getCount() > 1) {
                //TODO: Make sure this works for items that reach 3 digits
                if(item.getCount() > 9) {
                    graphics.drawCenteredString(Minecraft.getInstance().font, Component.literal(String.valueOf(item.getCount())), xCenter + xOffset + 3, yCenter + yOffset + 1, ARGB.color(255, 255, 255, 255));
                } else {
                    graphics.drawString(Minecraft.getInstance().font, Component.literal(String.valueOf(item.getCount())), xCenter + xOffset + 3, yCenter + yOffset + 1, ARGB.color(255, 255, 255, 255));
                }
            }

             */

            currentDegree += degreeFactor;
        }

        // calculateDesiredSlot(graphics, mouseX, mouseY);
    }

    public static void checkKeyReleased() {

    }

}
