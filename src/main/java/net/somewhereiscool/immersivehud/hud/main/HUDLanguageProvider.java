package net.somewhereiscool.immersivehud.hud.main;

import net.somewhereiscool.immersivehud.ImmersiveRadialHUD;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class HUDLanguageProvider extends LanguageProvider {
    public HUDLanguageProvider(PackOutput output) {
        super(output, ImmersiveRadialHUD.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add(ImmersiveRadialHUD.MODID + ".config.hudConfig", "Crosshair Distance");
        this.add(HUDKeybind.getCategory(), "HUD Keybinds");
    }
}
