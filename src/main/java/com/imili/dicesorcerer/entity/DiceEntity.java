package com.imili.dicesorcerer.entity;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animatable.processing.AnimationTest;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.keyframe.event.KeyFrameEvent;
import software.bernie.geckolib.animation.keyframe.event.data.CustomInstructionKeyframeData;

public final class DiceEntity extends Entity implements GeoEntity {
    private static final String ANIMATION_ID = "dice.model.dicing";
    public static final String ENTITY_ID = "dice";
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private boolean isDicing = false;
    private boolean playedAnimation = false;

    public DiceEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {

    }

    @Override
    public boolean hurtServer(@NotNull ServerLevel level, @NotNull DamageSource damageSource, float amount) {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(@NotNull ValueInput input) {

    }

    @Override
    protected void addAdditionalSaveData(@NotNull ValueOutput output) {

    }

    @Override
    public void registerControllers(AnimatableManager.@NotNull ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(DiceEntity.ENTITY_ID, 0, this::handle)
                .setCustomInstructionKeyframeHandler(this::handleKeyframes));
    }

    private void handleKeyframes(@NotNull KeyFrameEvent<GeoAnimatable, CustomInstructionKeyframeData> event) {
        if (event.controller().getAnimationState() == AnimationController.State.STOPPED) {
            System.out.println("hah");
        }
    }

    private PlayState handle(AnimationTest<GeoAnimatable> animatable) {
        if (isDicing && !playedAnimation) {
            animatable.setAnimation(RawAnimation.begin().then(DiceEntity.ANIMATION_ID, Animation.LoopType.PLAY_ONCE));
            playedAnimation = true;
        } else if (!isDicing && playedAnimation) {
            playedAnimation = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public void setDicing() {
        this.isDicing = true;
    }
}
