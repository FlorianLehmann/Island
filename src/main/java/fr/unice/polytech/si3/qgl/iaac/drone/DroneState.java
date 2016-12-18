package sample.bot.drone;

public interface DroneState {

    void execute(Drone drone);
    void wait(Drone drone);
}
