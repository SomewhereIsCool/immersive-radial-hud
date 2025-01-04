package net.somewhereiscool.immersivehud.hud.crosshair;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.horse.Horse;
import net.somewhereiscool.immersivehud.hud.main.HUDManager;
import net.somewhereiscool.immersivehud.hud.radial.HUDRadialOverlay;
import org.jetbrains.annotations.NotNull;

/**TODO: Implement features
 *  - Move Armor and XP hud to inventory
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

        // Do not render if HUDRadialOverlay is shown
        if(checkOverlayAllowed()) {
            renderHealthRadial(guiGraphics);
            renderHungerRadial(guiGraphics);
            renderAbsorptionRadial(guiGraphics);
            renderVehicleHealth(guiGraphics);
        }
    }

    public static boolean checkOverlayAllowed() {
        if(mcInstance.getOverlay() instanceof HUDRadialOverlay) {
            return false;
        }
        assert mcInstance.player != null;
        if(mcInstance.player.isCreative()) {
            return false;
        }
        if(mcInstance.player.isSpectator()) {
            return false;
        }
        if(mcInstance.options.hideGui) {
            return false;
        }
        if(HUDManager.isHudEnabled()) {
            return false;
        }
        return true;
    }

    public void renderAbsorptionRadial(GuiGraphics graphics) {
        assert mcInstance.player != null;
        float absorption = Math.min(mcInstance.player.getAbsorptionAmount(), 20);
        graphics.blit(RenderType.CROSSHAIR, HUDCrosshairTextures.ABSORPTION, xCenter - 13, yCenter - 11, 0,0, 4, (int)absorption, 4, 20,
                ARGB.color(255, 51, 255, 207));
    }

    public void renderHealthRadial(GuiGraphics graphics) {
        assert mcInstance.player != null;
        float health = mcInstance.player.getHealth();
        graphics.blit(RenderType.CROSSHAIR, HUDCrosshairTextures.EMPTY_HEALTH_BAR, xCenter - 13, yCenter - 11, 0, 0, 4, 20, 4, 20,
                ARGB.color(100, 100, 100, 100));
        graphics.blit(RenderType.CROSSHAIR, HUDCrosshairTextures.FULL_HEALTH_BAR, xCenter - 13, yCenter - 11, 0, 0, 4, (int) health, 4, 20,
                ARGB.color(255, 255, 255, 255));

    }

    public void renderHungerRadial(GuiGraphics graphics) {
        assert mcInstance.player != null;
        float hunger = mcInstance.player.getFoodData().getFoodLevel();
        if(mcInstance.player.getVehicle() == null) {
            graphics.blit(RenderType.CROSSHAIR, HUDCrosshairTextures.EMPTY_HUNGER_BAR, xCenter + 8, yCenter - 11, 0, 0, 4, 20, 4, 20,
                    ARGB.color(100, 100, 100,100));
            graphics.blit(RenderType.CROSSHAIR, HUDCrosshairTextures.FULL_HUNGER_BAR, xCenter + 8, yCenter - 11, 0, 0, 4, (int)hunger, 4, 20,
                    ARGB.color(255, 255, 255,255));
        }

    }

    public void renderVehicleHealth(GuiGraphics graphics) {
        assert mcInstance.player != null;
        if(mcInstance.player.getVehicle() != null) {
            float vehicleHealth = 0;
            if (mcInstance.player.getVehicle() instanceof Horse horse) {
                vehicleHealth = horse.getHealth();
            } else if (mcInstance.player.getVehicle() instanceof Camel camel) {
                vehicleHealth = camel.getHealth();
            }
            if(vehicleHealth >= 20) {
                graphics.blit(RenderType.CROSSHAIR, HUDCrosshairTextures.VEHICLE, xCenter + 8, yCenter - 11, 0, 0, 4, 20, 4, 20);
                graphics.blit(RenderType.CROSSHAIR, HUDCrosshairTextures.VEHICLE_SECOND, xCenter + 7, yCenter - 12, 0, 0, 6, (int)vehicleHealth % 20, 6, 22);
            } else {
                graphics.blit(RenderType.CROSSHAIR, HUDCrosshairTextures.VEHICLE, xCenter + 8, yCenter - 11, 0, 0, 4, (int)vehicleHealth, 4, 20);
            }
        }

    }

}