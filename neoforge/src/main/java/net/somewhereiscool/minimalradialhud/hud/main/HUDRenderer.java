package net.somewhereiscool.minimalradialhud.hud.main;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.somewhereiscool.minimalradialhud.MinimalRadialHud;
import net.somewhereiscool.minimalradialhud.hud.crosshair.BubbleLayer;
import net.somewhereiscool.minimalradialhud.hud.crosshair.CrosshairHandler;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class HUDRenderer {
    @SubscribeEvent
    public static void renderCrosshair(RegisterGuiLayersEvent event) {
        ResourceLocation layerId = ResourceLocation.fromNamespaceAndPath(MinimalRadialHud.MODID, "crosshair");
        event.registerAboveAll(layerId, new CrosshairHandler());

        ResourceLocation bubbleId = ResourceLocation.fromNamespaceAndPath(MinimalRadialHud.MODID, "bubble_crosshair");
        event.registerAboveAll(bubbleId, new BubbleLayer());
    }
}
