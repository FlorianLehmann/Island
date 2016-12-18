package fr.unice.polytech.si3.qgl.iaac.men;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import java.awt.*;
import java.util.Stack;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;

public class State1 implements State {

    private static boolean wayDefine = false;
    private static Stack<String> stack = new Stack();

    /**
     *
     *
     *
     */
    @Override
    public void execute(Men men) {
        //on définie le chemin jsuqu'à la ressource
        EnumDirection direction = NORD;
        String tmp = new String();
        int X;
        int Y;
        /*for (int i = 0; i <; i++) {
            if (ReadJSON.getContracts().size() > 0) {
                tmp = (String) ReadJSON.getContracts().get(0);


                if (!(men.getRessource(tmp)).hasR()) {
                    ReadJSON.getContracts().remove(0);
                    ReadJSON.getAmount().remove(0);
                }
            }
        }*/
        while (ReadJSON.getContracts().size() > 0 && !(men.getRessource((String) ReadJSON.getContracts().get(0))).hasR()) {
            ReadJSON.getContracts().remove(0);
            ReadJSON.getAmount().remove(0);
        }
        if (ReadJSON.getContracts().size() > 0) {
            if (!wayDefine) {

                tmp = (String) ReadJSON.getContracts().get(0);


                //Point point = (men.getRessource(tmp)).getNearest(men.getPoint());
                (men.getRessource(tmp)).setAmount(ReadJSON.getAmount().get(0));
                Point point;
                if (tmp.equals("FISH")) {
                    point = men.getACrique();
                } else {
                    point = men.getRessource(tmp).getTabMax();
                }
                X = (int) ((point.getX() * 3) - men.getPoint().getX());//(point.getX()-men.getPoint().getX());
                Y = (int) ((point.getY() * 3) - men.getPoint().getY());//(point.getY()-men.getPoint().getY());
                if (X < 0)
                    direction = WEST;
                if (X > 0)
                    direction = EST;
                for (int i = 0; i < Math.abs(X); i++) {
                    stack.push(MOVETO.toString(direction.front()));
                    men.setCoord(direction);
                }
                if (Y < 0)
                    direction = SUD;
                if (Y > 0)
                    direction = NORD;
                for (int i = 0; i < Math.abs(Y); i++) {
                    stack.push(MOVETO.toString(direction.front()));
                    men.setCoord(direction);
                }
                wayDefine = true;
            }
            if (stack.isEmpty()) {
                men.setAction(EXPLORE.toString(""));//on débarque!

                //men.setAction(EXPLORE.toString(tmp));//risque de bug si la ressource existe pas
            } else {
                men.setAction(stack.pop());
            }
        } else

        {
            men.setAction(STOP.toString(""));
        }
        //tant qu'il reste des élément dans la pile on continue'


    }

    /**
     *
     *
     *
     */
    @Override
    public void wait(Men men) {
        men.subBudget((int) ReadJSON.getInformations().get("cost"));

        //drone.subBudget((int) ReadJSON.getInformations().get("cost"));
        if (stack.isEmpty())
            men.setState(new State2());
    }

    public static void setWayDefine(boolean a) {
        wayDefine = a;
    }
}
