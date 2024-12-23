package net.somewhereiscool.immersivehud.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.living.LivingBreatheEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Objects;

@EventBusSubscriber
public class HUDManager {
    // Two events that check if the game notices HP change of a player, will likely be never used as the events are triggered
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

    @SubscribeEvent
    public static void playerPressesKeybind(InputEvent.Key event) {
        Minecraft mcInstance = Minecraft.getInstance();
        if(HUDKeybinds.OPENHUDRADIAL.isDown()) {
            assert mcInstance.player != null;
            if(mcInstance.player.isLocalPlayer()) {
                mcInstance.setScreen(new HUDRadialGraphics(Component.literal("test")));
            }
        }
    }

    /*
    @SubscribeEvent
    public static void playerHidesGUI(InputEvent.Key event) {
        Minecraft mcInstance = Minecraft.getInstance();
        if(mcInstance.player.isLocalPlayer()) {
            if(HUDKeybinds.RADIALSETTINGS.isDown()) {
                mcInstance.setScreen(new HUDRadialGraphics(Component.literal("test")));
            }
        }
    }
    */

    public static void healthChange(Player player) {
        Objects.requireNonNull(player.getServer(), "ServerPlayer does not exist in HUDManager").sendSystemMessage(Component.literal("HP Change"));
    }

    public static void hungerChange(Player player) {
        Objects.requireNonNull(player.getServer(), "ServerPlayer does not exist in HUDManager").sendSystemMessage(Component.literal("Hunger Change"));
    }
}
