package net.cobra.moreores.compat;//package net.cobra.moreores.compat;
//
//import me.shedaniel.math.Rectangle;
//import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
//import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
//import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
//import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
//import me.shedaniel.rei.api.common.util.EntryStacks;
//import net.cobra.moreores.block.ModBlocks;
//import net.cobra.moreores.recipe.GemPolisherRecipe;
//import net.cobra.moreores.screen.GemPolisherScreen;
//
//public class MoreOresREIPlugin implements REIClientPlugin {
//
//    @Override
//    public void registerCategories(CategoryRegistry registry) {
//        registry.add(new GemPolisherCategory());
//
//        registry.addWorkstations(GemPolisherCategory.GEM_POLISHING, EntryStacks.of(ModBlocks.GEM_POLISHER_BLOCK));
//    }
//
//    @Override
//    public void registerDisplays(DisplayRegistry registry) {
//        registry.beginRecipeFiller(GemPolisherRecipe.class, GemPolisherRecipe.Type.GEM_POLISHING,
//                GemPolisherDisplay::new);
//    }
//
//    @Override
//    public void registerScreens(ScreenRegistry registry) {
//        registry.registerClickArea(screen -> new Rectangle(75, 30, 20, 30), GemPolisherScreen.class, GemPolisherCategory.GEM_POLISHING);
//    }
//}
