package com.example;

import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CruiseMissileRenderer extends EntityRenderer<CruiseMissileEntity> {
    public CruiseMissileRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(CruiseMissileEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        // رندر ۳ بلاک چوب
        for (int i = 0; i < 3; i++) {
            matrices.push();
            matrices.translate(-0.5, i, -0.5);
            MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(Blocks.OAK_LOG.getDefaultState(), matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV);
            matrices.pop();
        }
        // رندر نوک TNT
        matrices.push();
        matrices.translate(-0.5, 3, -0.5);
        MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(Blocks.TNT.getDefaultState(), matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
        
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(CruiseMissileEntity entity) { return null; }
}
