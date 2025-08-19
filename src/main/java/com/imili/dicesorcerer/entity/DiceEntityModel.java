package com.imili.dicesorcerer.entity;

import com.imili.dicesorcerer.DicesorcererMod;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public final class DiceEntityModel extends GeoModel<DiceEntity> {
    @Contract("_ -> new")
    @Override
    public @NotNull ResourceLocation getModelResource(GeoRenderState renderState) {
        return ResourceLocation.fromNamespaceAndPath(DicesorcererMod.MOD_ID, "dice");
    }

    @Contract("_ -> new")
    @Override
    public @NotNull ResourceLocation getTextureResource(GeoRenderState renderState) {
        return ResourceLocation.fromNamespaceAndPath(DicesorcererMod.MOD_ID, "textures/entity/dice.png");
    }

    @Contract("_ -> new")
    @Override
    public @NotNull ResourceLocation getAnimationResource(DiceEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(DicesorcererMod.MOD_ID, "dice");
    }
}
