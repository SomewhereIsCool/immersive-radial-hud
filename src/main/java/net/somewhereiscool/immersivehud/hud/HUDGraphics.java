package net.somewhereiscool.immersivehud.hud;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ImageWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import assets.minecraft.*;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.somewhereiscool.immersivehud.ImmersiveRadialHUD;

public class HUDGraphics extends Screen {
    private final KeyMapping keybind;
    private final Player player;
    private static final ResourceLocation CROSSHAIR = ResourceLocation.fromNamespaceAndPath(String.valueOf(Minecraft.getInstance()), "textures/gui/sprites/hud/crosshair.png");
    private static final ResourceLocation SLOT = ResourceLocation.fromNamespaceAndPath(String.valueOf(Minecraft.getInstance()), "textures/gui/sprites/container/inventory/effect_background_small.png");
    private static final Integer RADIUS = 20;

    float xCenter;
    float yCenter;

    protected HUDGraphics(Component title) {
        super(title);
        keybind = HUDKeybinds.OPENHUDRADIAL;
        player = Minecraft.getInstance().player;
    }

    @Override
    protected void init() {
        super.init();

        // ImageWidget widget = ImageWidget.texture(10, 10, SLOT, 10, 10);

        // Add widgets and precomputed values
        this.addRenderableOnly(widget);
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

        int maxItems = Inventory.SELECTION_SIZE;
        double degreeFactor = ((double) maxItems / 360);
        double currentDegree = 0;


        for(int i = 0; i < maxItems; i++) {
            currentDegree += degreeFactor;

            double xOffset = RADIUS * Math.cos(Math.toRadians(currentDegree));
            double yOffset = RADIUS * Math.sin(Math.toRadians(currentDegree));

            player.getInventory().getItem(i);
        }
    }

    public void calculateDesiredSlot(Player player) {
        player.getInventory().pickSlot(0);
    }
}