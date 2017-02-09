package fr.unice.polytech.si3.qgl.iaac.resources;

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

    @Override
    public String toString() {
        return name;
    }

}
