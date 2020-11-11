package me.potaytoprograms.test.common.block;

import me.potaytoprograms.test.RubyMod;
import me.potaytoprograms.test.common.item.ItemGroupInit;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit {
	
	public static final Block RUBY_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(20.0f).breakByTool(FabricToolTags.PICKAXES, 3));
	public static final Block OVEN_BLOCK = new OvenBlock(FabricBlockSettings.of(Material.METAL).hardness(5.0f).breakByTool(FabricToolTags.PICKAXES, 1));
	public static final OreBlock RUBY_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(5.0f).breakByTool(FabricToolTags.PICKAXES, 3));
	
	public static void init(){
		//blocks
		Registry.register(Registry.BLOCK, new Identifier(RubyMod.MODID, "ruby_block"), RUBY_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(RubyMod.MODID, "oven_block"), OVEN_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(RubyMod.MODID, "ruby_ore"), RUBY_ORE);
		
		//items
		Registry.register(Registry.ITEM, new Identifier(RubyMod.MODID, "ruby_block"), new BlockItem(RUBY_BLOCK, new Item.Settings().group(ItemGroupInit.RUBY_MOD_ITEMS)));
		Registry.register(Registry.ITEM, new Identifier(RubyMod.MODID, "oven_block"), new BlockItem(OVEN_BLOCK, new Item.Settings().group(ItemGroupInit.RUBY_MOD_ITEMS)));
		Registry.register(Registry.ITEM, new Identifier(RubyMod.MODID, "ruby_ore"), new BlockItem(RUBY_ORE, new Item.Settings().group(ItemGroupInit.RUBY_MOD_ITEMS)));
	}
}
