package fr.unice.polytech.si3.qgl.iaac.men;

public class State0 implements State{

    @Override
    public void execute(Men men) {

    }

    @Override
    public void wait(Men men) {
	men.setState(new State1());
    }
    
}
