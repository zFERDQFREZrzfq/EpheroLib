package com.epherical.epherolib.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;

public class ForgeNodeWrapper implements CommonNode<ForgeConfigSpec.Builder> {

    ForgeConfigSpec.Builder builder;

    public ForgeNodeWrapper(ForgeConfigSpec.Builder builder) {
        this.builder = builder;

    }


    public ForgeConfigSpec.Builder getBuilder() {
        return builder;
    }

    @Override
    public CommonNode<ForgeConfigSpec.Builder> pop() {
        builder.pop();
        return this;
    }

    @Override
    public CommonNode<ForgeConfigSpec.Builder> push(String pushed) {
        builder.push(pushed);
        return this;
    }

    @Override
    public CommonNode<ForgeConfigSpec.Builder> comment(String comment) {
        builder.comment(comment);
        return this;
    }

    @Override
    public <V> CommonNode<ForgeConfigSpec.Builder> setValue(Class<V> type, V value) throws Exception {
        return this;
    }

    @Override
    public CommonNode<ForgeConfigSpec.Builder> setValue(Type type, Object obj) throws Exception {
        return this;
    }

    @Override
    public int getInt() {
        return 0;
    }

    @Override
    public int getInt(int defaultValue) {
        return 0;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public String getString(String defaultString) {
        return null;
    }

    @Override
    public ForgeConfigSpec.Builder getWrapper() {
        return null;
    }
}
