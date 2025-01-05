package net.somewhereiscool.minimalradialhud.hud.main;

import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.somewhereiscool.minimalradialhud.MinimalRadialHud;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HUDKeybind {
    public static final String CATEGORY = "key.categories." + MinimalRadialHud.MODID;

    public static String getCategory() {
        return CATEGORY;
    }

    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        for (KeyMapping key : HUDKeybinds.getKeys()) {
            event.register(key);
        }
    }

}
