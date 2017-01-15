package fr.unice.polytech.si3.qgl.iaac.drone;

public interface DroneState {

    void execute(Drone drone);

    void wait(Drone drone);
}
