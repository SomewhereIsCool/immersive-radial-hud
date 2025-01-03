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
 *  - Add graphics events for swim
 */

// Should be called when the player joins
public class CrosshairHandler implements LayeredDraw.Layer {
    private final static Minecraft mcInstance = Minecraft.getInstance();

    private static int xCenter;
    private static int yCenter;

    public CrosshairHandler() {}

    @Override
    public void render(GuiGraphics guiGraphics, @NotNull DeltaTracker deltaTracker) {
        xCenter = guiGraphics.guiWidth()/2;
        yCenter = guiGraphics.guiHeight()/2;
        renderHealthRadial(guiGraphics);
        renderHungerRadial(guiGraphics);
    }

    public void renderHealthRadial(GuiGraphics graphics) {
        assert mcInstance.player != null;
        float health = mcInstance.player.getHealth();
        graphics.blit(RenderType.CROSSHAIR, HUDCrosshairTextures.EMPTY_HEALTH_BAR, xCenter - 11, yCenter - 10, 0, 0, 4, 20, 4, 20,
                ARGB.color(100, 100, 100, 100));
        graphics.blit(RenderType.CROSSHAIR, HUDCrosshairTextures.FULL_HEALTH_BAR, xCenter - 11, yCenter - 10, 0, 0, 4, (int) health, 4, 20,
                ARGB.color(255, 255, 255, 255));

    }

    public void renderHungerRadial(GuiGraphics graphics) {
        assert mcInstance.player != null;
        float hunger = mcInstance.player.getFoodData().getFoodLevel();
        graphics.blit(RenderType.CROSSHAIR, HUDCrosshairTextures.EMPTY_HUNGER_BAR, xCenter + 6, yCenter -10, 0, 0, 4, 20, 4, 20,
                ARGB.color(100, 100, 100,100));
        graphics.blit(RenderType.CROSSHAIR, HUDCrosshairTextures.FULL_HUNGER_BAR, xCenter + 6, yCenter -10, 0, 0, 4, (int)hunger, 4, 20,
                ARGB.color(255, 255, 255,255));
    }

}