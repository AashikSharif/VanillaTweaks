package io.github.strikerrocker.vt.base;

import io.github.strikerrocker.vt.VanillaTweaks;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;
import java.util.Map;

public abstract class Module {
    private ForgeConfigSpec.Builder configBuilder;
    private Map<String, Feature> features = new HashMap<>();
    private String name;
    private String comments;

    public Module(String name, String comments, boolean requiresMCRestart, ForgeConfigSpec.Builder configBuilder) {
        this.name = name;
        this.comments = comments;
        this.configBuilder = configBuilder;
        addFeatures();
    }

    public abstract void addFeatures();

    public void setup() {
        features.values().stream().filter(Feature::usesEvents).forEach(MinecraftForge.EVENT_BUS::register);
        features.values().stream().filter(Feature::usesEvents).forEach(feature -> VanillaTweaks.LOGGER.debug("Registered Event Handler class" + feature.getClass().getName()));
        features.values().forEach(Feature::setup);
    }

    public void setupConfig() {
        configBuilder.comment(comments).push(name);
        features.values().forEach(feature -> feature.setupConfig(configBuilder));
        configBuilder.pop();
    }

    protected void registerFeature(String name, Feature feature) {
        features.put(name, feature);
        feature.setName(name);
        feature.setModule(this);
    }

    public Feature getFeature(String name) {
        return features.get(name);
    }

    public String getName() {
        return name;
    }
}