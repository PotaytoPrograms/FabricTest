package me.potaytoprograms.test.common.block.entity;

import me.potaytoprograms.test.common.ui.inventory.OvenScreenHandler;
import me.potaytoprograms.test.common.ui.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class OvenBlockEntity extends BlockEntity implements ImplementedInventory, SidedInventory, NamedScreenHandlerFactory {
	
	private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);
	
	public OvenBlockEntity() {
		super(BlockEntityInit.OVEN_BLOCK_ENTITY_TYPE);
	}
	
	@Override
	public DefaultedList<ItemStack> getInventory() {
		return inventory;
	}
	
	@Override
	public int[] getAvailableSlots(Direction side) {
		int[] result = new int[getInventory().size()];
		for(int i = 0; i < result.length; i++){
			result[i] = i;
		}
		return result;
	}
	
	@Override
	public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
		return dir != Direction.UP;
	}
	
	@Override
	public boolean canExtract(int slot, ItemStack stack, Direction dir) {
		return true;
	}
	
	@Override
	public Text getDisplayName() {
		return new TranslatableText(getCachedState().getBlock().getTranslationKey());
	}
	
	@Override
	public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
		return new OvenScreenHandler(syncId, inv, this);
	}
	
	@Override
	public void fromTag(BlockState state, CompoundTag tag) {
		super.fromTag(state, tag);
		inventory = DefaultedList.ofSize(size(), ItemStack.EMPTY);
		Inventories.fromTag(tag, this.inventory);
	}
	
	@Override
	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);
		Inventories.toTag(tag, this.inventory);
		return tag;
	}
	
	@Override
	public BlockEntityType<?> getType() {
		return BlockEntityInit.OVEN_BLOCK_ENTITY_TYPE;
	}
}
