package net.somewhereiscool.immersivehud.hud.main;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyModifier;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public final class HUDKeybinds {
    private static final List<KeyMapping> keys = new ArrayList<>();

    public static final KeyMapping OPENHUDRADIAL = new KeyMapping(
            "Open Radial HUD",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            HUDKeybind.getCategory()
    );
    public static final KeyMapping TOGGLEHUD = new KeyMapping(
            "Toggle HUD",
            KeyConflictContext.UNIVERSAL,
            KeyModifier.ALT,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            HUDKeybind.getCategory()
    );

    static {
        keys.add(OPENHUDRADIAL);
        keys.add(TOGGLEHUD);
    }

    public static List<KeyMapping> getKeys() {
        return keys;
    }
}
