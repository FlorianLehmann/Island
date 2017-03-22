package fr.unice.polytech.si3.qgl.iaac.contracts;

import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;
import fr.unice.polytech.si3.qgl.iaac.resources.Ingredient;

import java.util.ArrayList;
import java.util.List;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.*;

/**
 * Created by lehmann on 04/02/17.
 */
public class Contracts {

    private List<Contract> primaryContracts;
    private List<Contract> secondaryContracts;
    private List<Contract> notAPriorityPrimaryContract;
    private List<Contract> notAPrioritySecondaryContract;

    public Contracts() {
        primaryContracts = new ArrayList<>();
        secondaryContracts = new ArrayList<>();
        notAPriorityPrimaryContract = new ArrayList<>();
        notAPrioritySecondaryContract = new ArrayList<>();
    }

    /**
     * default constructor
     */
    public Contracts(List<Contract> contracts) {
        primaryContracts = new ArrayList<>();
        secondaryContracts = new ArrayList<>();
        notAPriorityPrimaryContract = new ArrayList<>();
        notAPrioritySecondaryContract = new ArrayList<>();
        for (Contract contract: contracts)
            add(contract);
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
        return isPrimaryCompleted() && isSecondaryCompleted();
    }

    public boolean isPrimaryCompleted() {
        for (Contract contract: primaryContracts) {
            if(!contract.isCompleted()){
                return false;
            }
        }
        return true;
    }

    public boolean isSecondaryCompleted() {
        return secondaryContracts.isEmpty();
    }


    public boolean containRessource(EnumPrimaryResources ressource) {
        for (Contract contract : primaryContracts) {
            if (!contract.isCompleted() && contract.getName().equals(ressource)) {
                return true;
            }
        }
        return false;
    }

    public boolean contain(EnumPrimaryResources ressource) {
        for (Contract contract : primaryContracts) {
            if (contract.getName().equals(ressource)) {
                return true;
            }
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
                contract.add(collect);
        }
    }

    public void allocateContracts() {
        for (Contract secondaryContract : secondaryContracts) {
            EnumManufacturedResources manufacturedResource = (EnumManufacturedResources) secondaryContract.getName();
            for (Ingredient ingredient : manufacturedResource.getIngredients()) {
                primaryContractsAdd(ingredient.getIngredient(), ingredient.getAmount() * (secondaryContract.getRequired() + ((int) secondaryContract.getRequired()/10)));
            }
        }
    }

    private void primaryContractsAdd(EnumPrimaryResources ingredient, int amount) {
        for (Contract contract : primaryContracts) {
            if (contract.getName() == ingredient) {
                contract.addRequired(amount);
                return;
            }
        }

        add(new Contract(amount, ingredient));

    }

    public boolean couldCompleteAnotherContract() {
        for (Contract secondaryContract : secondaryContracts) {
            if (!secondaryContract.isCompleted()) {
                int amount = secondaryContract.getRequired();
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
            for (int i = 0; i < primaryContracts.size() ; i++) {
                if (primaryContracts.get(i).getName() == ingredient.getIngredient() && primaryContracts.get(i).getDebt() >= necessaryAmount ) {
                    numberOfIngredients--;
                }
            }
        }
        return numberOfIngredients == 0;
    }


    public Contract getManufacturedContract() {
        for (Contract secondaryContract : secondaryContracts) {
            if (!secondaryContract.isCompleted()) {
                int amount = secondaryContract.getRequired();
                List<Ingredient> ingredients = ((EnumManufacturedResources) secondaryContract.getName()).getIngredients();
                if (hasEnoughToMakeManufacturedContract2(ingredients, amount)) {
                    Contract secContract = secondaryContract;
                    secondaryContracts.remove(secondaryContract);
                    return secContract;
                }
            }
        }
        throw new RuntimeException("no Manufactured contract");
    }

    private boolean hasEnoughToMakeManufacturedContract2(List<Ingredient> ingredients, int amount) {
        int numberOfIngredients = ingredients.size();
        for (Ingredient ingredient :
                ingredients) {
            int necessaryAmount = ingredient.getAmount()*(amount + ((int) amount/10));
            for (int i = 0; i < primaryContracts.size() ; i++) {
                if (primaryContracts.get(i).getName() == ingredient.getIngredient() && primaryContracts.get(i).getDebt() >= necessaryAmount ) {
                    primaryContracts.get(i).sub(necessaryAmount);
                    numberOfIngredients--;
                }
            }
        }
        return numberOfIngredients == 0;
    }



    public void sortPrimaryContracts(int budget) {
        List<Contract> primaryContractsSorted = new ArrayList();
        int amountMax = budget / 2;

        primaryContractsSorted = addToPrimaryContractsSorted(FISH,primaryContractsSorted,amountMax);
        primaryContractsSorted = addToPrimaryContractsSorted(WOOD,primaryContractsSorted,amountMax);
        primaryContractsSorted = addToPrimaryContractsSorted(QUARTZ,primaryContractsSorted,amountMax);

        for (int i = 0; i < primaryContracts.size() ; i++) {
            if (primaryContracts.get(i).getRequired() <= amountMax) {
                primaryContractsSorted.add(primaryContracts.get(i));
                primaryContracts.remove(i);
            }

        }

        for (int i = 0; i < primaryContracts.size() ; i++) {
            primaryContractsSorted.add(primaryContracts.get(i));
        }

        primaryContracts = primaryContractsSorted;

    }

    private List<Contract> addToPrimaryContractsSorted(EnumResources resources, List<Contract> primaryContractsSorted,int amountMax){
        for (int i = 0; i < primaryContracts.size() ; i++) {
            if (resources == primaryContracts.get(i).getName() && primaryContracts.get(i).getRequired() <= amountMax) {
                primaryContractsSorted.add(primaryContracts.get(i));
                primaryContracts.remove(i);
            }
        }
        return primaryContractsSorted;
    }

    public Contract getContract(){
        for (Contract contract: primaryContracts) {
            if(!contract.isCompleted()){
                return contract;
            }
        }
        throw new RuntimeException("no contract");
    }

    public void changePrimaryContractToNotAPriorityPrimaryContract(Contract contract){
        for(int i=0; i< primaryContracts.size();i++){
            if(primaryContracts.get(i).getName() == contract.getName()){
                notAPriorityPrimaryContract.add(contract);
                primaryContracts.remove(i);

            }
        }
    }

}
