package fr.unice.polytech.si3.qgl.iaac.resources;

import java.util.Arrays;
import java.util.List;

/**
 * Created by florian on 09/02/2017.
 */
public enum EnumManufacturedResources implements EnumResources {

    GLASS("GLASS"),
    INGOT("INGOT"),
    LEATHER("LEATHER"),
    PLANK("PLANK"),
    RUM("RUM");

    String name;

    EnumManufacturedResources(String name) {
        this.name = name;
    }

    public boolean isPrimary() {
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    //todo
    public static EnumResources getEnumManufacturedResources(String resource) {
        return EnumManufacturedResources.valueOf(resource);
    }

    //todo
    public boolean isManufactured(String resource) {
        List<EnumManufacturedResources> resources = Arrays.asList(EnumManufacturedResources.values());
        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i).name == resource)
                return true;
        }
        return false;
    }

}
