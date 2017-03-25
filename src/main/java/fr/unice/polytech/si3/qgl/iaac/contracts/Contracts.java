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

    /**
     * attributes
     */
    private List<Contract> primaryContracts;
    private List<Contract> secondaryContracts;
    private List<Contract> notAPriorityPrimaryContract;
    private List<Contract> notAPrioritySecondaryContract;

    /**
     * default constructor
     */
    public Contracts() {
        primaryContracts = new ArrayList<>();
        secondaryContracts = new ArrayList<>();
        notAPriorityPrimaryContract = new ArrayList<>();
        notAPrioritySecondaryContract = new ArrayList<>();
    }

    /**
     * overloaded constructor
     * @param contracts
     */
    public Contracts(List<Contract> contracts) {
        this();
        for (Contract contract: contracts)
            add(contract);
    }

    /**
     * add a contract
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
     * remove the resource's contract
     * @param resource
     */
    public void remove(EnumResources resource) {
        if (resource.isPrimary()) {
            remove(resource, primaryContracts);
        } else {
            remove(resource, secondaryContracts);
        }
    }

    /**
     * remove resource in the list contracts
     * @param resource
     * @param contracts
     */
    private void remove(EnumResources resource, List<Contract> contracts) {
        for (int i = 0; i < contracts.size(); i++)
            if (contracts.get(i).getName() == resource)
                contracts.remove(i);
    }

    /**
     * @return true if all contracts are completed
     */
    public boolean isCompleted() {
        return isPrimaryCompleted() && isSecondaryCompleted();
    }

    /**
     *
     * @return true if all primary contracts are completed
     */
    public boolean isPrimaryCompleted() {
        return isCompleted(primaryContracts);
    }

    /**
     *
     * @return true if all secondary contracts are completed
     */
    public boolean isSecondaryCompleted() {
        return /*secondaryContracts.isEmpty()*/ isCompleted(secondaryContracts);
        //pas vide donc return false
    }

    /**
     *
     * @param contracts
     * @return true if all contracts contained in contracts are completed
     */
    private boolean isCompleted(List<Contract> contracts) {
        for (Contract contract: contracts)
            if(!contract.isCompleted())
                return false;
        return true;
    }

    //get contract ???
    public boolean containRessource(EnumPrimaryResources ressource) {
        for (Contract contract : primaryContracts) {
            if (!contract.isCompleted() && contract.getName().equals(ressource)) {
                return true;
            }
        }
        return false;
    }


    /**
     * TODO
     * @param contract
     */
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
                if (primaryContracts.get(i).getName() == ingredient.getIngredient() && primaryContracts.get(i).getCollected() >= necessaryAmount ) {
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
                if (hasEnoughToMakeManufacturedContract(ingredients, amount)) {
                    Contract secContract = secondaryContract;
                    secondaryContracts.remove(secondaryContract);
                    return secContract;
                }
            }
        }
        throw new RuntimeException("no Manufactured contract");
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
