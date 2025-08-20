package com.imili.dicesorcerer.item;

import com.imili.dicesorcerer.DicesorcererMod;
import com.imili.dicesorcerer.entity.DiceEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public final class DiceItem extends Item {
    public static final String ITEM_ID = "dice";

    public DiceItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (!level.isClientSide()) {
            DiceEntity dice = new DiceEntity(DicesorcererMod.DICE_ENTITY.get(), level);
            // 設定起始位置（稍微離玩家眼睛前方一點，避免和玩家重疊）
            Vec3 look = player.getLookAngle();
            Vec3 pos = player.position().add(0, player.getEyeHeight(), 0).add(look.scale(0.5));
            dice.setPos(pos.x, pos.y, pos.z);

            level.addFreshEntity(dice);

            // 設定初速度
            double power = 0.6; // 可調整拋出速度
            dice.setDeltaMovement(look.scale(power));
        }
        return super.use(level, player, hand);
    }
}
