package sample.bot.drone;

public interface State {

    void execute(Drone drone);
    void wait(Drone drone);

}
