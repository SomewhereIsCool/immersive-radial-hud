package net.somewhereiscool.immersivehud;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = ImmersiveRadialHUD.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    /** TODO: Add config for:
    *   - Hide/show certain HUDs when immersive mode is on/off
     *  - Allow configuration of distance between two radial indicators
    */
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // TODO: Figure out how to show make config
    // TODO: Consider showing whole standard hud when Inventory is on
    /*
    private static ModConfigSpec.ConfigValue<Integer> hudDistance = BUILDER
            .comment("Adjusts the distance between the hud crossbars for hunger and health.");

     */
    private static ModConfigSpec.ConfigValue<Integer> radialDistance;

    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {

    }
}
