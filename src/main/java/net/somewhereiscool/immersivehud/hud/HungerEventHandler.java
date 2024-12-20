package net.somewhereiscool.immersivehud.hud;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@EventBusSubscriber
public class HungerEventHandler {
    private static final Map<UUID, Integer> playerFoodLevels = new HashMap<UUID, Integer>();

    @SubscribeEvent
    public static void checkChangeInHunger(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        UUID playerId = player.getUUID();
        int currentFoodLevel = player.getFoodData().getFoodLevel();

        // Get previous food level; default to current level if none exists
        int prevFoodLevel = playerFoodLevels.getOrDefault(playerId, currentFoodLevel);

        // Compare levels and update if changed
        if (currentFoodLevel != prevFoodLevel) {
            playerFoodLevels.put(playerId, currentFoodLevel); // Update stored level
            changeInHunger(player);
        }
    }

    public static void changeInHunger(Player player) {
        if (!player.isLocalPlayer()) {
            Objects.requireNonNull(player.getServer(), "ServerPlayer is null in PlayerHungerEvent in changeInHunger()")
                    .sendSystemMessage(Component.literal("Change in hunger!"));
        }
    }
}
