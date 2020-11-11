package me.potaytoprograms.test.common.block.entity;

import me.potaytoprograms.test.RubyMod;
import me.potaytoprograms.test.common.block.BlockInit;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockEntityInit {
	
	public static BlockEntityType<OvenBlockEntity> OVEN_BLOCK_ENTITY_TYPE;
	
	public static void init(){
		OVEN_BLOCK_ENTITY_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(RubyMod.MODID, "oven_block_entity"), BlockEntityType.Builder.create(OvenBlockEntity::new, BlockInit.OVEN_BLOCK).build(null));
	}
	
}
