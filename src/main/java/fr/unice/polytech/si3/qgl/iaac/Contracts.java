package fr.unice.polytech.si3.qgl.iaac;

import fr.unice.polytech.si3.qgl.iaac.resources.EnumResources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lehmann on 04/02/17.
 */
public class Contracts {

    private List<Contract> primaryContracts;
    private List<Contract> secondaryContracts;

    /**
     * default constructor
     */
    public Contracts() {
        primaryContracts = new ArrayList<>();
        secondaryContracts = new ArrayList<>();
    }

    /**
     *
     * @param contract
     */
    public void add(Contract contract)  {
        if (contract.getName().isPrimary())
            primaryContracts.add(contract);
        else
            secondaryContracts.add(contract);
    }

    /**
     *
     * @param resource
     */
    public void remove(EnumResources resource) {
        if (resource.isPrimary()) {
            for (int i = 0; i < primaryContracts.size(); i++)
                if (primaryContracts.get(i).getName() == resource)
                    primaryContracts.remove(i);
        }
        else{
            for (int i = 0; i < secondaryContracts.size(); i++)
                if (secondaryContracts.get(i).getName() == resource)
                    secondaryContracts.remove(i);
        }
    }

    /**
     *
     * @return true if all contracts are completed
     */
    public boolean isCompleted() {
        return primaryContracts.isEmpty() && secondaryContracts.isEmpty();
    }

}
