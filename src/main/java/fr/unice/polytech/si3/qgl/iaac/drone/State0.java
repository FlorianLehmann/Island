package fr.unice.polytech.si3.qgl.iaac.drone;

public class State0 implements State{

    @Override
    public void execute(Drone drone) {

    }

    @Override
    public void wait(Drone drone) {
	drone.setState(new State1());
    }
    
}
