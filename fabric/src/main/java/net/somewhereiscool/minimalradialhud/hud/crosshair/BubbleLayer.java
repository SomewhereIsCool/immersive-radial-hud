package net.somewhereiscool.minimalradialhud.hud.crosshair;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.somewhereiscool.minimalradialhud.Config;
import org.jetbrains.annotations.NotNull;

import static net.somewhereiscool.minimalradialhud.hud.crosshair.CrosshairHandler.xLeftCenterOffset;

//TODO: Simplify this code
@EventBusSubscriber
public class BubbleLayer implements LayeredDraw.Layer {
    private static final Minecraft mcInstance = Minecraft.getInstance();
    private static boolean isUnderwater;
    private static int xCenter;
    private static int yCenter;
    private static BubbleState bubbleState;

    enum BubbleState {
        RESTORED,
        SHRINK,
        GROW,
        POP
    }

    @SubscribeEvent
    public static void isUnderwater(PlayerTickEvent.Post event) {
        assert mcInstance.player != null;
        if(event.getEntity() instanceof LocalPlayer player) {
            isUnderwater = player.isUnderWater();
        }
    }

    public static int getPlayerWaterLevel() {
        assert mcInstance.player != null;
        return mcInstance.player.getAirSupply();
    }

    public static void handlePlayerUnderwater() {
        int waterLevel = getPlayerWaterLevel();
        if(isUnderwater) {
            if(waterLevel >= 1) {
                bubbleState = BubbleState.SHRINK;
            } else if(waterLevel == 0) {
                bubbleState = BubbleState.POP;
            }
        } else {
            assert mcInstance.player != null;
            if (waterLevel < mcInstance.player.getMaxAirSupply()) {
                bubbleState = BubbleState.GROW;
            } else {
                bubbleState = BubbleState.RESTORED;
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, @NotNull DeltaTracker deltaTracker) {
        xCenter = guiGraphics.guiWidth()/2;
        yCenter = guiGraphics.guiHeight()/2;

        handlePlayerUnderwater();
        switch(bubbleState) {
            case RESTORED, POP -> {
                return;
            }
            case GROW, SHRINK -> {
            }
        }

        showBubble(guiGraphics);

    }

    public void showBubble(GuiGraphics guiGraphics) {
        int totalTicks = 320;
        int totalPixels = 22;
        int ticksPerPixel = totalTicks / totalPixels;

        int currentAirSupply = getPlayerWaterLevel() + 20;
        int bubbleHeight = Math.min(currentAirSupply / ticksPerPixel, totalPixels);
        if(CrosshairHandler.checkOverlayAllowed())  {
            guiGraphics.blit(RenderType.CROSSHAIR, HUDCrosshairTextures.BUBBLE, xCenter - 14 + Config.hudDistance.get() + xLeftCenterOffset, yCenter - CrosshairHandler.yCenterOffset - 1, 0, 0, 6, bubbleHeight, 6, 22);    }
        }

}
