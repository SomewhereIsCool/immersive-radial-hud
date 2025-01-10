package net.somewhereiscool.minimalradialhud.hud.crosshair;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.somewhereiscool.minimalradialhud.hud.main.HUDManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//TODO: Consider changing the HungerEventHandler since you know that Minecraft.getInstance() exists
@EventBusSubscriber
public class HungerEventHandler {
    private static final Map<UUID, Integer> playerFoodLevels = new HashMap<>();

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
            HUDManager.hungerChange(player);
        }
    }
}