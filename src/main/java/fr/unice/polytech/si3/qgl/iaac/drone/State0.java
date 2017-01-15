package fr.unice.polytech.si3.qgl.iaac.drone;

public class State0 implements State {

    /**
     * do nothing
     * @param drone
     */
    @Override
    public void execute(Drone drone) {

    }

    /**
     * change the state
     * @param drone
     */
    @Override
    public void wait(Drone drone) {
        drone.setState(new EchoFront());
    }

}
