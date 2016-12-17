package fr.unice.polytech.si3.qgl.iaac.men;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource.*;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import java.util.Stack;
import java.awt.*;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;

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
        if(!wayDefine){
            if (ReadJSON.getContracts().size() > 0)
                tmp = (String) ReadJSON.getContracts().get(0);
            Point point = (men.getRessource(tmp)).getNearest(men.getPoint());
            (men.getRessource(tmp)).setAmount(ReadJSON.getAmount().get(0));
            X = (int) (Wood.getTabMax().getX()-men.getPoint().getX());//(point.getX()-men.getPoint().getX());
            Y = (int) (Wood.getTabMax().getY()-men.getPoint().getY());//(point.getY()-men.getPoint().getY());
            if (X < 0)
                direction = WEST;
            if (X > 0)
                direction = EST;
            for(int i = 0; i < Math.abs(X)*3; i++ )
                stack.push(MOVETO.toString(direction.front()));
            if (Y < 0)
                direction = SUD;
            if (Y > 0)
                direction = NORD;
            for(int i = 0; i < Math.abs(X)*3; i++ )
                stack.push(MOVETO.toString(direction.front()));
            wayDefine = true;
        }
        if (stack.isEmpty())
            men.setAction(EXPLORE.toString(tmp));//risque de bug si la ressource existe pas
        else
            men.setAction(stack.pop());
        
        //tant qu'il reste des élément dans la pile on continue'
            

    }

    /**
     *
     *
     *
     */
    @Override
    public void wait(Men men) {
        //drone.subBudget((int) ReadJSON.getInformations().get("cost"));
        if (stack.isEmpty())
            men.setState(new State11());
    }


}
