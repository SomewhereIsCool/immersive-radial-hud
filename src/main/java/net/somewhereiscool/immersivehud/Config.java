package net.somewhereiscool.immersivehud;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
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

    public static final ModConfigSpec.ConfigValue<Integer> hudDistance = BUILDER
            .comment("Adjusts the distance between the hud crossbars for hunger and health. Negative values further the distance while positive brings them closer.")
            .translation(ImmersiveRadialHUD.MODID + ".config.hudConfig")
            .defineInRange("hudConfig", 0, -200, 200);

    public static final ModConfigSpec.ConfigValue<Integer> radialDistance = BUILDER
            .comment("Adjust the radius size for the radial hotbar.")
            .translation(ImmersiveRadialHUD.MODID + ".config.radialDist")
            .defineInRange("radialDist", 50, 0, 100);

    public static final ModConfigSpec.ConfigValue<Boolean> showCrosshair = BUILDER
            .comment("Show crosshair while in radial mode.")
            .translation(ImmersiveRadialHUD.MODID + ".config.showCrosshair")
            .define("showCrosshair", true);

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
