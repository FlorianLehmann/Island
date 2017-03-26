package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.contracts.Budget;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.contracts.ContractsStrategy;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;
import fr.unice.polytech.si3.qgl.iaac.resources.Ingredient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.SECURITY_MARGIN;

/**
 * Created by florian on 18/03/2017.
 */
public class Factory implements State {

    private static final Logger logger = LogManager.getLogger(Factory.class);


    private Contract contract;
    private Contracts contracts;
    private ContractsStrategy contractsStrategy;

    @Override
    public String execute(Men men, Contracts contracts, Carte carte, Budget budget) {

        this.contracts = contracts;
        contractsStrategy = new ContractsStrategy(contracts, carte);
        contract = contractsStrategy.getManufacturedContract();

        int amount = contract.getRequired();
        List<Ingredient> ingredients = ((EnumManufacturedResources) contract.getName()).getIngredients();
        int amountManufactured = ((EnumManufacturedResources) contract.getName()).getAmountManufactured();

        if (ingredients.size() == 1)
            return men.transform(ingredients.get(0).getIngredient(),
                    (ingredients.get(0).getAmount()) * (amount + ((int) (amount * SECURITY_MARGIN))));
        return men.transform(ingredients.get(0).getIngredient(), ingredients.get(1).getIngredient(),
                (ingredients.get(0).getAmount()) * (amount + ((int) (amount * SECURITY_MARGIN))), (ingredients.get(1).getAmount()) * (amount + ((int) (amount * SECURITY_MARGIN))));
    }

    @Override
    public State changeState(ReadJSON json) {
        contract.sub(json.getProduction());
        contracts.getSecondaryContracts().remove(contract.getName());
        return this;
    }
}
