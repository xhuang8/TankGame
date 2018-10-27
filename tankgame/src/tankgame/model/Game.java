/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame.model;
import static java.awt.event.KeyEvent.*;
import java.io.*;
import java.util.*;
import tankgame.view.*;
/**
 *
 * @author XiaoQian Huang
 */
public class Game {
    public static Game instance;
    private Tanks[] player = new Tanks[2];
    private MapBuild map;
    private ArrayList<BreakableWall> wall1 = new ArrayList<>();
    private ArrayList<UnbreakableWall> wall2 = new ArrayList<>();
    private ArrayList<PowerUp> powerups = new ArrayList<>();
    BackgroundView view;
    int updatecount = 0;
    
    public void setPlayer(Tanks[] player) 
    {
        this.player = player;
    }
 
    public Tanks[] getPlayer()
    {
        return player;
    }

    public void setMap(MapBuild map)
    {
        this.map = map;
    }
    
    public MapBuild getMap() 
    {
        return map;
    }

    private void setInstance()
    {
        Game.instance = this;
    }
    
    public Game() throws FileNotFoundException
    {
        this.map = new MapBuild("map.txt");
        for(int j=0; j<map.getH() - 1; ++j)
        {
           for(int i=0; i<map.getW() - 1; ++i)
           {
                if(map.getMap()[j][i] == 1) 
                {
                    BreakableWall w1 = new BreakableWall();
                    w1.setX(i * 32 + 16);
                    w1.setY(j * 32 + 16);
                    wall1.add(w1);
                }
                if(map.getMap()[j][i] == 2) 
                {
                    UnbreakableWall w2 = new UnbreakableWall();
                    w2.setX(i * 32 + 16);
                    w2.setY(j * 32 + 16);
                    wall2.add(w2);
                }
            }
        }
        for(int i=0; i<2; ++i)
        {
            player[i] = new Tanks();
        }
        player[0].setX(48);
        player[0].setY(48);
        player[0].setAngle(0);
        player[0].setTankID("1");
        player[0].setOpponent(player[1]);
        player[1].setX((map.getW() - 1) * 32 - 48);
        player[1].setY((map.getH() - 1) * 32 - 48);
        player[1].setAngle(180);
        player[1].setTankID("2");
        player[1].setOpponent(player[0]);
        setInstance();
    }

    public void keyPress(int ch)
    {
        switch(ch)
        {
            case 'W': getPlayer()[0].forward(); break;
            case 'S': getPlayer()[0].backward(); break;
            case 'A': getPlayer()[0].left(); break;
            case 'D': getPlayer()[0].right(); break;
            case 'F': getPlayer()[0].fire(); break;
            
            case VK_UP: getPlayer()[1].forward(); break;
            case VK_DOWN: getPlayer()[1].backward(); break;
            case VK_LEFT: getPlayer()[1].left(); break;
            case VK_RIGHT: getPlayer()[1].right(); break;
            case VK_ENTER: getPlayer()[1].fire(); break;
        }
    }
    
    public void update() 
    {
        ++updatecount;
        for(int i=0; i<2; ++i)
        {
            Tanks tank = getPlayer()[i];
            
            for(int k=0; k<getPowerups().size(); ++k)
            {
                if(hit(tank, getPowerups().get(k)))
                {
                    getPowerups().remove(k);
                    tank.setPower(tank.getPower() + 10);
                }
            }
            
            for(int j=0; j<tank.getBullets().length; ++j)
            {
                Bullet b = tank.getBullets()[j];
                if(b == null) continue;
                b.forward(20);
                
                if(over_screen(b)) 
                {
                    tank.getBullets()[j] = null;
                    continue;
                }
                Tanks opponent = getPlayer()[1 - i]; 
                if(hit(b, opponent))
                {
                    opponent.setHp(opponent.getHp() - b.getPower());
                    tank.getBullets()[j] = null;
                    continue;
                }
                for(int k=0; k<getWall1().size(); ++k)
                {
                    BreakableWall w = getWall1().get(k);
                    if(hit(b, w))
                    {
                        tank.getBullets()[j] = null;
                        if(Math.random() < 0.2)
                        {
                            PowerUp p = new PowerUp();
                            p.setX(w.getX());
                            p.setY(w.getY());
                            getPowerups().add(p);
                        }
                        getWall1().remove(k);
                        break;
                    }
                }
                for(int k=0; k<getWall2().size(); ++k)
                {
                    UnbreakableWall w = getWall2().get(k);
                    if(hit(b, w))
                    {
                        tank.getBullets()[j] = null;
                        break;
                    }
                }
            }
        }
    }
    
    public boolean over_screen(Movement m) 
    {
        return m.getX() < 0 || m.getX() >= getMap().getW() * 32 - 32|| m.getY() < 0 || m.getY() >= getMap().getH() * 32 - 32;
    }
  
    public boolean hitWall(Movement m) 
    {
         for(int k=0; k<getWall1().size(); ++k)
         {
            BreakableWall w = getWall1().get(k);
            if(hit(m, w))
            {
                return true;
            }
        }
        for(int k=0; k<getWall2().size(); ++k)
        {
            UnbreakableWall w = getWall2().get(k);
            if(hit(m, w))
            {
                return true;
            }
        }
        return false;
    }
 
    public boolean hit(Movement a, Movement b) 
    {
        double dis = a.distance(b);
        return dis < Math.max(a.hitsize(), b.hitsize());
    }

    public ArrayList<BreakableWall> getWall1()
    {
        return wall1;
    }

    public ArrayList<UnbreakableWall> getWall2()
    {
        return wall2;
    }

    public ArrayList<PowerUp> getPowerups() 
    {
        return powerups;
    }
}
