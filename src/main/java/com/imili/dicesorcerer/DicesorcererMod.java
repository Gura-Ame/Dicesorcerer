package com.imili.dicesorcerer;

import com.imili.dicesorcerer.entity.DiceEntity;
import com.imili.dicesorcerer.item.DiceItem;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@Mod(DicesorcererMod.MOD_ID)
public class DicesorcererMod {
    public static final String MOD_ID = "dicesorcerer";
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DicesorcererMod.MOD_ID);
    public static final DeferredItem<Item> DICE_ITEM = ITEMS.registerItem(DiceItem.ITEM_ID, DiceItem::new);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DICESORCERER_TAB = CREATIVE_MODE_TABS.register("dicesorcerer_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.dicesorcerer"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> DICE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(DICE_ITEM.get());
            }).build());

    public static final DeferredRegister.Entities ENTITY_TYPES =
            DeferredRegister.createEntities(DicesorcererMod.MOD_ID);
    public static final Supplier<EntityType<DiceEntity>> DICE_ENTITY = DicesorcererMod.ENTITY_TYPES.register(
            DiceEntity.ENTITY_ID,
            // The entity type, created using a builder.
            () -> EntityType.Builder.of(
                            // An EntityType.EntityFactory<T>, where T is the entity class used - MyEntity in this case.
                            // You can think of it as a BiFunction<EntityType<T>, Level, T>.
                            // This is commonly a reference to the entity constructor.
                            DiceEntity::new,
                            // The MobCategory our entity uses. This is mainly relevant for spawning.
                            // See below for more information.
                            MobCategory.MISC
                    )
                    // The width and height, in blocks. The width is used in both horizontal directions.
                    // This also means that non-square footprints are not supported. Default is 0.6f and 1.8f.
                    .sized(1.0f, 1.0f)
                    // A multiplicative factor (scalar) used by mobs that spawn in varying sizes.
                    // In vanilla, these are only slimes and magma cubes, both of which use 4.0f.
                    .spawnDimensionsScale(4.0f)
                    // The eye height, in blocks from the bottom of the size. Defaults to height * 0.85.
                    // This must be called after #sized to have an effect.
                    .eyeHeight(0.5f)
                    // Disables the entity being summonable via /summon.
                    .noSummon()
                    // Prevents the entity from being saved to disk.
                    .noSave()
                    // Makes the entity fire immune.
                    .fireImmune()
                    // Makes the entity immune to damage from a certain block. Vanilla uses this to make
                    // foxes immune to sweet berry bushes, withers and wither skeletons immune to wither roses,
                    // and polar bears, snow golems and strays immune to powder snow.
                    .immuneTo(Blocks.POWDER_SNOW)
                    // Disables a rule in the spawn handler that limits the distance at which entities can spawn.
                    // This means that no matter the distance to the player, this entity can spawn.
                    // Vanilla enables this for pillagers and shulkers.
                    .canSpawnFarFromPlayer()
                    // The range in which the entity is kept loaded by the client, in chunks.
                    // Vanilla values for this vary, but it's often something around 8 or 10. Defaults to 5.
                    // Be aware that if this is greater than the client's chunk view distance,
                    // then that chunk view distance is effectively used here instead.
                    .clientTrackingRange(8)
                    // How often update packets are sent for this entity, in once every x ticks. This is set to higher values
                    // for entities that have predictable movement patterns, for example projectiles. Defaults to 3.
                    .updateInterval(10)
                    // Build the entity type using a resource key. The second parameter should be the same as the entity id.
                    .build(ResourceKey.create(
                            Registries.ENTITY_TYPE,
                            ResourceLocation.fromNamespaceAndPath(DicesorcererMod.MOD_ID, DiceEntity.ENTITY_ID)
                    ))
    );

    public DicesorcererMod(IEventBus modEventBus, @NotNull ModContainer modContainer) {
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        ENTITY_TYPES.register(modEventBus);
    }
}
