package com.example;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class TargetingStickItem extends Item {
    public TargetingStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        NbtCompound nbt = context.getStack().getOrCreateNbt();
        nbt.putLong("target_pos", pos.asLong());
        
        if (!context.getWorld().isClient) {
            context.getPlayer().sendMessage(Text.literal("§b[رادار] §fهدف در مختصات " + pos.toShortString() + " قفل شد!"), true);
        }
        return ActionResult.SUCCESS;
    }
}
