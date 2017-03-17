package fr.unice.polytech.si3.qgl.iaac.ground;

import fr.unice.polytech.si3.qgl.iaac.contracts.Contract;
import fr.unice.polytech.si3.qgl.iaac.contracts.Contracts;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import fr.unice.polytech.si3.qgl.iaac.resources.EnumManufacturedResources;

/**
 * Created by sebde on 24/02/2017.
 */
public class ExploreSecond implements State {
    private Contract contract;
    private int numeroRessource=0;

    public ExploreSecond(int numeroRessource){
        this. numeroRessource=numeroRessource;
    }

    @Override
    public String execute(Men men, Contracts contracts, Carte carte) {
        contract =  contracts.getSecondaryContract();
        return men.explore();
    }

    //TODO refaire les conditions
    @Override
    public State wait(ReadJSON json) {
        boolean resource;
        resource = false;
        for (int i = 0; i < json.getResources().size() ; i++) {
            if(json.getResources().get(i).equals(((EnumManufacturedResources)contract.getName()).getNeeded().get(numeroRessource).toString()))
                resource = true;
        }

        if (resource)
            return new ExploitSecond(numeroRessource);
        else
            return new DefineWaySecond(numeroRessource);
    }
}
