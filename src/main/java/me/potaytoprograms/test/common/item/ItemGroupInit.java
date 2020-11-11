package me.potaytoprograms.test.common.item;

import me.potaytoprograms.test.RubyMod;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ItemGroupInit {
	
	public static final ItemGroup RUBY_MOD_ITEMS = FabricItemGroupBuilder.build(
			new Identifier(RubyMod.MODID, "item_group"),
			() -> new ItemStack(ItemInit.RUBY));
}
