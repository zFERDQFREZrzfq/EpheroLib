package com.epherical.epherolib.config;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;

public interface CommonNode<T> {

    CommonNode<T> pop();

    /**
     * Push a node to work on the next value. Any time you push, you will need to pop after you are done.
     * @return The new node you are working with
     */
    CommonNode<T> push(String pushed);

    CommonNode<T> comment(String comment);

    <V> CommonNode<T> setValue(Class<V> type, V value) throws Exception;

    CommonNode<T> setValue(Type type, Object obj) throws Exception;

    int getInt();

    int getInt(int defaultValue);

    String getString();

    String getString(String defaultString);

    T getWrapper();



}
