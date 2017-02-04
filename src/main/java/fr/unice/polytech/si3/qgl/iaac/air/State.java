package fr.unice.polytech.si3.qgl.iaac.air;


import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

/**
 * Created by sebde on 04/02/2017.
 */
public interface State {

    public String execute(Drone drone);

    public State wait(ReadJSON json);

    public boolean isOver();
}
