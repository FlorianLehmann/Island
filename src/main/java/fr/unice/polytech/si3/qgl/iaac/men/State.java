package fr.unice.polytech.si3.qgl.iaac.men;

public interface State {

    void execute(Men men);
    void wait(Men men);

}
