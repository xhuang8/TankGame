/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame.view;


import tankgame.model.Game;
import tankgame.model.Movement;
import tankgame.model.Tanks;
import java.awt.Color;
import java.awt.Graphics;
/**
 *
 * @author XiaoQian Huang
 */
public class MiniMap {
  Game game;
  
  MiniMap(Game g)
  {
      this.game = g;
  }
  
  void drawMovable(Graphics g, Movement t, Color c, int x, int y)
  {
      g.setColor(c);
      g.fillRect((int)(x + Math.floor(t.getX() / 32) * 4), (int)(y + Math.floor(t.getY() / 32) * 4), 4, 4);
  }
  void draw(Graphics g, int x, int y)
  {
      for(int i=0; i<2; ++i)
      {
          drawMovable(g, game.getPlayer()[i], Color.RED, x, y);
      }
      
      for(int i=0;i<game.getWall1().size(); ++i)
      {
          drawMovable(g, game.getWall1().get(i), Color.WHITE, x, y);
      }
      
      for(int i=0;i<game.getWall2().size(); ++i)
      {
          drawMovable(g, game.getWall2().get(i), Color.BLACK, x, y);
      }
      
      for(int i=0;i<game.getPowerups().size(); ++i)
      {
          drawMovable(g, game.getPowerups().get(i), Color.GREEN, x, y);
      }
  }    
}
