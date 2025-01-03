package net.somewhereiscool.immersivehud.hud.crosshair;

import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class InWaterEvent {
    private static Minecraft mcInstance = Minecraft.getInstance();
    private static int waterLevel;

    @SubscribeEvent
    public static boolean isUnderwater(PlayerTickEvent.Post event) {
        return false;
    }
}
