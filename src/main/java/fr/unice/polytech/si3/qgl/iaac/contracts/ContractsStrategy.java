package fr.unice.polytech.si3.qgl.iaac.contracts;

import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;
import fr.unice.polytech.si3.qgl.iaac.resources.Ingredient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.SECURITY_MARGIN;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.*;

/**
 * Created by florian on 25/03/2017.
 */
public class ContractsStrategy {

    private static final Logger logger = LogManager.getLogger(ContractsStrategy.class);
    private Contracts contracts;
    private Carte carte;


    public ContractsStrategy(Contracts contracts, Carte carte) {
        this.contracts = contracts;
        this.carte = carte;
    }


    public boolean couldCompleteAnotherContract() {
        for (Map.Entry<EnumResources, Contract> secondaryContract: contracts.getSecondaryContracts().entrySet()) {
            if (!secondaryContract.getValue().isCompleted()) {

                int amount = secondaryContract.getValue().getRequired();

                List<Ingredient> ingredients = ((EnumManufacturedResources) secondaryContract.getValue().getName()).getIngredients();
                if (hasEnoughToMakeManufacturedContract(ingredients, amount))
                    return true;
            }
        }
        return false;
    }

    private boolean hasEnoughToMakeManufacturedContract(List<Ingredient> ingredients, int amount) {
        int numberOfIngredients = ingredients.size();
        for (Ingredient ingredient :
                ingredients) {
            int necessaryAmount = ingredient.getAmount() * (amount + ((int) (amount * SECURITY_MARGIN)));
            logger.info("NECESSARY" + necessaryAmount);
            for (Map.Entry<EnumResources, Contract> primaryContract: contracts.getPrimaryContracts().entrySet()) {

                if (primaryContract.getValue().getName() == ingredient.getIngredient() && primaryContract.getValue().getCollected() >= necessaryAmount) {
                    numberOfIngredients--;
                }
            }
        }
        return numberOfIngredients == 0;
    }

    public Contract getManufacturedContract() {
        for (Map.Entry<EnumResources, Contract> secondaryContract: contracts.getSecondaryContracts().entrySet()) {
            if (!secondaryContract.getValue().isCompleted()) {
                int amount = secondaryContract.getValue().getRequired();
                List<Ingredient> ingredients = ((EnumManufacturedResources) secondaryContract.getValue().getName()).getIngredients();
                if (hasEnoughToMakeManufacturedContract(ingredients, amount)) {
                    Contract secContract = secondaryContract.getValue();
                    contracts.remove(secondaryContract.getValue().getName());
                    return secContract;
                }
            }
        }
        throw new RuntimeException("no Manufactured contract");
    }

    private boolean couldBeRecolted(EnumPrimaryResources resource, int amountMax) {
        if (contracts.needResource(resource) && carte.hasResource(resource) && contracts.getPrimaryContracts(resource).getRequired() <= amountMax)
            return true;
        return false;
    }

    private boolean mayBeRecolted(EnumPrimaryResources resource) {
        if (contracts.needResource(resource) && carte.hasResource(resource))
            return true;
        return false;
    }

    public EnumPrimaryResources nextContract(int budget) {

        int amountMax = budget / 2;

        if (couldBeRecolted(FISH, amountMax))
            return FISH;

        if (couldBeRecolted(WOOD, amountMax))
            return WOOD;

        if (couldBeRecolted(QUARTZ, amountMax))
            return QUARTZ;

        for (Map.Entry<EnumResources, Contract> contractEntry: contracts.getPrimaryContracts().entrySet()) {
            if (couldBeRecolted((EnumPrimaryResources) contractEntry.getValue().getName(), amountMax)) {
                return (EnumPrimaryResources) contractEntry.getValue().getName();
            }
        }

        for (Map.Entry<EnumResources, Contract> contractEntry: contracts.getPrimaryContracts().entrySet()) {
            if (mayBeRecolted((EnumPrimaryResources) contractEntry.getValue().getName())) {
                return (EnumPrimaryResources) contractEntry.getValue().getName();
            }
        }

        throw new RuntimeException("All contracts are completed");
    }
}