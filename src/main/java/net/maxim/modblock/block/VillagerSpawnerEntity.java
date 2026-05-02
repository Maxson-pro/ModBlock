package net.maxim.modblock.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.maxim.modblock.Modblock;

import java.util.ArrayList;
import java.util.List;

public class VillagerSpawnerEntity extends BlockEntity {
    public VillagerSpawnerEntity(BlockPos pos, BlockState state) {
        super(Modblock.VILLAGER_SPAWNER_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, VillagerSpawnerEntity entity) {
        if (!world.isClient && world.getTime() % 100 == 0) {
            ServerWorld serverWorld = (ServerWorld) world;

            List<BlockPos> validSpawnPositions = new ArrayList<>();
            int radius = 5;

            for (int x = -radius; x <= radius; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        BlockPos checkPos = pos.add(x, y, z);
                        if (world.getBlockState(checkPos).isOf(Blocks.COBBLESTONE) &&
                                world.isAir(checkPos.up()) &&
                                world.isAir(checkPos.up(2))) {
                            validSpawnPositions.add(checkPos.up());
                        }
                    }
                }
            }
            if (!validSpawnPositions.isEmpty()) {
                BlockPos spawnPos = validSpawnPositions.get(world.random.nextInt(validSpawnPositions.size()));
                VillagerEntity villager = EntityType.VILLAGER.create(serverWorld);

                if (villager != null) {
                    villager.refreshPositionAndAngles(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, 0, 0);
                    serverWorld.spawnEntityAndPassengers(villager);
                }
            }
        }
    }
}