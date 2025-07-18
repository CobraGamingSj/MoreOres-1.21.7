package net.cobra.moreores.client;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.ModBlocks;
import net.cobra.moreores.block.entity.ModBlockEntityType;
import net.cobra.moreores.client.render.block.entity.GemPolisherBlockEntityRenderer;
import net.cobra.moreores.networking.ModS2CNetworks;
import net.cobra.moreores.recipe.display.GemPolishingRecipeDisplay;
import net.cobra.moreores.screen.GemPolisherScreen;
import net.cobra.moreores.screen.ModScreenHandlerType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class MoreOresClientModInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModS2CNetworks.registerClientS2C();

        Registry.register(Registries.RECIPE_DISPLAY, MoreOresModInitializer.getId("gem_polishing"), GemPolishingRecipeDisplay.SERIALIZER);

        BlockRenderLayerMap.putBlock(ModBlocks.TOMATO_CROP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.PINEAPPLE_CROP, BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(ModBlocks.GEM_POLISHER_BLOCK, BlockRenderLayer.TRANSLUCENT);

        HandledScreens.register(ModScreenHandlerType.GEM_POLISHING_SCREEN_HANDLER, GemPolisherScreen::new);

        BlockEntityRendererFactories.register(ModBlockEntityType.GEM_POLISHER_BLOCK_ENTITY, GemPolisherBlockEntityRenderer::new);
    }
}
