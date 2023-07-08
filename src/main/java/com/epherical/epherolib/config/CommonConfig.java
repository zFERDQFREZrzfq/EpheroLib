package com.epherical.epherolib.config;

public abstract class CommonConfig<T, V> {

    private static int configVersion = 1;

    public static void setConfigVersion(int configVersion) {
        CommonConfig.configVersion = configVersion;
    }
    public static void incrementVersion() {
        configVersion += 1;
    }

    public static int getConfigVersion() {
        return configVersion;
    }

    /**
     * After a config has been generated, we need to grab the values from the config and potentially assign them to fields
     * for easy retrieval
     */
    public abstract void parseConfig(V node);


    /**
     * Generate the layout for a config here with all comments and values.
     * @return The returned node after generating the config.
     */
    public abstract T generateConfig(T node);



}
