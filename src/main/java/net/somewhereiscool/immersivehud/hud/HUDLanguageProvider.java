package net.somewhereiscool.immersivehud.hud;

import net.somewhereiscool.immersivehud.ImmersiveRadialHUD;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

// @EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class HUDLanguageProvider extends LanguageProvider {
    public HUDLanguageProvider(PackOutput output) {
        super(output, ImmersiveRadialHUD.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add(HUDKeybind.getCategory(), "HUD Keybinds");
    }
}
