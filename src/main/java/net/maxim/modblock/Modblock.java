package net.maxim.modblock;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.maxim.modblock.block.VillagerSpawnerBlock;
import net.maxim.modblock.block.VillagerSpawnerEntity;

public class Modblock implements ModInitializer {
    public static final String MOD_ID = "modblock";

    public static final Block VILLAGER_SPAWNER_BLOCK = Registry.register(
            Registries.BLOCK,
            new Identifier(MOD_ID, "villager_spawner_block"),
            new VillagerSpawnerBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK))
    );

    public static final Item VILLAGER_SPAWNER_ITEM = Registry.register(
            Registries.ITEM,
            new Identifier(MOD_ID, "villager_spawner_block"),
            new BlockItem(VILLAGER_SPAWNER_BLOCK, new Item.Settings())
    );
    public static final BlockEntityType<VillagerSpawnerEntity> VILLAGER_SPAWNER_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(MOD_ID, "villager_spawner_entity"),
            FabricBlockEntityTypeBuilder.create(VillagerSpawnerEntity::new, VILLAGER_SPAWNER_BLOCK).build()
    );
    public static final ItemGroup MOD_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(MOD_ID, "mod_group"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(VILLAGER_SPAWNER_BLOCK))
                    .displayName(Text.translatable("itemGroup.modblock.mod_group"))
                    .entries((context, entries) -> {
                        entries.add(VILLAGER_SPAWNER_ITEM);
                    })
                    .build()
    );

    @Override
    public void onInitialize() {

    }
}