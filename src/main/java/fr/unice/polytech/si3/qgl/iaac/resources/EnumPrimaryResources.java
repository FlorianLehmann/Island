package fr.unice.polytech.si3.qgl.iaac.resources;

import java.util.Arrays;
import java.util.List;

/**
 * Created by florian on 09/02/2017.
 */
public enum EnumPrimaryResources implements EnumResources {

    FISH("FISH"),
    FLOWER("FLOWER"),
    FRUITS("FRUITS"),
    FUR("FUR"),
    ORE("ORE"),
    QUARTZ("QUARTZ"),
    SUGAR_CANE("SUGAR_CANE"),
    WOOD("WOOD");

    String name;

    EnumPrimaryResources(String name) {
        this.name = name;
    }

    public boolean isPrimary() {
        return true;
    }

    //todo
    public static boolean isPrimary(String resource) {
        List<EnumPrimaryResources> resources = Arrays.asList(EnumPrimaryResources.values());
        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i).name == resource)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    //todo
    public static EnumResources getEnumPrimaryResources(String resource) {
        return EnumPrimaryResources.valueOf(resource);
    }

}
