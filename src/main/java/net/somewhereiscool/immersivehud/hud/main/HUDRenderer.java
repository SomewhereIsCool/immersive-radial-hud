package net.somewhereiscool.immersivehud.hud.main;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.somewhereiscool.immersivehud.ImmersiveRadialHUD;
import net.somewhereiscool.immersivehud.hud.crosshair.BubbleLayer;
import net.somewhereiscool.immersivehud.hud.crosshair.CrosshairHandler;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class HUDRenderer {
    @SubscribeEvent
    public static void renderCrosshair(RegisterGuiLayersEvent event) {
        ResourceLocation layerId = ResourceLocation.fromNamespaceAndPath(ImmersiveRadialHUD.MODID, "crosshair");
        event.registerAboveAll(layerId, new CrosshairHandler());

        ResourceLocation bubbleId = ResourceLocation.fromNamespaceAndPath(ImmersiveRadialHUD.MODID, "bubble_crosshair");
        event.registerAboveAll(bubbleId, new BubbleLayer());
    }
}
