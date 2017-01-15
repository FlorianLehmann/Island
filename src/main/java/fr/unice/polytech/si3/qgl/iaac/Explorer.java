package fr.unice.polytech.si3.qgl.iaac;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.drone.Drone;
import fr.unice.polytech.si3.qgl.iaac.men.Men;

import java.awt.*;

public class Explorer implements IExplorerRaid {

    /**
     * Attributes
     */
    private Carte carte;
    private Men men;
    private ReadJSON json;
    private int budget;
    private EnumDirection direction;
    private Drone drone;

    /**
     * Initialize attributes with the JSON request
     */
    @Override
    public void initialize(String s) {
        carte = new Carte();

        json = new ReadJSON();
        json.read(s);


        budget = (int) json.getInformations().get("budget");


        direction = EnumDirection.getEnumDirection((String) json.getInformations().get("heading"));

        drone = new Drone(direction, carte);
        men = new Men(carte, new Point(0, 0));
        men.setBudget(drone.getBudget());

    }

    /**
     * Take decision
     */
    @Override
    public String takeDecision() {
        if (!drone.getEnd()) {
            drone.getState().wait(drone);
            drone.getState().execute(drone);
            if (drone.getEnd()) {
                men = new Men(carte, new Point((int) drone.getPoint().getX() * 3, (int) drone.getPoint().getY() * 3));
                men.setBudget(drone.getBudget());
            }
            return drone.getAction();
        } else {
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
        return "Report";
    }
}
