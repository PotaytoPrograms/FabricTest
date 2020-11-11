package me.potaytoprograms.test.common.enchantment;

import me.potaytoprograms.test.RubyMod;
import me.potaytoprograms.test.common.enchantment.FrostBiteEnchant;
import me.potaytoprograms.test.common.enchantment.TeleportEnchant;
import me.potaytoprograms.test.common.enchantment.ThrowEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EnchantInit {
	
	public static Enchantment FROSTBITE = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier(RubyMod.MODID, "frostbite_enchantment"),
			new FrostBiteEnchant()
	);
	
	public static Enchantment TELEPORT = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier(RubyMod.MODID, "teleport_enchantment"),
			new TeleportEnchant()
	);
	
	public static Enchantment THROW = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier(RubyMod.MODID, "throw_enchantment"),
			new ThrowEnchantment()
	);
	
	public static void init(){
	
	}
}
