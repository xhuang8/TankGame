/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame.draw;
import java.awt.Graphics;
import tankgame.*;
/**
 *
 * @author XiaoQian Huang
 */
public class BackgroundDraw {
    java.awt.Image im;
    String filename;
    final int TX = 32;
    final int TY = 32;
    final int MX = 320;
    final int MY = 240;
    
    public BackgroundDraw(String filename) 
    {
       this.filename = filename;
       im = java.awt.Toolkit.getDefaultToolkit().getImage("resources/" + filename);
    }
    
    public void draw(Graphics g, int x, int y)
    {
        g.drawImage(im, x, y, x + 32, y + 32, 0, 0, 64, 64, Tankworld.instance); 
    }
    
    public void drawWall(Graphics g, int x, int y)
    {
        g.drawImage(im, x, y, x + 32, y + 32, 0, 0, 32, 32, Tankworld.instance); 
    }

    public void draw(Graphics g, int x, int y, int tx, int ty) 
    { 
        g.drawImage(im, x, y, x + TX, y + TY, tx * TX % MX, ty * TY % MY, (tx * TX + TX) % MX, (ty * TY + TY) % MY, Tankworld.instance);
    }
    
    public void drawFrame(Graphics g, int x, int y, int n)
    {
    }
}
