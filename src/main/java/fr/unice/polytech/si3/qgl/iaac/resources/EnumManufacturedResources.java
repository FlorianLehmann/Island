package fr.unice.polytech.si3.qgl.iaac.resources;

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

    @Override
    public String toString() {
        return name;
    }


}
