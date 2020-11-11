package me.potaytoprograms.test.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.Heightmap;

import java.util.Random;

public class TeleportEnchant extends Enchantment {
	
	private Random random = new Random();
	private final float min = 2.0f;
	private final float max = 10.0f;
	
	public TeleportEnchant() {
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
			float x = (min * level) + random.nextFloat() * ((max * level) - (min * level));
			float z = (min * level) + random.nextFloat() * ((max * level) - (min * level));
			float y = target.world.getTopY(Heightmap.Type.WORLD_SURFACE, (int) x, (int) z);
			((LivingEntity) target).teleport(target.getX() + x, y, target.getZ() + z, true);
		}
		super.onTargetDamaged(user, target, level);
	}
}
