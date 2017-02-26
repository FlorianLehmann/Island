package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.Contract;
import fr.unice.polytech.si3.qgl.iaac.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;

/**
 * Created by sebde on 24/02/2017.
 */
public class TransformSecond implements State {
    private Contract contract;
    private Contracts contracts;
    private int collect;

    public TransformSecond(int collect){
        this.collect=collect;
    }

    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {
        this.contracts = contracts;
        contract =  contracts.getSecondaryContract();
        return men.transform(((EnumManufacturedResources)contract.getName()).getNeeded().get(0),collect);
    }

    @Override
    public State wait(ReadJSON json) {
        contract.sub(json.getCollect());
        if (contract.isCompleted()) {
            contracts.remove(contract.getName());
            return new DefineWaySecond();
        } else {
            return new DefineWaySecond();
            //return new TounerRond();
        }
    }
}
