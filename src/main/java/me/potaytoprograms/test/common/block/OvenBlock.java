package me.potaytoprograms.test.common.block;

import me.potaytoprograms.test.common.block.entity.OvenBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class OvenBlock extends HorizontalFacingBlock implements BlockEntityProvider {
	
	public static final BooleanProperty OPEN = BooleanProperty.of("open");
	
	public static final VoxelShape NORTH = VoxelShapes.cuboid(0.1f, 0.0f, 0.0f, 0.9f, 0.8f, 0.9f);
	public static final VoxelShape SOUTH = VoxelShapes.cuboid(0.1f, 0.0f, 0.1f, 0.9f, 0.8f, 1.0f);
	public static final VoxelShape EAST = VoxelShapes.cuboid(0.1f, 0.0f, 0.1f, 1.0f, 0.8f, 0.9f);
	public static final VoxelShape WEST = VoxelShapes.cuboid(0.0f, 0.0f, 0.1f, 0.9f, 0.8f, 0.9f);
	
	public OvenBlock(Settings settings) {
		super(settings);
		setDefaultState(getStateManager().getDefaultState().with(OPEN, false).with(Properties.HORIZONTAL_FACING, Direction.NORTH));
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(OPEN);
		builder.add(Properties.HORIZONTAL_FACING);
	}
	
	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if(!world.isClient) {
			NamedScreenHandlerFactory screenHandlerFactory = (NamedScreenHandlerFactory) world.getBlockEntity(pos);
			
			if (screenHandlerFactory != null) {
				player.openHandledScreen(screenHandlerFactory);
			}
		}
		return ActionResult.SUCCESS;
	}
	
	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		if(state.getBlock() != newState.getBlock()){
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if(blockEntity instanceof OvenBlockEntity){
				ItemScatterer.spawn(world, pos, (Inventory) blockEntity);
				
				world.updateComparators(pos, this);
			}
		}
		super.onStateReplaced(state, world, pos, newState, moved);
	}
	
	@Override
	public boolean hasComparatorOutput(BlockState state) {
		return true;
	}
	
	@Override
	public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Direction dir = state.get(FACING);
		switch (dir){
			case NORTH:
				return NORTH;
			case SOUTH:
				return SOUTH;
			case EAST:
				return EAST;
			case WEST:
				return WEST;
			default:
				return VoxelShapes.fullCube();
		}
	}
	
	@Override
	public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(FACING, ctx.getPlayerFacing());
	}
	
	@Override
	public @Nullable BlockEntity createBlockEntity(BlockView world) {
		return new OvenBlockEntity();
	}
}
