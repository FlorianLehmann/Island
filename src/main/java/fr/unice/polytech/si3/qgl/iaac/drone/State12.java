package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource.*;
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
    for(int i=0;i<ReadJSON.getBiome().size();i++){
        switch (ReadJSON.getBiome().get(i)){
            case "TROPICAL_RAIN_FOREST":
                Wood.addWood(drone.getPoint());
                Flower.addFlower(drone.getPoint());
                SugarCane.addSugarCane(drone.getPoint());
                Fruits.addFruits(drone.getPoint());
                break;
            case "MANGROVE":
                Wood.addWood(drone.getPoint());
                Flower.addFlower(drone.getPoint());
                break;
            case "TROPICAL_SEASONAL_FOREST":
                SugarCane.addSugarCane(drone.getPoint());
                Wood.addWood(drone.getPoint());
                Flower.addFlower(drone.getPoint());
                Fruits.addFruits(drone.getPoint());
                break;
            case "TEMPERATE_DECIDUOUS_FOREST":
                Wood.addWood(drone.getPoint());
                //Fur.addFur(drone.getPoint()); // a suppr

                break;
            case "TEMPERATE_RAIN_FOREST":
                Wood.addWood(drone.getPoint());
                Fur.addFur(drone.getPoint());
                break;
            case "OCEAN":
                Fish.addFish(drone.getPoint());
                break;
            case "LAKE":
                Fish.addFish(drone.getPoint());
                break;
            case "BEACH":
                Quartz.addQuartz(drone.getPoint());
                break;
            case "GRASSLAND":
                Fur.addFur(drone.getPoint());
                break;
            case "TEMPERATE_DESERT":
                Ore.addOre(drone.getPoint());
                Quartz.addQuartz(drone.getPoint());
                break;
            //test
            case "TAIGA":

                break;
            case "SNOW":

                break;
            case "TUNDRA":

                break;
            case "ALPINE":
                Flower.addFlower(drone.getPoint());
                Ore.addOre(drone.getPoint());
                break;
            case "GLACIER":
                Flower.addFlower(drone.getPoint());

                break;
            case "SHRUBLAND":

                break;
            case "SUB_TROPICAL_DESERT":
                Quartz.addQuartz(drone.getPoint());

                break;



        }

    }

	if ( /*(((drone.getBudgetInit() - (drone.getBudgetInit()/3)) > drone.getBudget())  && (drone.getNbCreek() >= 2)) ||*/ drone.getNbAllerRetour()>=4) {


	    drone.setState(new State26());
	}
	else {
	    drone.setState(new State13());
	}
    }

}

