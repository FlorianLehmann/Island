package fr.unice.polytech.si3.qgl.iaac.contracts;

import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;
import fr.unice.polytech.si3.qgl.iaac.resources.Ingredient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources.SECURITY_MARGIN;

/**
 * Created by lehmann on 04/02/17.
 */
public class Contracts {

    /**
     * attributes
     */
    private Map<EnumResources, Contract> primaryContracts;
    private Map<EnumResources, Contract> secondaryContracts;

    /**
     * default constructor
     */
    public Contracts() {
        primaryContracts = new HashMap<>();
        secondaryContracts = new HashMap<>();
    }

    /**
     * overloaded constructor
     * @param contracts
     */
    public Contracts(List<Contract> contracts) {
        this();
        for (Contract contract : contracts)
            add(contract);
    }

    /**
     * add a contract
     * @param contract
     */
    public void add(Contract contract) {
        if (contract.getName().isPrimary()) {
            if (primaryContracts.containsKey(contract.getName()))
                primaryContracts.get(contract.getName()).addRequired(contract.getRequired());
            else
                primaryContracts.put(contract.getName(), contract);
        } else {
            secondaryContracts.put(contract.getName(), contract);
        }


    }

    /**
     * convert ManufacturedContract to PrimaryContract
     */
    public void allocateContracts() {
        for (Map.Entry<EnumResources, Contract> secondaryContract : secondaryContracts.entrySet()) {
            EnumManufacturedResources manufacturedResource = (EnumManufacturedResources) secondaryContract.getValue().getName();
            for (Ingredient ingredient : manufacturedResource.getIngredients()) {
                primaryContractsAdd(ingredient.getIngredient(), ingredient.getAmount() * (secondaryContract.getValue().getRequired() + ((int) (secondaryContract.getValue().getRequired() * SECURITY_MARGIN))));
            }
        }
    }

    /**
     * Add the required amount for a contract
     *
     * @param ingredient
     * @param amount
     */
    private void primaryContractsAdd(EnumPrimaryResources ingredient, int amount) {

        if (primaryContracts.containsKey(ingredient))
            primaryContracts.get(ingredient).addRequired(amount);
        else
            add(new Contract(amount, ingredient));

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
    private void remove(EnumResources resource, Map<EnumResources, Contract> contracts) {
        contracts.remove(resource);
    }

    /**
     * @return true if all contracts are completed
     */
    public boolean isCompleted() {
        return isPrimaryCompleted() && isSecondaryCompleted();
    }

    /**
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
        return isCompleted(secondaryContracts);
    }

    /**
     *
     * @param contracts
     * @return true if all contracts contained in contracts are completed
     */
    private boolean isCompleted(Map<EnumResources, Contract> contracts) {
        for (Map.Entry<EnumResources, Contract> contract : contracts.entrySet())
            if (!contract.getValue().isCompleted())
                return false;
        return true;
    }

    /**
     *
     * @param ressource
     * @return true if the contract is not completed
     */
    public boolean needResource(EnumPrimaryResources ressource) {
        if (primaryContracts.containsKey(ressource))
            return !primaryContracts.get(ressource).isCompleted();
        return false;
    }

    /**
     *
     * @param collect
     * @param resource
     */
    public void addColectedContract(int collect, EnumPrimaryResources resource) {
        primaryContracts.get(resource).add(collect);
    }

    public Map<EnumResources, Contract> getPrimaryContracts() {
        return primaryContracts;
    }

    public Map<EnumResources, Contract> getSecondaryContracts() {
        return secondaryContracts;
    }

    public Contract getPrimaryContracts(EnumPrimaryResources resource) {
        return primaryContracts.get(resource);
    }

}
