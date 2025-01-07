package net.somewhereiscool.minimalradialhud.hud.main;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.somewhereiscool.minimalradialhud.MinimalRadialHud;
import net.somewhereiscool.minimalradialhud.hud.radial.HUDRadialOverlay;
import net.somewhereiscool.minimalradialhud.mixin.GuiMixin;

import java.util.Objects;

/**
* The "control center" for the HUD. It manages when graphics should be applied,
* looks for events to apply to HUDRadialOverlay. Also toggles the HUD display.
*
*
 */
@EventBusSubscriber(modid = MinimalRadialHud.MODID)
public class HUDManager {
    private static final Minecraft mcInstance = Minecraft.getInstance();
    private static final long window = mcInstance.getWindow().getWindow();
    private static boolean hudEnabled = false;

    @SubscribeEvent
    public static void handleKeyPresses(InputEvent.Key key) {
        if(pressAllowed()) {
            checkKeyPresses(key);
        }
    }

    public static boolean pressAllowed() {
        return mcInstance.screen == null;
    }

    public static void checkKeyPresses(InputEvent.Key key) {
        if(key.getKey() == HUDKeybinds.OPENHUDRADIAL.getKey().getValue()) {
            // Render just the items
            Minecraft.getInstance().setOverlay(new HUDRadialOverlay(key, mcInstance));
        }
        if(InputConstants.isKeyDown(window, key.getKey())) {
            // Toggle HUD display
            if(key.getKey() == HUDKeybinds.TOGGLEHUD.getKey().getValue()) showOrHideHUD();
        }
    }

    /**
     * @see GuiMixin
     *
     */
    public static void showOrHideHUD() {
        hudEnabled = !hudEnabled;
    }

    /**
    * Events below check if the player changes HP, changes hunger, is underwater, or has armor
     */
    @SubscribeEvent
    public static void playerIncreaseHP(LivingHealEvent livingHealEvent) {
        if(livingHealEvent.getEntity() instanceof Player player) {
            healthChange(player);
        }
    }

    @SubscribeEvent
    public static void playerDecreaseHP(LivingDamageEvent.Pre livingDamageEvent) {
        if(livingDamageEvent.getEntity() instanceof Player player) {
            healthChange(player);
        }
    }

    /*
    @SubscribeEvent
    public static void playerInWater(LivingBreatheEvent event) {
        // Add HUD indicator for player in water (bubble)
        if(!event.canBreathe() && (event.getEntity().getServer() != null)) {
            Objects.requireNonNull(event.getEntity().getServer(), "Get server is null").sendSystemMessage(Component.literal("You are drowning!"));
        }
    }

     */

    /*
    @SubscribeEvent
    public static void playerHasArmor(PlayerTickEvent.Pre event) {
        // Add HUD indicator for armor
        Player player = event.getEntity();
        if (player.getArmorValue() > 0 && !event.getEntity().isControlledByOrIsLocalPlayer()) {
            Objects.requireNonNull(event.getEntity().getServer(), "ServerPlayer does not exist in HUDManager").sendSystemMessage(Component.literal("You have armor!"));
        }
    }
     */

    /** For play testing */
    public static void healthChange(Player player) {
        Objects.requireNonNull(player.getServer(), "ServerPlayer does not exist in HUDManager").sendSystemMessage(Component.literal("HP Change"));
    }

    public static void hungerChange(Player player) {
        Objects.requireNonNull(player.getServer(), "ServerPlayer does not exist in HUDManager").sendSystemMessage(Component.literal("Hunger Change"));
    }

    /**
     * Getters and setters :D
     */
    public static boolean isHudEnabled() {
        return hudEnabled;
    }

    public static Minecraft getMcInstance() {
        return mcInstance;
    }
}