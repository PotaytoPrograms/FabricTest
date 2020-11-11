package me.potaytoprograms.test.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class ThrowEnchantment extends Enchantment {
	
	public ThrowEnchantment() {
		super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
	}
	
	@Override
	public int getMinPower(int level) {
		return 10 * level;
	}
	
	@Override
	public int getMaxLevel() {
		return 3;
	}
	
	@Override
	public void onTargetDamaged(LivingEntity user, Entity target, int level) {
		if(target instanceof LivingEntity){
			((LivingEntity) target).teleport(target.getX(), target.getY() + 5 * level, target.getZ());
		}
		super.onTargetDamaged(user, target, level);
	}
}
