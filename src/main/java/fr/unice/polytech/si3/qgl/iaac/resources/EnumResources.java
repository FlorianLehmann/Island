package fr.unice.polytech.si3.qgl.iaac.resources;

/**
 * Created by lehmann on 04/02/17.
 */
public interface EnumResources {

    public static EnumResources getEnumResources(String string) {
        if (EnumPrimaryResources.isPrimary(string))
            return Enum.valueOf(EnumPrimaryResources.class, string);
        return Enum.valueOf(EnumManufacturedResources.class, string);
    }

    public boolean isPrimary();

}
