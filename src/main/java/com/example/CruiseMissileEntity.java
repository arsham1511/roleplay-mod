package com.example;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CruiseMissileEntity extends Entity {
    private BlockPos targetPos;
    private boolean launched = false;

    public CruiseMissileEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() instanceof TargetingStickItem && stack.hasNbt()) {
            this.targetPos = BlockPos.fromLong(stack.getNbt().getLong("target_pos"));
            this.launched = true;
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public void tick() {
        super.tick();
        if (!launched || targetPos == null || getWorld().isClient) return;

        Vec3d targetVec = Vec3d.ofCenter(targetPos);
        Vec3d motion = targetVec.subtract(getPos()).normalize().multiply(0.7);
        this.setVelocity(motion);
        this.move(MovementType.SELF, getVelocity());

        if (getPos().distanceTo(targetVec) < 2.0 || this.horizontalCollision || this.verticalCollision) {
            getWorld().createExplosion(this, getX(), getY(), getZ(), 10.0f, World.ExplosionSourceType.TNT);
            this.discard();
        }
    }

    @Override protected void initDataTracker() {}
    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        launched = nbt.getBoolean("launched");
        if (nbt.contains("target_pos")) targetPos = BlockPos.fromLong(nbt.getLong("target_pos"));
    }
    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putBoolean("launched", launched);
        if (targetPos != null) nbt.putLong("target_pos", targetPos.asLong());
    }
}
