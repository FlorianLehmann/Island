package sample.bot;

import eu.ace_design.island.bot.IExplorerRaid;
import java.util.LinkedList;
import java.util.List;
import sample.bot.drone.Drone;
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
    private List<Men> men;
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
	List<Men> men = new LinkedList();

	budget = (int) json.getInformations().get("budget");

	for (int i = 0; i < ((int) json.getInformations().get("men")); i++) {
	    men.add(new Men());
	}

	direction = EnumDirection.getEnumDirection((String) json.getInformations().get("heading"));

	drone = new Drone(direction, carte);

	//gérer les ressources
	
	
    }

    /**
     *
     * Take decision
     *
     */
    @Override
    public String takeDecision() {
	drone.getState().wait(drone);
	drone.getState().execute(drone);
	return drone.getAction();
    }

    @Override
    public void acknowledgeResults(String s) {
	json.read(s);
    }

    @Override
    public String deliverFinalReport() {
        return "CREEK:" + carte.getNearestCreekPU() +"\n" +"EMERGENCY:" + carte.getPU();
    }
}
