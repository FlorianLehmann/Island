package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.ECHO;

public class EchoLeft implements State {

    /**
     * Demande un echo sur la gauche
     */
    @Override
    public void execute(Drone drone) {

        drone.setAction(ECHO.toString(drone.getDirection().left()));

    }

    /**
     * Analyse du resultat et passsage à l'etat suivant
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
        if (ReadJSON.getInformations().get("found").equals("GROUND")) {
            drone.setState(new State6());
            drone.setCaseToTarget((int) ReadJSON.getInformations().get("range"));
        } else {
            drone.setState(new EchoRight());
            drone.setNbCaseLeft((int) ReadJSON.getInformations().get("range"));
        }
    }


}
