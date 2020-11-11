package me.potaytoprograms.test.common.ui.inventory;

import me.potaytoprograms.test.RubyMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class OvenScreenHandler extends ScreenHandler {
	
	public final Inventory inventory;
	
	public OvenScreenHandler(int syncId, PlayerInventory playerInv) {
		this(syncId, playerInv, new SimpleInventory(9));
	}
	
	public OvenScreenHandler(int syncId, PlayerInventory playerInv, Inventory inv){
		super(RubyMod.BOX_SCREEN_HANDLER, syncId);
		this.inventory = inv;
		inventory.onOpen(playerInv.player);
		
		int m;
		int l;
		
		for (m = 0; m < 3; ++m) {
			for (l = 0; l < 3; ++l) {
				this.addSlot(new Slot(inventory, l + m * 3, 62 + l * 18, 17 + (m * 18)));
			}
		}
		//The player inventory
		for (m = 0; m < 3; ++m) {
			for (l = 0; l < 9; ++l) {
				this.addSlot(new Slot(playerInv, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
			}
		}
		//The player Hotbar
		for (m = 0; m < 9; ++m) {
			this.addSlot(new Slot(playerInv, m, 8 + m * 18, 142));
		}
	}
	
	@Override
	public boolean canUse(PlayerEntity player) {
		return this.inventory.canPlayerUse(player);
	}
	
	@Override
	public ItemStack transferSlot(PlayerEntity player, int index) {
		ItemStack newStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if(slot != null && slot.hasStack()){
			ItemStack orignalStack = slot.getStack();
			newStack = orignalStack.copy();
			if(index < this.inventory.size()) {
				if (!this.insertItem(orignalStack, this.inventory.size(), this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			}else if(!this.insertItem(orignalStack, 0, this.inventory.size(), false)){
					return ItemStack.EMPTY;
			}
			
			if(orignalStack.isEmpty()){
				slot.setStack(ItemStack.EMPTY);
			}else{
				slot.markDirty();
			}
		}
		return newStack;
	}
}
