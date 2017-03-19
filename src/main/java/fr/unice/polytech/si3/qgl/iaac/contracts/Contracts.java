package fr.unice.polytech.si3.qgl.iaac.contracts;

import fr.unice.polytech.si3.qgl.iaac.ground.GroundStrategy;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;
import fr.unice.polytech.si3.qgl.iaac.resources.Ingredient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lehmann on 04/02/17.
 */
public class Contracts {

    private List<Contract> primaryContracts;
    private List<Contract> secondaryContracts;

    private static final Logger logger = LogManager.getLogger(Contracts.class);


    /**
     * default constructor
     */
    public Contracts() {
        primaryContracts = new ArrayList<>();
        secondaryContracts = new ArrayList<>();
    }

    /**
     * @param contract
     */
    public void add(Contract contract) {
        if (contract.getName().isPrimary()) {
            primaryContracts.add(contract);
        } else {
            secondaryContracts.add(contract);
        }
    }

    /**
     * @param resource
     */
    public void remove(EnumResources resource) {
        if (resource.isPrimary()) {
            for (int i = 0; i < primaryContracts.size(); i++)
                if (primaryContracts.get(i).getName() == resource)
                    primaryContracts.remove(i);
        } else {
            for (int i = 0; i < secondaryContracts.size(); i++)
                if (secondaryContracts.get(i).getName() == resource)
                    secondaryContracts.remove(i);
        }
    }

    /**
     * @return true if all contracts are completed
     */
    public boolean isCompleted() {
        return primaryContracts.isEmpty();/* && secondaryContracts.isEmpty(); //todo /*&& secondaryContracts.isEmpty()*/
        // ;
    }

    public boolean isPrimaryCompleted() {
        return primaryContracts.isEmpty();
    }

    public boolean isSecondaryCompleted() {
        return secondaryContracts.isEmpty();
    }


    public boolean containRessource(EnumPrimaryResources ressource) {
        for (Contract contract : primaryContracts) {
            if (!contract.isCompleted() && contract.getName().equals(ressource)) return true;
        }
        return false;
    }

    public void verifyContractCompleted(Contract contract) {
        if (contract.isCompleted()) {
            remove(contract.getName());
        }
    }

    public Contract getPrimaryContract() {
        return primaryContracts.get(0);
    }

    public Contract getSecondaryContract() {
        return secondaryContracts.get(0);
    }

    public Contract getPrimaryContract(EnumPrimaryResources ressource) {
        for (Contract contract : primaryContracts) {
            if (contract.getName().equals(ressource)) return contract;
        }
        throw new RuntimeException("There are not primary contracts");
    }

    public void addColectedContract(int collect, EnumPrimaryResources resource) {
        for (Contract contract : primaryContracts) {
            if (contract.getName() == resource)
                contract.sub(collect);
        }
    }

    public void allocateContracts() {
        for (Contract secondaryContract : secondaryContracts) {
            EnumManufacturedResources manufacturedResource = (EnumManufacturedResources) secondaryContract.getName();
            for (Ingredient ingredient : manufacturedResource.getIngredients()) {
                primaryContractsAdd(ingredient.getIngredient(), ingredient.getAmount());
            }
        }
    }

    private void primaryContractsAdd(EnumPrimaryResources ingredient, int amount) {
        for (Contract contract : primaryContracts) {
            if (contract.getName() == ingredient) {
                contract.add(amount);
                return;
            }
        }

        add(new Contract(ingredient, amount));

    }

    public boolean couldCompleteAnotherContract() {
        for (Contract secondaryContract : secondaryContracts) {
            if (!secondaryContract.isCompleted()) {
                int amount = secondaryContract.getAmount();
                List<Ingredient> ingredients = ((EnumManufacturedResources) secondaryContract.getName()).getIngredients();
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
            int necessaryAmount = ingredient.getAmount()*(amount + ((int) amount/10));
            //logger.info("ingredient : " + ingredient.getIngredient() + " nb: " + necessaryAmount + "truenb " + primaryContracts.get(i).getAmount() );
            for (int i = 0; i < primaryContracts.size() ; i++) {
                if (primaryContracts.get(i).getName() == ingredient.getIngredient() && primaryContracts.get(i).isCompleted() ) {
                    numberOfIngredients--;
                }
            }
        }
        return numberOfIngredients == 0;
    }


    public Contract getManufacturedContract() {
        for (Contract secondaryContract : secondaryContracts) {
            if (!secondaryContract.isCompleted()) {
                int amount = secondaryContract.getAmount();
                List<Ingredient> ingredients = ((EnumManufacturedResources) secondaryContract.getName()).getIngredients();
                if (hasEnoughToMakeManufacturedContract(ingredients, amount)) {
                    Contract secContract = secondaryContract;
                    secondaryContracts.remove(secondaryContract);
                    return secContract;
                }
            }
        }
        throw new RuntimeException("no Manufactured contract");
    }
}
