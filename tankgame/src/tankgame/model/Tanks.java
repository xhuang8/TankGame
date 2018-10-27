/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame.model;

import javax.swing.JOptionPane;
import tankgame.Tankworld;

/**
 *
 * @author XiaoQian Huang
 */
public class Tanks extends Movement {
    private int hp = 100;
    private int power = 10;
    private int maxBullets = 1;
    private int life = 3;
    private Bullet[] bullets = new Bullet[1];
    private Tanks opponent;
    private String tankID;
    
    public void setHp(int hp) 
    {
        this.hp = hp;
        if(this.hp <= 0)
        {
            this.hp = 100;
            setLife(getLife() - 1);
            setPower(10);
            if(getLife() < 0)
            {
                JOptionPane.showMessageDialog(Tankworld.instance, String.format("Tank%s win !!!", this.getOpponent().getTankID()));
                System.exit(0);
            }
        }
    }
   
    public int getHp()
    {
        return hp;
    }

    public void setPower(int power) 
    {
        this.power = power;
    }

    public int getPower() 
    {
        return power;
    }

    public void setMaxBullets(int maxBullets) 
    {
        this.maxBullets = maxBullets;
    }

    public int getMaxBullets() 
    {
        return maxBullets;
    }

    public Bullet[] getBullets() 
    {
        return bullets;
    }

    public void fire()
    {
        for(int i=0; i<bullets.length; ++i)
        {
            if(bullets[i] == null)
            {
                bullets[i] = new Bullet();
                bullets[i].setX(x);
                bullets[i].setY(y);
                bullets[i].setAngle(angle);
                bullets[i].forward(16);
                bullets[i].setPower(power);
                break;
            }
        }
    }

    public String getTankID() 
    {
        return tankID;
    }

    public void setTankID(String id) 
    {
        tankID = id;
    }

    public int getLife() 
    {
        return life;
    }

    public void setLife(int life) 
    {
        this.life = life;
    }
    
    @Override
    public void forward()
    {
        double x = getX(), y = getY();
        super.forward();
        if(Game.instance.over_screen(this) || Game.instance.hitWall(this)) 
        {
           setX(x);
           setY(y);
        }
    }
    
    @Override
    public void backward()
    {
        double x = getX(), y = getY();
        super.backward();
        if(Game.instance.over_screen(this) || Game.instance.hitWall(this)) 
        {
           setX(x);
           setY(y);
        }
    }

    public Tanks getOpponent() 
    {
        return opponent;
    }

    public void setOpponent(Tanks opponent) 
    {
        this.opponent = opponent;
    }
   
}
