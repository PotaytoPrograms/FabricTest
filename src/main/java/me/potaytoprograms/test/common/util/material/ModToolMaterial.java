package me.potaytoprograms.test.common.util.material;

import me.potaytoprograms.test.common.item.ItemInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum ModToolMaterial implements ToolMaterial {
	
	RUBY(2000, 4, 20, 10.0f, 5.0f, Ingredient.ofItems(ItemInit.RUBY));
	
	private int DURABILITY;
	private int MINING_LEVEL;
	private int ENCHANTABILITY;
	private float MINING_SPEED;
	private float DAMAGE;
	private Ingredient INGREDIENT;
	
	ModToolMaterial(int durability, int miningLevel, int enchantability, float miningSpeed, float damage, Ingredient ingredient){
		DURABILITY = durability;
		MINING_LEVEL = miningLevel;
		ENCHANTABILITY = enchantability;
		MINING_SPEED = miningSpeed;
		DAMAGE = damage;
		INGREDIENT = ingredient;
	}
	
	@Override
	public int getDurability() {
		return DURABILITY;
	}
	
	@Override
	public float getMiningSpeedMultiplier() {
		return MINING_SPEED;
	}
	
	@Override
	public float getAttackDamage() {
		return DAMAGE;
	}
	
	@Override
	public int getMiningLevel() {
		return MINING_LEVEL;
	}
	
	@Override
	public int getEnchantability() {
		return ENCHANTABILITY;
	}
	
	@Override
	public Ingredient getRepairIngredient() {
		return INGREDIENT;
	}
}
