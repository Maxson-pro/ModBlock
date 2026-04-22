package net.maxim.modblock.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class VillagerSpawnerBlock extends Block {
    public VillagerSpawnerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, 100);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int x = pos.getX() + random.nextBetween(-10, 10);
        int z = pos.getZ() + random.nextBetween(-10, 10);
        BlockPos spawnPos = new BlockPos(x, pos.getY() + 1, z);

        if (world.isAir(spawnPos)) {
            EntityType.VILLAGER.spawn(world, spawnPos, SpawnReason.SPAWNER);
        }
        world.scheduleBlockTick(pos, this, 100);
    }
}