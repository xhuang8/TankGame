/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import tankgame.model.*;
import tankgame.draw.*;

/**
 *
 * @author XiaoQian Huang
 */
public class Background {
    MapBuild map;
    Tanks tank;
    Game game;
    Font font = new Font("Arial", 24, 0);
    int x1, x2, y1, y2;
    int width, height;
    BackgroundSet background;
    
    public Background(MapBuild m, Game g, Tanks t, int w, int h)
    {
        background = new BackgroundSet("Background.png");
        this.map = m;
        this.game = g;
        this.tank = t;
        this.width = w;
        this.height = h;
    }
    
    public Background(MapBuild m, Game g, Tanks t) 
    {
        this(m, g, t, 16, 32);
    }
    
    public void calcRange()
    {
        this.x1 = (int)Math.floor((tank.getX() - 16)/ 32 - this.width / 2); 
        this.y1= (int)Math.floor((tank.getY() - 16) / 32 - this.height / 2);
        this.x2 = (int)Math.floor((tank.getX() - 16) / 32 + this.width / 2);
        this.y2 = (int)Math.floor((tank.getY() - 16)/ 32 + this.height / 2);
        
        while(this.x1 < 0 && this.x2 < map.getW()) 
        {
            ++this.x1;
            ++this.x2;
        }
        
        while(this.x2 >= map.getW() && this.x1 > 0) 
        {
            --this.x1;
            --this.x2;
        }
        
        while(this.y1 < 0 && this.y2 < map.getH())
        {
            ++this.y1;
            ++this.y2;
        }
        
        while(this.y2 >= map.getH() && this.y1 > 0) 
        {
            --this.y1;
            --this.y2;
        }
    }
    
    public void draw(Graphics g, int x, int y)
    {
        calcRange();
        clearRange(g, x, y);
        drawMap(g, x, y);
        drawTanks(g, x, y);
        drawBullets(g, x, y);
        drawWalls1(g, x, y);
        drawWalls2(g, x, y);
        drawPowerups(g, x, y);
        drawHP(g, x, y);
    }
    
    public void clearRange(Graphics g, int x, int y)
    {
        g.clearRect(x, y, width * 32, height * 32);
    }
    
    public void drawMap(Graphics g, int x, int y)
    {
        for(int j=y1; j<=y2; ++j) 
        {
            for(int i=x1; i<=x2; ++i) 
            {
                background.draw(g, x + (i - x1) * 32, y + (j - y1) * 32, i,  j);
            }
        }
    }
    
    public void drawTanks(Graphics g, int x, int y)
    {
        for(int i=0; i<2; ++i)
        {
            Tanks tank = this.game.getPlayer()[i];
            String id = tank.getTankID();
            int angle =  (int)tank.getAngle() / 6 + 1;
            BackgroundDraw im = new BackgroundDraw(String.format("Tank%s/tank%03d.png", id, angle));
            int tx = (int)tank.getX() - 16 - x1 * 32 + x;
            int ty = (int)tank.getY() - 16 - y1 * 32 + y;
            if(tx >= x && tx <= x + width * 32 && ty >= y && ty <= y + height * 32)
               im.draw(g, tx, ty);
        }
    }

    
    public void drawBullets(Graphics g, int x, int y)
    {
        for(int i=0; i<2; ++i)
        {
            Tanks tank = this.game.getPlayer()[i];
            Bullet b[] = tank.getBullets();
            for(int j=0; j<b.length; ++j)
            {
                Bullet bullet = b[j];
                if(bullet == null) continue;
                int angle =  (int)bullet.getAngle() / 6 + 1;
                BackgroundDraw im = new BackgroundDraw("shell.gif");
                int tx = (int)bullet.getX() - 12 - x1 * 32 + x;
                int ty = (int)bullet.getY() - 12 - y1 * 32 + y;
                if(tx >= x && tx <= x + width * 32 && ty >= y && ty <= y + height * 32)
                   im.draw(g, tx, ty);
            }
        }
    }
    
    public void drawHP(Graphics g, int x, int y)
    {
        for(int i=0; i<2; ++i)
        {
            Tanks tank = this.game.getPlayer()[i];
            String id = tank.getTankID();
            int angle =  (int)tank.getAngle() / 6 + 1;
            int tx = (int)tank.getX() - 16 - x1 * 32 + x;
            int ty = (int)tank.getY() - 16 - y1 * 32 + y;
            if(tx >= x && tx <= x + width * 32 &&
               ty >= y && ty <= y + height * 32)
            {
               g.setFont(font);
               g.setColor(Color.WHITE);
               g.fillRect(tx, ty - 20, 32, 4);
               g.setColor(Color.RED);
               g.fillRect(tx, ty - 20, (int)(32 * tank.getHp() / 100), 4);
            }
        }
        
        BackgroundDraw im = new BackgroundDraw(String.format("Tank%s.gif", this.tank.getTankID()));
        for(int i=0; i<tank.getLife(); ++i)
        {
            im.draw(g, x + 32 + i * 32 + (i - 1) * 20, y + 20);
        }
    }
    
    public void drawWalls1(Graphics g, int x, int y) 
    {
        List<BreakableWall> list = game.getWall1();
        BackgroundDraw im = new BackgroundDraw("Wall1.gif");
           
        for(int i=0; i<list.size(); ++i)
        {
            BreakableWall wall = list.get(i);
            int tx = (int)wall.getX() - 16 - x1 * 32 + x;
            int ty = (int)wall.getY() - 16 - y1 * 32 + y;
            if(tx >= x && tx <= x + width * 32 && ty >= y && ty <= y + height * 32)
            {
               im.drawWall(g, tx, ty);
            }
        }
    }
    
    public void drawWalls2(Graphics g, int x, int y) 
    {
        List<UnbreakableWall> list = game.getWall2();
        BackgroundDraw im = new BackgroundDraw("Wall2.gif");  
        for(int i=0; i<list.size(); ++i)
        {
            UnbreakableWall wall = list.get(i);
            int tx = (int)wall.getX() - 16 - x1 * 32 + x;
            int ty = (int)wall.getY() - 16 - y1 * 32 + y;
            if(tx >= x && tx <= x + width * 32 && ty >= y && ty <= y + height * 32)
            {
               im.drawWall(g, tx, ty);
            }
        }
    }
    
    public void drawPowerups(Graphics g, int x, int y)
    {
        List<PowerUp> list = game.getPowerups();
        BackgroundDraw im = new BackgroundDraw("Weapon.gif");
        System.out.println(list.size());
        for(int i=0; i<list.size(); ++i)
        {
            PowerUp pup = list.get(i);
            int tx = (int)pup.getX() - 16 - x1 * 32 + x;
            int ty = (int)pup.getY() - 16 - y1 * 32 + y;
            if(tx >= x && tx <= x + width * 32 && ty >= y && ty <= y + height * 32)
            {
               im.drawWall(g, tx, ty);
            }
        }
    }
}
