package net.somewhereiscool.immersivehud.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Input;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.event.entity.living.LivingBreatheEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Objects;

/*
* The "control center" for the HUD. It manages when graphics should be applied,
* looks for events to apply to HUDRadialOverlay
*
*
 */

@EventBusSubscriber
public class HUDManager {
    @SubscribeEvent
    public static void checkKeyPresses(InputEvent.Key key) {
        if(key.getKey() == HUDKeybinds.OPENHUDRADIAL.getKey().getValue()) {
            // Render just the items
            Minecraft.getInstance().setOverlay(new HUDRadialOverlay(key, Minecraft.getInstance()));
        }
    }

    /*
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

    @SubscribeEvent
    public static void playerInWater(LivingBreatheEvent event) {
        // Add HUD indicator for player in water (bubble)
        if(!event.canBreathe() && (event.getEntity().getServer() != null)) {
            Objects.requireNonNull(event.getEntity().getServer(), "Get server is null").sendSystemMessage(Component.literal("You are drowning!"));
        }
    }

    @SubscribeEvent
    public static void playerHasArmor(PlayerTickEvent.Pre event) {
        // Add HUD indicator for armor
        Player player = event.getEntity();
        if (player.getArmorValue() > 0 && !event.getEntity().isControlledByOrIsLocalPlayer()) {
            event.getEntity().getServer().sendSystemMessage(Component.literal("You have armor!"));
        }
    }

    public static void healthChange(Player player) {
        Objects.requireNonNull(player.getServer(), "ServerPlayer does not exist in HUDManager").sendSystemMessage(Component.literal("HP Change"));
    }

    public static void hungerChange(Player player) {
        Objects.requireNonNull(player.getServer(), "ServerPlayer does not exist in HUDManager").sendSystemMessage(Component.literal("Hunger Change"));
    }

}