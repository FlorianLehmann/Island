package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Florian on 18/12/2016.
 */
public enum EnumSecondaire {
    GLASS("GLASS"),
    INGOT("INGOT"),
    LEATHER("LEATHER"),
    PLANK("PLANK"),
    RUM("RUM");

    String name;
    static Map<String, EnumSecondaire> map = new HashMap();

    /**
     * initialize la hashmap
     */
    static {
        Arrays.asList(EnumSecondaire.values()).forEach(cw -> map.put(cw.name, cw));
    }

    /**
     * default constructor
     * @param name
     */
    EnumSecondaire(String name) {
        this.name = name;
    }

    /**
     *
     * @param name
     * @return true if it is not a primary resource
     */
    public static boolean isSecond(String name) {
        return map.containsKey(name);
    }

}
