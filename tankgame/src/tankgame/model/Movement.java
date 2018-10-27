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
public class Movement {
    protected double x;
    protected double y;
    protected double angle;
 
    public void setX(double x)
    {
        this.x = x;
    }

    public double getX()
    {
        return x;
    }
    
    public void setY(double y) 
    {
        this.y = y;
    }

    public double getY() 
    {
        return y;
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
    }
    
    public double getAngle()
    {
        return angle;
    }

    public void forward(int unit) 
    {
        x += Math.cos(angle / 180 * Math.PI) * unit;
        y -= Math.sin(angle / 180 * Math.PI) * unit;
    }

    public void forward() 
    {
        forward(10);
    }

    public void backward() 
    {
        forward(-10);
    }
    
    public int hitsize()
    {
        return 32;
    }

    public void left() 
    {
        angle += 6;
        if (angle >= 360)
        {
            angle -= 360;
        }
    }

    public void right() 
    {
        angle -= 6;
        if (angle < 0) 
        {
            angle += 360;
        }
    }
    
    public double distance(Movement b)
    {
        return Math.hypot(getX() - b.getX(), getY() - b.getY());
    }  
}
