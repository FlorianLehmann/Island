package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;
import fr.unice.polytech.si3.qgl.iaac.resources.Ingredient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by florian on 18/03/2017.
 */
public class Factory implements State {

    private static final Logger logger = LogManager.getLogger(Factory.class);


    private Contract contract;

    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {
        contract = contracts.getManufacturedContract();
        int amount = contract.getRequired();
        List<Ingredient> ingredients = ((EnumManufacturedResources)contract.getName()).getIngredients();
        int amountManufactured = ((EnumManufacturedResources)contract.getName()).getAmountManufactured();
        if (ingredients.size() == 1)
            return men.transform(ingredients.get(0).getIngredient(),
                    (ingredients.get(0).getAmount())*(amount + ((int) amount/10)));
        return men.transform(ingredients.get(0).getIngredient(), ingredients.get(1).getIngredient(),
                (ingredients.get(0).getAmount())*(amount + ((int) amount/10)), (ingredients.get(1).getAmount())*(amount + ((int) amount/10)));
    }

    @Override
    public State changeState(ReadJSON json) {
        contract.sub(json.getAnswer().getProduction());
        logger.info("TEST " + contract.isCompleted() + " amount" + contract.getDebt());
        return this;
    }
}
