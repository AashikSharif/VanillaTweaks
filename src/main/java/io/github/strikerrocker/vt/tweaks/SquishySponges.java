package io.github.strikerrocker.vt.tweaks;

import io.github.strikerrocker.vt.base.Feature;
import net.minecraft.block.Blocks;
import net.minecraft.block.WetSpongeBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SquishySponges extends Feature {
    private ForgeConfigSpec.BooleanValue squishySponge;

    //TODO Doesn't work

    private static void turnIntoWater(World worldIn, BlockPos pos) {
        if (worldIn.getBlockState(pos).getMaterial().isReplaceable()) {
            if (worldIn.dimensionType().ultraWarm()) {
                worldIn.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            } else {
                worldIn.setBlock(pos, Fluids.WATER.defaultFluidState().createLegacyBlock(), 11);
                worldIn.neighborChanged(pos, Blocks.WATER, pos);
            }
        }
    }

    @Override
    public boolean usesEvents() {
        return true;
    }

    @Override
    public void setupConfig(ForgeConfigSpec.Builder builder) {
        squishySponge = builder
                .translation("config.vanillatweaks:squishySponge")
                .comment("Do you want to neglect fall damage and get water spilled when you fall on a wet sponge?")
                .define("squishySponge", true);
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        World world = event.getEntity().getCommandSenderWorld();
        LivingEntity entity = event.getEntityLiving();
        BlockPos pos = entity.blockPosition().below();
        if (entity instanceof PlayerEntity && squishySponge.get() && world.getBlockState(pos).getBlock() instanceof WetSpongeBlock) {
            turnIntoWater(world, pos.relative(Direction.Plane.HORIZONTAL.getRandomDirection(world.random)));
            world.setBlockAndUpdate(pos, Blocks.SPONGE.defaultBlockState());
            event.setCanceled(true);
        }
    }
}