package fr.unice.polytech.si3.qgl.iaac.resources;

/**
 * Created by florian on 27/02/2017.
 */
public class Ingredient {

    /**
     * attributes
     */
    private EnumPrimaryResources ingredient;
    private int amount;

    /**
     * default constructor
     * @param ingredient
     * @param amount
     */
    public Ingredient(EnumPrimaryResources ingredient, int amount){
        if (amount <= 0)
            throw new RuntimeException("The amount required cannot be less than 0");
        this.ingredient = ingredient;
        this.amount = amount;
    }

    /**
     *
     * @return his name
     */
    public EnumPrimaryResources getIngredient() {
        return ingredient;
    }

    /**
     *
     * @return the amount required
     */
    public int getAmount() {
        return amount;
    }
    
}
