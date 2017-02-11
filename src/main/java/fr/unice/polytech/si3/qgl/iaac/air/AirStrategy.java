package fr.unice.polytech.si3.qgl.iaac.air;

import fr.unice.polytech.si3.qgl.iaac.Carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.exploreIsland.Stop;
import fr.unice.polytech.si3.qgl.iaac.air.findIsland.EchoFace;

/**
 * Created by lehmann on 04/02/17.
 */
public class AirStrategy {

    private State state;
    private Drone drone;
    private ReadJSON json;
    private Carte carte;

    public AirStrategy(Drone drone, ReadJSON json, Carte carte) {
        this.drone = drone;
        this.json = json;
        this.carte = carte;
        state = new EchoFace();
    }

    public String takeAction() {
        if (!carte.tmp_hasAcrique())
            return state.execute(drone);
        return (new Stop().execute(drone));
    }

    public void acknowledgeResults() {
        state = state.wait(json);
        carte.addAirCase(drone.getCoord());
    }

}
