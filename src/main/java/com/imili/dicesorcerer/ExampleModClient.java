package com.imili.dicesorcerer;

import com.imili.dicesorcerer.entity.DiceEntity;
import com.imili.dicesorcerer.entity.DiceEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.jetbrains.annotations.NotNull;

@Mod(value = DicesorcererMod.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = DicesorcererMod.MOD_ID, value = Dist.CLIENT)
public class ExampleModClient {
    public ExampleModClient(@NotNull ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(DicesorcererMod.DICE_ENTITY.get(), DiceEntityRenderer::new);
    }
}
