package io.github.strikerrocker.vt.content.blocks;

import com.google.gson.JsonObject;
import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public record BlockConditions(String object) implements ICondition {

    private static final ResourceLocation NAME = new ResourceLocation(VanillaTweaks.MODID, "blocks");

    @Override
    public ResourceLocation getID() {
        return NAME;
    }

    @Override
    public boolean test() {
        if (object.equals("pedestal")) return Blocks.enablePedestal.get();
        if (object.equals("storage_blocks")) return Blocks.enableStorageBlocks.get();
        return false;
    }

    public static class Serializer implements IConditionSerializer<BlockConditions> {
        static final BlockConditions.Serializer INSTANCE = new BlockConditions.Serializer();

        @Override
        public void write(JsonObject json, BlockConditions value) {

        }

        @Override
        public BlockConditions read(JsonObject json) {
            return new BlockConditions(json.get("object").getAsString());
        }

        @Override
        public ResourceLocation getID() {
            return NAME;
        }
    }
}
