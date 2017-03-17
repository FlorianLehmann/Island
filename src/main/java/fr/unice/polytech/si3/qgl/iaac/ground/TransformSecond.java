package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import fr.unice.polytech.si3.qgl.iaac.contracts.SecondaryContract;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;

/**
 * Created by sebde on 24/02/2017.
 */
public class TransformSecond implements State {
    private Contract contract;
    private Contracts contracts;

    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {
        this.contracts = contracts;
        contract =  contracts.getSecondaryContract();
        if(((SecondaryContract) contract).getNbPrimaryRessources()==1) return men.transform(((EnumManufacturedResources)contract.getName()).getNeeded().get(0),((SecondaryContract) contract).getNbFirstRessource());
        return men.transform(((EnumManufacturedResources)contract.getName()).getNeeded().get(0),((EnumManufacturedResources)contract.getName()).getNeeded().get(1),((SecondaryContract)contract).getNbFirstRessource(),((SecondaryContract)contract).getNbSecondRessource());
    }

    @Override
    public State wait(ReadJSON json) {
        contract.sub(json.getCollect());
        contracts.remove(contract.getName());
        return new DefineWaySecond();
    }
}