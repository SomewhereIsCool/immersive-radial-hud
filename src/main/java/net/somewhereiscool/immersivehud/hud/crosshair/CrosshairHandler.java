package net.somewhereiscool.immersivehud.hud.crosshair;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.ARGB;
import org.jetbrains.annotations.NotNull;

/**TODO: Implement features
 *  - Move Armor and XP hud to inventory
 *  - Show new health and hunger bar
 *  - Add graphics events for health, hunger, armor, swim
 *  - Lerp?
 */

// Should be called when the player joins
public class CrosshairHandler implements LayeredDraw.Layer {
    private static Minecraft mcInstance = Minecraft.getInstance();
    private static GuiGraphics guiGraphics;

    private static int xCenter;
    private static int yCenter;
    private static int health;
    private static int hunger;

    public CrosshairHandler() {}

    public void updateHUDStats() {}

    @Override
    public void render(GuiGraphics guiGraphics, @NotNull DeltaTracker deltaTracker) {
        xCenter = guiGraphics.guiWidth()/2;
        yCenter = guiGraphics.guiHeight()/2;
        renderHealthRadial(guiGraphics);
        renderHungerRadial(guiGraphics);
    }

    public void renderHealthRadial(GuiGraphics graphics) {
        graphics.blit(RenderType.CROSSHAIR, HUDCrosshairTextures.EMPTY_HEALTH_BAR, xCenter - 8, yCenter -10, 0, 0, 4, 20, 4, 20, ARGB.color(255, 255, 255,255));
    }

    public void renderHungerRadial(GuiGraphics graphics) {
        graphics.blit(RenderType.CROSSHAIR, HUDCrosshairTextures.EMPTY_HUNGER_BAR, xCenter + 4, yCenter -10, 0, 0, 4, 20, 4, 20, ARGB.color(255, 255, 255,255));
    }

}