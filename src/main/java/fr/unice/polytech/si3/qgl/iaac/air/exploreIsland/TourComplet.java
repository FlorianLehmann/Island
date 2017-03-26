package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.air.AirStrategy;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;

/**
 * Created by sebde on 12/02/2017.
 */
public class TourComplet implements State {

    private int etat=0;
    private int range=0;

    public TourComplet(int etat){this.etat=etat;}

    public TourComplet(int etat, int range){
        this.range=range;
        this.etat=etat;
    }

    public String execute(Drone drone){
        if(etat==0)return drone.fly();
        if(etat==1)return drone.echo(drone.getLastDirection());
        if(etat==2)return drone.heading(drone.getLastDirection());
        if(etat==3)return drone.fly();
        return drone.heading(drone.getLastDirection());

    }

    public State nextState(ReadJSON json) {
        if(etat==0) return new TourComplet(1,range);
        if(etat==1){
            range=json.getRange();
            if (json.getFound() && range <= 3) {
                return new TourComplet(0,range);
            }
            return new TourComplet(2);
        }
        if(etat==2){return new TourComplet(3);}
        if(etat==3){return new TourComplet(4);}
        AirStrategy.incHalfTurn();
        return new EchoFront8();

    }

}
