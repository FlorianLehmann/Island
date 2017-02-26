package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.Contract;
import fr.unice.polytech.si3.qgl.iaac.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;

/**
 * Created by sebde on 24/02/2017.
 */
public class ExploreSecond implements State {
    private Contract contract;

    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {
        contract =  contracts.getSecondaryContract();
        return men.explore();
    }


    @Override
    public State wait(ReadJSON json) {
        boolean resource;
        resource = false;
        for (int i = 0; i < json.getResources().size() ; i++) {
            if(json.getResources().get(i).equals(((EnumManufacturedResources)contract.getName()).getNeeded().get(0).toString()))
                resource = true;
        }

        if (resource)
            return new ExploitSecond();
        else
            return new DefineWaySecond();
    }
}
