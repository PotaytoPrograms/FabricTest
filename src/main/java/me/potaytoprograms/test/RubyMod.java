package me.potaytoprograms.test;

import me.potaytoprograms.test.common.block.BlockInit;
import me.potaytoprograms.test.common.enchantment.EnchantInit;
import me.potaytoprograms.test.common.entity.EntityInit;
import me.potaytoprograms.test.common.ui.inventory.OvenScreenHandler;
import me.potaytoprograms.test.common.item.ItemInit;
import me.potaytoprograms.test.common.world.ore.RubyModOreGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class RubyMod implements ModInitializer {
	
	public static final ScreenHandlerType<OvenScreenHandler> BOX_SCREEN_HANDLER;
	public static final String MODID = "ruby";
	
	static {
		BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(RubyMod.MODID, "box_screen"), OvenScreenHandler::new);
	}
	
	@Override
	public void onInitialize() {
		EnchantInit.init();
		BlockInit.init();
		ItemInit.init();
		EntityInit.init();
		RubyModOreGeneration.init();
	}
}
