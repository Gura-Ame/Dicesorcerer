package com.imili.dicesorcerer.item;

import com.imili.dicesorcerer.DicesorcererMod;
import com.imili.dicesorcerer.entity.DiceEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public final class DiceItem extends Item {
    public static final String ITEM_ID = "dice";

    public DiceItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide()) {
            DiceEntity dice = new DiceEntity(DicesorcererMod.DICE_ENTITY.get(), level);
            dice.setPos(context.getClickedPos().getCenter());
            level.addFreshEntity(dice);
            dice.setDicing();
        }
        return super.useOn(context);
    }
}
