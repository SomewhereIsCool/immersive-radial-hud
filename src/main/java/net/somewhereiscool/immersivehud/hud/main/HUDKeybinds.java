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

    public static final KeyMapping OPENHUDRADIAL;
    public static final KeyMapping RADIALSETTINGS;
    public static final KeyMapping TOGGLEHUD;

    public static List<KeyMapping> getKeys() {
        return keys;
    }

    // TODO: Consider simpler keybinds
    static {
        OPENHUDRADIAL = new KeyMapping(
                "Open Radial HUD",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                HUDKeybind.getCategory()
        );
        RADIALSETTINGS = new KeyMapping(
                "HUD Settings",
                KeyConflictContext.UNIVERSAL,
                KeyModifier.ALT,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                HUDKeybind.getCategory()
        );
        TOGGLEHUD = new KeyMapping(
                "Toggle HUD",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_Y,
                HUDKeybind.getCategory()
        );

        keys.add(OPENHUDRADIAL);
        keys.add(RADIALSETTINGS);
        keys.add(TOGGLEHUD);
    }
}
