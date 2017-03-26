package fr.unice.polytech.si3.qgl.iaac.resources;


import java.util.Arrays;
import java.util.List;

/**
 * Created by florian on 09/02/2017.
 */
public enum EnumPrimaryResources implements EnumResources {

    FISH,
    FLOWER,
    FRUITS,
    FUR,
    ORE,
    QUARTZ,
    SUGAR_CANE,
    WOOD;

    public static boolean isPrimary(String resource) {
        List<EnumPrimaryResources> resources = Arrays.asList(EnumPrimaryResources.values());
        for (int i = 0; i < resources.size(); i++)
            if (resources.get(i).name().equals(resource))
                return true;
        return false;
    }

    public static EnumResources getEnumPrimaryResources(String resource) {
        return EnumPrimaryResources.valueOf(resource);
    }

    public boolean isPrimary() {
        return true;
    }

    @Override
    public String toString() {
        return name();
    }

}
