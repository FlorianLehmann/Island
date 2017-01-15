package fr.unice.polytech.si3.qgl.iaac.men;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumReadJSON.*;


public class CheminRessource implements State {

    private static boolean wayDefine = false;
    private static Deque<String> stack = new ArrayDeque<>();

    /**
     *
     *
     *
     */
    @Override
    public void execute(Men men) {
        EnumDirection direction = NORD;
        String tmp;
        int coordX;
        int coordY;

        while (ReadJSON.getContracts().size() > 0 && !(men.getRessource((String) ReadJSON.getContracts().get(0))).hasR()) {
            ReadJSON.getContracts().remove(0);
            ReadJSON.getAmount().remove(0);
        }
        if (ReadJSON.getContracts().size() > 0) {
            if (!wayDefine) {

                tmp = (String) ReadJSON.getContracts().get(0);


                (men.getRessource(tmp)).setAmount(ReadJSON.getAmount().get(0));
                Point point;
                if ("FISH".equals(tmp)) {
                    point = men.getACrique();
                } else {
                    point = men.getRessource(tmp).getTabMax();
                }
                coordX = (point.x * 3) - men.getPoint().x;
                coordY = (point.y * 3) - men.getPoint().y;
                if (coordX < 0)
                    direction = WEST;
                if (coordX > 0)
                    direction = EST;
                for (int i = 0; i < Math.abs(coordX); i++) {
                    stack.push(MOVETO.toString(direction.front()));
                    men.setCoord(direction);
                }
                if (coordY < 0)
                    direction = SUD;
                if (coordY > 0)
                    direction = NORD;
                for (int i = 0; i < Math.abs(coordY); i++) {
                    stack.push(MOVETO.toString(direction.front()));
                    men.setCoord(direction);
                }
                wayDefine = true;
            }
            if (stack.isEmpty()) {
                men.setAction(EXPLORE.toString(""));
            } else {
                men.setAction(stack.pop());
            }
        } else
        {
            men.setAction(STOP.toString(""));
        }
    }

    /**
     *
     *
     *
     */
    @Override
    public void wait(Men men) {
        men.subBudget((int) ReadJSON.getInformations().get(COST.toString()));
        if (stack.isEmpty())
            men.setState(new Explore());
    }

    public static void setWayDefine(boolean a) {
        wayDefine = a;
    }
}
