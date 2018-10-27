/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame.model;

/**
 *
 * @author XiaoQian Huang
 */
public class Bullet extends Movement {
    private int power;
    private boolean inUse = false;
   
    public void setPower(int power) 
    {
        this.power = power;
    }
 
    public int getPower() {
        return power;
    }

    public void setInUse(boolean inUse)
    {
        this.inUse = inUse;
    }
   
    public boolean isInUse() {
        return inUse;
    }

    @Override
    public int hitsize()
    {
        return 12;
    }  
}
