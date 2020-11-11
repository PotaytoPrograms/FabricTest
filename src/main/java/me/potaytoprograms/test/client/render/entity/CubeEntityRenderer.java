package me.potaytoprograms.test.client.render.entity;

import me.potaytoprograms.test.RubyMod;
import me.potaytoprograms.test.common.entity.cube.CubeEntity;
import me.potaytoprograms.test.common.entity.cube.CubeEntityModel;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class CubeEntityRenderer extends MobEntityRenderer<CubeEntity, CubeEntityModel> {
	
	public CubeEntityRenderer(EntityRenderDispatcher entityRenderDispatcher){
		super(entityRenderDispatcher, new CubeEntityModel(), 0.5f);
	}
	
	@Override
	public Identifier getTexture(CubeEntity entity) {
		return new Identifier(RubyMod.MODID, "textures/entity/cube/cube.png");
	}
}
