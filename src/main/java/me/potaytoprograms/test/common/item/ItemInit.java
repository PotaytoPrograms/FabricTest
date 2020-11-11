package me.potaytoprograms.test.common.item;

import me.potaytoprograms.test.RubyMod;
import me.potaytoprograms.test.common.item.ItemGroupInit;
import me.potaytoprograms.test.common.item.advanced.HealStickItem;
import me.potaytoprograms.test.common.item.tools.ModAxeItem;
import me.potaytoprograms.test.common.item.tools.ModHoeItem;
import me.potaytoprograms.test.common.item.tools.ModPickaxeItem;
import me.potaytoprograms.test.common.util.material.ModArmorMaterial;
import me.potaytoprograms.test.common.util.material.ModToolMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit {
	
	//normal items
	public static final Item RUBY = new Item(new FabricItemSettings().group(ItemGroupInit.RUBY_MOD_ITEMS));
	//advanced items
	public static final HealStickItem HEAL_STICK_ITEM = new HealStickItem(new FabricItemSettings().group(ItemGroupInit.RUBY_MOD_ITEMS).maxCount(1).maxDamage(10));
	//tools
	public static final ToolMaterial RUBY_TOOL_MATERIAL = ModToolMaterial.RUBY;
	
	public static final SwordItem RUBY_SWORD = new SwordItem(RUBY_TOOL_MATERIAL, 6, 0.5f, new Item.Settings().group(ItemGroupInit.RUBY_MOD_ITEMS));
	public static final ShovelItem RUBY_SHOVEL = new ShovelItem(RUBY_TOOL_MATERIAL, 1.5f, 1.0f, new Item.Settings().group(ItemGroupInit.RUBY_MOD_ITEMS));
	private static final ToolItem RUBY_AXE = new ModAxeItem(RUBY_TOOL_MATERIAL, 7.0f, 1.5f, new Item.Settings().group(ItemGroupInit.RUBY_MOD_ITEMS));
	private static final ToolItem RUBY_HOE = new ModHoeItem(RUBY_TOOL_MATERIAL, 2, 1.5f, new Item.Settings().group(ItemGroupInit.RUBY_MOD_ITEMS));
	private static final ToolItem RUBY_PICKAXE = new ModPickaxeItem(RUBY_TOOL_MATERIAL, 3, 1.5f, new Item.Settings().group(ItemGroupInit.RUBY_MOD_ITEMS));
	//armor
	public static final ArmorMaterial RUBY_ARMOR_MATERIAL = ModArmorMaterial.RUBY;
	
	public static final Item RUBY_HELMET = new ArmorItem(RUBY_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroupInit.RUBY_MOD_ITEMS));
	public static final Item RUBY_CHESTPLATE = new ArmorItem(RUBY_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroupInit.RUBY_MOD_ITEMS));
	public static final Item RUBY_LEGGINGS = new ArmorItem(RUBY_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroupInit.RUBY_MOD_ITEMS));
	public static final Item RUBY_BOOTS = new ArmorItem(RUBY_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(ItemGroupInit.RUBY_MOD_ITEMS));
	
	public static void init(){
		//normal items
		Registry.register(Registry.ITEM, new Identifier(RubyMod.MODID, "ruby_item"), RUBY);
		
		//advanced items
		Registry.register(Registry.ITEM, new Identifier(RubyMod.MODID, "heal_stick_item"), HEAL_STICK_ITEM);
		
		//armor
		Registry.register(Registry.ITEM, new Identifier(RubyMod.MODID, "ruby_helmet"), RUBY_HELMET);
		Registry.register(Registry.ITEM, new Identifier(RubyMod.MODID, "ruby_chestplate"), RUBY_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(RubyMod.MODID, "ruby_leggings"), RUBY_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier(RubyMod.MODID, "ruby_boots"), RUBY_BOOTS);
		
		//tools
		Registry.register(Registry.ITEM, new Identifier(RubyMod.MODID, "ruby_sword"), RUBY_SWORD);
		Registry.register(Registry.ITEM, new Identifier(RubyMod.MODID, "ruby_axe"), RUBY_AXE);
		Registry.register(Registry.ITEM, new Identifier(RubyMod.MODID, "ruby_shovel"), RUBY_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier(RubyMod.MODID, "ruby_hoe"), RUBY_HOE);
		Registry.register(Registry.ITEM, new Identifier(RubyMod.MODID, "ruby_pickaxe"), RUBY_PICKAXE);
	}
}
