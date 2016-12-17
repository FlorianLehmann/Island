package sample.bot;

import eu.ace_design.island.bot.IExplorerRaid;
import java.util.LinkedList;
import java.util.List;
import sample.bot.men.Men;
import sample.bot.drone.Drone;
import sample.bot.carte.poi.ressource.*;
import sample.bot.drone.State;
import sample.bot.carte.Carte;
import sample.bot.EnumDirection;
import java.awt.*;
public class Explorer implements IExplorerRaid {

    /**
     *
     * Attributes
     *
     */
    private Carte carte;
    private Men men;
    private ReadJSON json;
    private int budget;
    private EnumDirection direction;
    private Drone drone;
    //voir ou on va stocker les ressources

    /**
     *
     * Initialize attributes with the JSON request
     *
     */
    @Override
    public void initialize(String s) {
        carte = new Carte();
        
	json = new ReadJSON();
	json.read(s);
	

	budget = (int) json.getInformations().get("budget");


	direction = EnumDirection.getEnumDirection((String) json.getInformations().get("heading"));

	drone = new Drone(direction, carte);
        
        men = new Men(carte, new Point(0,0));


	//gérer les ressources
	

    }

    /**
     *
     * Take decision
     *
     */
    @Override
    public String takeDecision() {
	if (!drone.getEnd()) {
	    drone.getState().wait(drone);
	    drone.getState().execute(drone);
        if (drone.getEnd()) {
            men = new Men(carte, drone.getPoint());
        }
        return drone.getAction();
	}
	else {
        men.getState().wait(men);
        men.getState().execute(men);

        
        
	}
	return men.getAction();
    }

    @Override
    public void acknowledgeResults(String s) {
	json.read(s);
    }

    @Override
    public String deliverFinalReport() {
        return "CREEK:" + Wood.getTabMax().getX()+ ";" + Wood.getTabMax().getY()+ Wood.toStringg();
    }
}
