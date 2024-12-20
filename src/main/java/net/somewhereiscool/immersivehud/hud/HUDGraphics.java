package net.somewhereiscool.immersivehud.hud;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import assets.minecraft.*;

public class HUDGraphics extends Screen {
    private final KeyMapping keybind;
    private final Player player;

    protected HUDGraphics(Component title) {
        super(title);
        keybind = HUDKeybinds.OPENHUDRADIAL;
        player = Minecraft.getInstance().player;
    }

    @Override
    protected void init() {
        super.init();

        // Add widgets and precomputed values
        // this.addRenderableOnly();
    }

    @Override
    public void tick() {
        super.tick();


    }

    @Override
    public void onClose() {
        // Stop any handlers here

        // Call last in case it interferes with the override
        super.onClose();
    }

    @Override
    public void removed() {
        // Reset initial states here

        // Call last in case it interferes with the override
        super.removed();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {

    }

    public void drawItems() {
        // Get items and make a circle out of it
        // I would need to access the current players inventory
    }

    public void calculateDesiredSlot(Player player) {
        player.getInventory().pickSlot(0);
    }
}