package me.potaytoprograms.test.common.entity;

import me.potaytoprograms.test.RubyMod;
import me.potaytoprograms.test.common.entity.cube.CubeEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityInit {
	
	public static final EntityType<CubeEntity> CUBE_ENTITY = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(RubyMod.MODID, "cube_entity"),
			FabricEntityTypeBuilder.<CubeEntity>create(SpawnGroup.CREATURE, CubeEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build());
	
	public static void init(){
		
		FabricDefaultAttributeRegistry.register(CUBE_ENTITY, CubeEntity.createMobAttributes());
	}
}
