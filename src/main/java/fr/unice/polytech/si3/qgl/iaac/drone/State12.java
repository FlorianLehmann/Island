package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import java.util.HashMap;

public class State12 implements State {

    /**
     *
     * Demande un SCAN
     *
     */
    @Override
    public void execute(Drone drone) {
	drone.setAction(SCAN.toString(""));
    drone.addCase();
    }

    /**
     *
     * Analyse du résultat
     *
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
	if(ReadJSON.getInformations().containsKey("sites") /*&& !((String) ReadJSON.getInformations().get("sites")).equals("")*/) {// TODO:
        drone.setPU((String) ReadJSON.getInformations().get("sites"));
    }
	if(ReadJSON.getInformations().containsKey("creeks") /*&& !((String)ReadJSON.getInformations().get("sites")).equals("")*/) {// TODO:
       drone.setCreek((String) ReadJSON.getInformations().get("creeks"));
    }
    for(int i=0;i<ReadJSON.getbiome().size;i++){
        switch (ReadJSON.getbiome().get(i)){
            case "TROPICAL_RAIN_FOREST":
                wood.addWood(drone.getPoint());
                flower.addFlower(drone.getPoint());
                sugarCane.addSugarCane(drone.getPoint());
                break;
            case "MANGROVE":
                wood.addWood(drone.getPoint());
                flower.addFlower(drone.getPoint());
                break;
            case "TROPICAL_SEASONAL_FOREST":
                wood.addWood(drone.getPoint());
                flower.addFlower(drone.getPoint());
                break;
            case "TEMPERATE_DECIDUOUS_FOREST":
                wood.addWood(drone.getPoint());
                break;
            case "TEMPERATE_RAIN_FOREST":
                wood.addWood(drone.getPoint());
                fur.addfur(drone.getPoint());
                break;
            case "OCEAN":
                fish.addFish(drone.getPoint());
                break;
            case "LAKE":
                fish.addFish(drone.getPoint());
                break;
            case "BEACH":
                quartz.addQuartz(drone.getPoint());
                break;
            case "GRASSLAND":
                break;
            case "TEMPERATE_DESERT":
                ore.addOre(drone.getPoint());
                quartz.addQuartz(drone.getPoint());
                break;



        }

    }

	if ( drone.hasPU() && (drone.getNbCreek() == 8) ) {


	    drone.setState(new State26());
	}
	else {
	    drone.setState(new State13());
	}
    }

}

