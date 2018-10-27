/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame.view;
import java.awt.Graphics;
import tankgame.model.*;
/**
 *
 * @author XiaoQian Huang
 */
public class BackgroundView {
    Background map[];
    Game g;
    MiniMap miniMap;
    public BackgroundView(Game g)
    {
        this.g = g;
        map = new Background[2];
        map[0] = new Background(g.getMap(), g, g.getPlayer()[0]);
        map[1] = new Background(g.getMap(), g, g.getPlayer()[1]);
        miniMap = new MiniMap(g);
    }
    
    public void update(Graphics g)
    {
        map[0].draw(g, 0, 0);
        map[1].draw(g, 512+32+20, 0);
        miniMap.draw(g, 512+32+20-this.g.getMap().getW() * 2, 1024 - this.g.getMap().getH() * 4);
    }
}
