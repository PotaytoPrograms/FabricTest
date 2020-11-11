package me.potaytoprograms.test.client;

import me.potaytoprograms.test.RubyMod;
import me.potaytoprograms.test.client.render.entity.CubeEntityRenderer;
import me.potaytoprograms.test.client.render.ui.OvenScreen;
import me.potaytoprograms.test.common.entity.EntityInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class RubyModClient implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		ScreenRegistry.register(RubyMod.BOX_SCREEN_HANDLER, OvenScreen::new);
		EntityRendererRegistry.INSTANCE.register(EntityInit.CUBE_ENTITY, (entityRenderDispatcher, context) ->  new CubeEntityRenderer(entityRenderDispatcher));
	}
}
