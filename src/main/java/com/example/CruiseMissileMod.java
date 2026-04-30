package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CruiseMissileMod implements ModInitializer {
    public static final String MOD_ID = "cruisemissile";

    // ثبت آیتم هدف‌گیر
    public static final Item TARGETING_STICK = new TargetingStickItem(new FabricItemSettings().maxCount(1));

    // ثبت بلاک موشک
    public static final Block MISSILE_BLOCK = new MissileBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS));

    // ثبت موجودیت موشک
    public static final EntityType<CruiseMissileEntity> CRUISE_MISSILE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MOD_ID, "missile"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, CruiseMissileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 4.0f)) // ابعاد موشک
                    .build()
    );

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "targeting_stick"), TARGETING_STICK);
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "missile_block"), MISSILE_BLOCK);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "missile_block"), new BlockItem(MISSILE_BLOCK, new FabricItemSettings()));
    }
}
