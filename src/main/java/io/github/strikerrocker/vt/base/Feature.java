package io.github.strikerrocker.vt.base;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public abstract class Feature {
    protected Module module;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setup(FMLCommonSetupEvent event) {
    }

    public void configChanged(ModConfig.ModConfigEvent event) {
    }

    public void setupConfig(ForgeConfigSpec.Builder builder) {
    }

    public boolean usesEvents() {
        return false;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
