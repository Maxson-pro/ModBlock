package net.maxim.modblock;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.maxim.modblock.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Modblock implements ModInitializer {
    public static final String MOD_ID = "modblock";
    public static final ItemGroup MOD_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "mod_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.modblock.mod_group"))
                    .icon(() -> new ItemStack(Items.VILLAGER_SPAWN_EGG))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.VILLAGER_SPAWNER_BLOCK);}).build());

    @Override
    public void onInitialize() {
        ModBlocks.registerModBlocks();
    }
}