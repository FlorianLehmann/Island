package sample.bot.men;

import sample.bot.ReadJSON;

import java.util.Stack;

import static sample.bot.EnumJSON.EXPLORE;

public class State2 implements State {

    private static boolean wayDefine = false;
    private static Stack<String> stack = new Stack();

    /**
     *
     *
     *
     */
    @Override
    public void execute(Men men) {
        String tmp = (String) ReadJSON.getContracts().get(0);
        men.setAction(EXPLORE.toString(tmp));

    }

    /**
     *
     *
     *
     */
    @Override
    public void wait(Men men) {
        boolean resource;
        resource = false;
        for (int i = 0; i < ReadJSON.getResources().size(); i++) {
            if (ReadJSON.getResources().get(i).equals((String) ReadJSON.getContracts().get(0)))
                resource = true;
        }
        if (resource == true)
            men.setState(new State3());//récolte
        else
            men.setState(new State4());//avance


        //sinon
        //TODO
        /*men.getRessource((String) ReadJSON.getContracts().get(0)).setAmount(men.getRessource((String) ReadJSON.getContracts().get(0)).getAmount() - ReadJSON.getCollect());
        if (men.getRessource((String) ReadJSON.getContracts().get(0)).getAmount() < 0)
            men.setState(new State11());*/
    }


}
