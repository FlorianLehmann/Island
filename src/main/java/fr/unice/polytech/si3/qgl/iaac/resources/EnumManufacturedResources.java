package fr.unice.polytech.si3.qgl.iaac.resources;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.*;

/**
 * Created by florian on 09/02/2017.
 */
public enum EnumManufacturedResources implements EnumResources {

    GLASS(1, new Ingredient(QUARTZ, 10), new Ingredient(WOOD, 5)), //10 et 5
    INGOT(1, new Ingredient(ORE, 5), new Ingredient(WOOD, 5)),
    LEATHER(1, new Ingredient(FUR, 3)),
    PLANK(4, new Ingredient(WOOD, 1)),
    RUM(1, new Ingredient(SUGAR_CANE, 10), new Ingredient(FRUITS, 1));

    /**
     * attributes
     */
    private List<Ingredient> ingredients;
    private int amountManufactured;

    /**
     * default constructor
     * @param amountManufactured
     * @param ingredients
     */
    EnumManufacturedResources(int amountManufactured, Ingredient ... ingredients) {
        this.ingredients = new ArrayList<>();
        for (Ingredient ingredient : ingredients)
            this.ingredients.add(ingredient);
        this.amountManufactured = amountManufactured;
    }

    /**
     *
     * @return false
     */
    public boolean isPrimary() {
        return false;
    }

    @Override
    public String toString() {
        return name();
    }

    /**
     *
     * @param resource
     * @return the enum name's associated
     */
    public static EnumResources getEnumManufacturedResources(String resource) {
        return EnumManufacturedResources.valueOf(resource);
    }

    /**
     *
     * @param resource
     * @return true if the resource is a manufactured resource
     */
    public boolean isManufactured(String resource) {
        List<EnumManufacturedResources> resources = Arrays.asList(EnumManufacturedResources.values());
        for (int i = 0; i < resources.size(); i++)
            if (resources.get(i).name().equals(resource))
                return true;
        return false;
    }

    /**
     *
     * @return the list of ingredients
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     *
     * @return the amount which can be manufactured with these ingredients
     */
    public int getAmountManufactured() {
        return amountManufactured;
    }

}
