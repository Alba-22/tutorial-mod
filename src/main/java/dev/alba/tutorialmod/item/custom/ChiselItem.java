package dev.alba.tutorialmod.item.custom;

import dev.alba.tutorialmod.block.ModBlocks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.Objects;

public class ChiselItem extends Item {

    private static final Map<Block, Block> CHISEL_MAP = Map.of(
            Blocks.STONE, Blocks.STONE_BRICKS,
            Blocks.END_STONE, Blocks.END_STONE_BRICKS,
            Blocks.DIRT, ModBlocks.ALEXANDRITE_BLOCK.get()
    );

    public ChiselItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        // Level determines if we are on the server or in the client
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if (CHISEL_MAP.containsKey(clickedBlock)) {
            // If I want to change something, like changing blocks, this can only be done in the server, not in the client, since it would be a huge vulnerability
            if (!level.isClientSide()) {
                // Set the clicked block to be the equivalent from the map
                level.setBlockAndUpdate(pContext.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());
                // Diminishes the item durability by one
                pContext.getItemInHand().hurtAndBreak(
                        1,
                        ((ServerLevel) level),
                        ((ServerPlayer) pContext.getPlayer()),
                        item -> Objects.requireNonNull(pContext.getPlayer()).onEquippedItemBroken(item, EquipmentSlot.MAINHAND)
                );
                // Play a Grindstone sound when chisel is used
                level.playSound(null, pContext.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);

            }
        }

        // Indicates that the action was done successfully, implying in the default use item animation by the game
        return InteractionResult.SUCCESS;
    }

    @Override
    // Method to use item without looking to a block
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    // Method for using an item on holding right click, like when using a bow
    public int getUseDuration(ItemStack pStack, LivingEntity pEntity) {
        return super.getUseDuration(pStack, pEntity);
    }
}
