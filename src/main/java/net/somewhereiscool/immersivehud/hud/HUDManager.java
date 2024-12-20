package net.somewhereiscool.immersivehud.hud;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;

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

    public static void healthChange(Player player) {
        Objects.requireNonNull(player.getServer(), "ServerPlayer does not exist in HUDManager").sendSystemMessage(Component.literal("HP Change"));
    }

    public static void hungerChange(Player player) {
        Objects.requireNonNull(player.getServer(), "ServerPlayer does not exist in HUDManager").sendSystemMessage(Component.literal("Hunger Change"));

    }
}