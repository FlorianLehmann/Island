package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import java.awt.*;

public abstract class Res {

    public abstract void setAmount(int n);
    public abstract Point getNearest(Point point);
    public  abstract boolean hasR();
    public abstract int getAmount();
    public abstract Point getTabMax();
}
