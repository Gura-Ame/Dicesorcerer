package com.imili.dicesorcerer.entity;

import com.imili.dicesorcerer.DicesorcererMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import software.bernie.geckolib.constant.dataticket.DataTicket;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.Map;

public final class DiceEntityRenderer<R extends EntityRenderState & GeoRenderState> extends GeoEntityRenderer<DiceEntity, R> {
    public DiceEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new DiceEntityModel());
    }

    @Contract("_ -> new")
    @Override
    public @NotNull ResourceLocation getTextureLocation(R renderState) {
        return ResourceLocation.fromNamespaceAndPath(DicesorcererMod.MOD_ID, "textures/entity/dice.png");
    }
}
