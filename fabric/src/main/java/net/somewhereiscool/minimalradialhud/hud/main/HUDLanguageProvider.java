package net.somewhereiscool.minimalradialhud.hud.main;

import net.somewhereiscool.minimalradialhud.MinimalRadialHud;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class HUDLanguageProvider extends LanguageProvider {
    public HUDLanguageProvider(PackOutput output) {
        super(output, MinimalRadialHud.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add(MinimalRadialHud.MODID + ".config.hudConfig", "Crosshair Distance");
        this.add(HUDKeybind.getCategory(), "HUD Keybinds");
    }
}
