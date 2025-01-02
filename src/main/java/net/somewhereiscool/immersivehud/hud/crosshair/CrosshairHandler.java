package net.somewhereiscool.immersivehud.hud.crosshair;

import net.minecraft.client.Minecraft;

/**TODO: Implement features
 *  - Create graphics for health, hunger, XP, armor
 *  - Add graphics events for health, hunger, armor, swim
 *  - Decide if this class needs to be extended by a superclass
 *  - Fading animation
 *  - Lerp?
 */

// Should be called when the player joins
// @EventBusSubscriber(modid = ImmersiveRadialHUD.MODID, value = Dist.CLIENT)
public class CrosshairHandler {
    private static Minecraft mcInstance = Minecraft.getInstance();
    public static boolean loadedIn = false;

    private static int xCenter;
    private static int yCenter;

    public CrosshairHandler() {
    }


}
