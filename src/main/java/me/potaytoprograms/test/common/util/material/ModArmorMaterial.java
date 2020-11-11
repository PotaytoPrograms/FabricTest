package me.potaytoprograms.test.common.util.material;

import me.potaytoprograms.test.common.item.ItemInit;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum ModArmorMaterial implements ArmorMaterial {
	
	RUBY("ruby", new int[] {26, 30, 32, 22}, new int[] {6, 12, 16, 6}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, Ingredient.ofItems(ItemInit.RUBY), 10.0f, 10.0f);
	
	private int[] BASE_DURABILITY;
	private int[] PROTECTION_AMOUNT;
	private int ENCHANTABILITY;
	private SoundEvent SOUND;
	private Ingredient INGREDIENT;
	private float TOUGHNESS;
	private float KNOCKBACK_RESITANCE;
	private String NAME;
	
	ModArmorMaterial(String name, int[] durability, int[] protection, int enchantability, SoundEvent sound, Ingredient ingredient, float toughess, float knockbackResistance){
		BASE_DURABILITY = durability;
		PROTECTION_AMOUNT = protection;
		ENCHANTABILITY = enchantability;
		SOUND = sound;
		INGREDIENT = ingredient;
		TOUGHNESS = toughess;
		KNOCKBACK_RESITANCE = knockbackResistance;
		NAME = name;
	}
	
	@Override
	public int getDurability(EquipmentSlot slot) {
		return BASE_DURABILITY[slot.getEntitySlotId()];
	}
	
	@Override
	public int getProtectionAmount(EquipmentSlot slot) {
		return PROTECTION_AMOUNT[slot.getEntitySlotId()];
	}
	
	@Override
	public int getEnchantability() {
		return ENCHANTABILITY;
	}
	
	@Override
	public SoundEvent getEquipSound() {
		return SOUND;
	}
	
	@Override
	public Ingredient getRepairIngredient() {
		return INGREDIENT;
	}
	
	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public float getToughness() {
		return TOUGHNESS;
	}
	
	@Override
	public float getKnockbackResistance() {
		return KNOCKBACK_RESITANCE;
	}
}
