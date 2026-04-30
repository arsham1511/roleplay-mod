package com.example;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MissileBlock extends Block {
    public MissileBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            CruiseMissileEntity missile = new CruiseMissileEntity(CruiseMissileMod.CRUISE_MISSILE, world);
            missile.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
            world.spawnEntity(missile);
            world.setBlockState(pos, net.minecraft.block.Blocks.AIR.getDefaultState());
        }
    }
}
