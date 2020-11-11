package me.potaytoprograms.test.common.entity.cube;

import me.potaytoprograms.test.common.block.BlockInit;
import me.potaytoprograms.test.common.entity.EntityInit;
import me.potaytoprograms.test.common.item.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CubeEntity extends TameableEntity {
	
	public CubeEntity(World world){
		super(EntityInit.CUBE_ENTITY, world);
	}
	
	public CubeEntity(EntityType<? extends TameableEntity> entityType, World world) {
		super(entityType, world);
		setTamed(false);
	}
	
	@Override
	public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		CubeEntity cubeEntity = (CubeEntity) EntityInit.CUBE_ENTITY.create(world);
		UUID uUID = this.getOwnerUuid();
		if(uUID != null){
			cubeEntity.setOwnerUuid(uUID);
			cubeEntity.setTamed(true);
		}
		return null;
	}
	
	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new SwimGoal(this));
		this.goalSelector.add(2, new SitGoal(this));
		this.goalSelector.add(4, new PounceAtTargetGoal(this, 0.4F));
		this.goalSelector.add(5, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.add(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
		this.goalSelector.add(7, new AnimalMateGoal(this, 1.0D));
		this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0D));
		this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(9, new LookAroundGoal(this));
		this.targetSelector.add(1, new TrackOwnerAttackerGoal(this));
		this.targetSelector.add(2, new AttackWithOwnerGoal(this));
		this.targetSelector.add(3, (new RevengeGoal(this, new Class[0])).setGroupRevenge());
		this.targetSelector.add(4, new FollowTargetIfTamedGoal(this, TurtleEntity.class, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
		this.targetSelector.add(5, new FollowTargetGoal(this, AbstractSkeletonEntity.class, false));
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return stack.getItem() == BlockInit.RUBY_BLOCK.asItem();
	}
	
	@Override
	public void tickMovement() {
		super.tickMovement();
		if (!this.world.isClient) {
			if(isTamed()) this.world.sendEntityStatus(this, (byte)6);
			else this.world.sendEntityStatus(this, (byte) 8);
		}
	}
	
	@Override
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);
		Item item = itemStack.getItem();
		if (this.world.isClient) {
			boolean bl = this.isOwner(player) || this.isTamed() || item == Items.BONE && !this.isTamed();
			return bl ? ActionResult.CONSUME : ActionResult.PASS;
		}else {
			if (this.isTamed()) {
				if (this.isBreedingItem(itemStack) && this.getHealth() < this.getMaxHealth()) {
					if (!player.abilities.creativeMode) {
						itemStack.decrement(1);
					}
					
					this.heal((float) item.getFoodComponent().getHunger());
					return ActionResult.SUCCESS;
				}
			}  else if (item == ItemInit.RUBY) {
				if (!player.abilities.creativeMode) {
					itemStack.decrement(1);
				}
				
				if (this.random.nextInt(3) == 0) {
					this.setOwner(player);
					this.navigation.stop();
					this.setTarget((LivingEntity)null);
					this.setTamed(true);
					this.world.sendEntityStatus(this, (byte)6);
				} else {
					this.world.sendEntityStatus(this, (byte)8);
				}
				
				return ActionResult.SUCCESS;
			}
		}
		return super.interactMob(player, hand);
	}
	
	@Override
	public boolean canBreedWith(AnimalEntity other) {
		if (other == this) {
			return false;
		} else if (!this.isTamed()) {
			return false;
		} else if (!(other instanceof CubeEntity)) {
			return false;
		} else {
			CubeEntity cubeEntity = (CubeEntity) other;
			if (!cubeEntity.isTamed()) {
				return false;
			} else if (cubeEntity.isInSittingPose()) {
				return false;
			} else {
				return this.isInLove() && cubeEntity.isInLove();
			}
		}
	}
	
	@Override
	public boolean canAttackWithOwner(LivingEntity target, LivingEntity owner) {
		if (!(target instanceof CreeperEntity) && !(target instanceof GhastEntity)) {
			if (target instanceof CubeEntity) {
				CubeEntity wolfEntity = (CubeEntity) target;
				return !wolfEntity.isTamed() || wolfEntity.getOwner() != owner;
			} else if (target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity)owner).shouldDamagePlayer((PlayerEntity)target)) {
				return false;
			} else if (target instanceof HorseBaseEntity && ((HorseBaseEntity)target).isTame()) {
				return false;
			} else {
				return !(target instanceof TameableEntity) || !((TameableEntity)target).isTamed();
			}
		} else {
			return false;
		}
	}
	
	@Override
	public boolean tryAttack(Entity target) {
		boolean bl = target.damage(DamageSource.mob(this), (float)((int)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)));
		if (bl) {
			this.dealDamage(this, target);
		}
		
		return bl;
	}
	
	@Override
	public void setTamed(boolean tamed) {
		super.setTamed(tamed);
		if (tamed) {
			this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(20.0D);
			this.setHealth(20.0F);
		} else {
			this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(8.0D);
		}
		
		this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(4.0D);
	}
}
