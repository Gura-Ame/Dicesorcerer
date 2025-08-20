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
            () -> EntityType.Builder.of(DiceEntity::new, MobCategory.MISC)
                    .sized(0.3f, 0.3f)
                    .canSpawnFarFromPlayer()
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
