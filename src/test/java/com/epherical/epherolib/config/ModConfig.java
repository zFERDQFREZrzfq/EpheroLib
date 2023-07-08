package com.epherical.epherolib.config;

public class ModConfig<T extends CommonNode<?>, V extends CommonNode<?>> extends CommonConfig<T, V> {

    /*public static int version = 1;
    public static int silly = 5;
    public static String bozo = "Dingus";*/




    @Override
    public void parseConfig(V node) {

    }

    @Override
    public T generateConfig(T node) {
        /*try {
            node.push("version").setValue(5).comment("Config Version, do not edit.").pop();
            node.push("silly").setValue(silly).comment("This is very silly").pop();
            node.push("bozo").setValue(bozo).comment("Very Bozo, very silly").pop();
            node.push("actions")
                    .push("bingus").setValue("Howdy").comment("This is an object").pop()
                    .push("dingus").setValue("Binge").comment("Not An Object").pop()
                    .pop();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return node;
    }
}
