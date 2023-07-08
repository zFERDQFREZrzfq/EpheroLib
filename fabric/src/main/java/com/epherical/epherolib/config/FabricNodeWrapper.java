package com.epherical.epherolib.config;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.CommentedConfigurationNode;

import java.lang.reflect.Type;

public class FabricNodeWrapper implements CommonNode<CommentedConfigurationNode> {

    private CommentedConfigurationNode node;

    public FabricNodeWrapper(CommentedConfigurationNode configurationNode) {
        this.node = configurationNode;

    }

    @Override
    public @Nullable CommonNode<CommentedConfigurationNode> pop() {
        node = node.parent();
        return this;
    }

    @Override
    public CommonNode<CommentedConfigurationNode> push(String pushed) {
        node = node.node(pushed);
        return this;
    }

    @Override
    public CommonNode<CommentedConfigurationNode> comment(String comment) {
        node = node.comment(comment);
        return this;
    }

    @Override
    public <V> CommonNode<CommentedConfigurationNode> setValue(Class<V> type, V value) throws Exception {
        node = node.set(type, value);
        return this;
    }

    @Override
    public CommonNode<CommentedConfigurationNode> setValue(Type type, Object obj) throws Exception {
        node = node.set(type, obj);
        return this;
    }

    @Override
    public int getInt() {
        return node.getInt();
    }

    @Override
    public int getInt(int defaultValue) {
        return node.getInt(defaultValue);
    }

    @Override
    public String getString() {
        return node.getString();
    }

    @Override
    public String getString(String defaultString) {
        return node.getString(defaultString);
    }

    @Override
    public CommentedConfigurationNode getWrapper() {
        return node;
    }
}
